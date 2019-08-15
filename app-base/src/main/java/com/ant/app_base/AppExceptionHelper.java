package com.ant.app_base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import com.ant.app_utils.AppHelper;
import com.ant.app_utils.LogUtil;
import com.ant.app_utils.StringUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * className:QiumiExceptionHanlder
 * author:"anthui"
 * date:2017/6/21
 * describe:全局异常捕捉
 */
public class AppExceptionHelper implements UncaughtExceptionHandler {

    public static final String TAG = "AppExceptionHelper";

    public static final String ACTION = "com.ux.base.AppExceptionHelper";

    //系统默认的UncaughtException处理类
    private UncaughtExceptionHandler mDefaultHandler;
    //GlobalExceptionHanlder实例
    private static AppExceptionHelper mHandler;
    //程序的Context对象
    private Context mContext;
    //用来存储设备信息和异常信息
    private Map<String, String> infos = new HashMap<String, String>();

    //用于格式化日期,作为日志文件名的一部分
    private DateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss", Locale.getDefault());

    //日志位置
    private String logPath = "";
    private String file_name = "";

    //异常信息等级

    private AppExceptionHelper() {
        //不用外部 部分可能机子没有权限
//        logPath = Environment.getExternalStorageDirectory() + "/ux/" + "log/";
    }


    /**
     * 注册异常处理
     *
     * @param context
     */
    public void register(Context context) {
        mContext = context;
        logPath = mContext.getFilesDir() + "/app_crashLog/";

        String versionName = AppHelper.getVersionName(context);
        file_name = "crash_" + versionName + ".txt";
        //获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        //设置该GlobalExceptionHanlder为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 获取GlobalExceptionHanlder实例 ,单例模式
     */
    public static AppExceptionHelper getInstance() {
        if (mHandler == null) {
            mHandler = new AppExceptionHelper();
        }
        return mHandler;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
//        if (!CommonUtils.isNetConnectionAvailable(mContext)) {
//            Toast.makeText(mContext, "网络异常，请检查网络设置", Toast.LENGTH_LONG).show();
//        }

        if (!handleException(ex) && mDefaultHandler != null) {
            //如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, ex);
        }
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(mContext, "程序开小差了，将会在2秒后退出", Toast.LENGTH_SHORT).show();//使用Toast来显示异常信息
                Looper.loop();
            }
        }.start();


        SystemClock.sleep(3000);//延迟2秒杀进程
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    /**
     * 退出程序
     */
    private void systemExit() {
        Intent intent = new Intent();
        intent.setAction(AppExceptionHelper.ACTION);
        android.os.Process.killProcess(android.os.Process.myPid());
        //    MobclickAgent.onKillProcess(mContext);  //友盟
        System.exit(0);
    }


    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     *
     * @param ex
     * @return true:如果处理了该异常信息;否则返回false.
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return true;
        }

        //收集设备参数信息
        collectDeviceInfo(mContext);
        //保存日志文件
        try {

            saveCrashInfo2File(ex);
        } catch (Exception e) {
            LogUtil.e("文件保存异常");
        }

        //分类处理异常
        return true;
    }

    /**
     * 收集设备参数信息
     *
     * @param mContext
     */
    private void collectDeviceInfo(Context mContext) {
        try {
            PackageManager pm = mContext.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = pi.versionName == null ? "null" : pi.versionName;
                String versionCode = pi.versionCode + "";
                infos.put("versionName", versionName);
                infos.put("versionCode", versionCode);
            }
        } catch (NameNotFoundException e) {
            Log.e(TAG, "an error occured when collect package info", e);
        }
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                infos.put(field.getName(), field.get(null).toString());
                Log.d(TAG, field.getName() + " : " + field.get(null));
            } catch (Exception e) {
                Log.e(TAG, "an error occured when collect crash info", e);
            }
        }
    }

    /**
     * 保存错误信息到文件中
     *
     * @param throwable
     * @return 返回文件名称, 便于将文件传送到服务器
     */
    private String saveCrashInfo2File(Throwable throwable) {
        String time = formatter.format(new Date());

        StringBuffer sb = new StringBuffer();

        sb.append("\n\n******************" + time + "**********************\n");

        //先添加设备信息
        for (Map.Entry<String, String> entry : infos.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key + "=" + value + "\n");
        }

//        String fileName = "crash_" + time + ".txt";
        sb.append("file_name=" + file_name + "\n");

        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        throwable.printStackTrace(printWriter);
        Throwable cause = throwable.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        sb.append("******************************错误信息**************************\n");

        sb.append(result);
//        Log.e(TAG, result);//此处如果改成Log.d()；有些手机异常会打印不出来
        FileWriter fileWriter = null;
        try {

            //判断文件夹是否存在
            File dir = new File(logPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            fileWriter = new FileWriter(logPath + file_name, true);//如果保存失败，很可能是没有写SD卡权限
            fileWriter.write(sb.toString());
//            fileWriter.write("000000000000000000000000000000000000000000000000000");
            fileWriter.flush();

        } catch (Exception e) {
//            Log.e(TAG, "==============================错误2===============================.", e);
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
//                Log.e(TAG, "=============================错误1================================.", e);
            }
        }

        //上报错误
        // MobclickAgent.reportError(mContext, toString);
        return sb.toString();
    }

    /**
     * 删除所有的日志文件
     */
    public void clearAllLogs(final Context ctx) {
        if (StringUtil.isEmpty(logPath)) {
            LogUtil.e("文件目录未初始化");
            return;
        }
        File file = new File(logPath);
        if (file != null && file.exists()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                files[i].delete();
            }
        }

    }

    /**
     * 获取日志文件
     */
    public File getLogFile() {
        File file = new File(logPath + file_name);
        if (file != null && file.exists() && file.isFile()) {
            return file;
        }
        return null;
    }

    /**
     * 获取日志目录下的所有日志文件
     */
    public File[] getAllLogFile() {

        if (StringUtil.isEmpty(logPath)) {
            LogUtil.e("文件目录未初始化");
            return null;
        }
        File file = new File(logPath);
        if (file != null && file.exists()) {
            File[] files = file.listFiles();
            if (files != null && files.length != 0) {
                return files;
            }
        }
        return null;
    }

    /**
     * 异步删除所有日志文件
     *
     * @param ctx
     */
    public void asynClearAllLogs(final Context ctx) {
        new Thread() {
            public void run() {
                clearAllLogs(ctx);
            }
        }.start();
    }
}

