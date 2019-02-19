package com.ant.base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.ant.utils.CommonUtils;
import com.ant.utils.LogUtil;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * className:QiumiExceptionHanlder
 * author:"anthui"
 * date:2017/6/21
 * describe:全局异常捕捉
 */
public class UxExceptionHelper implements UncaughtExceptionHandler {

    public static final String TAG = "UxExceptionHelper";

    public static final String ACTION = "com.haoxin.ux.base.UxExceptionHelper";

    //系统默认的UncaughtException处理类
    private UncaughtExceptionHandler mDefaultHandler;
    //GlobalExceptionHanlder实例
    private static UxExceptionHelper mHandler;
    //程序的Context对象
    private Context mContext;
    //用来存储设备信息和异常信息
    private Map<String, String> infos = new HashMap<String, String>();

    //用于格式化日期,作为日志文件名的一部分
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss", Locale.getDefault());

    //日志位置
    private String logPath = null;

    //异常信息等级

    public UxExceptionHelper() {
        logPath = Environment.getExternalStorageDirectory() + "/ux/" + "log/";
    }


    /**
     * 注册异常处理
     *
     * @param context
     */
    public void register(Context context) {
        mContext = context;
        //获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        //设置该GlobalExceptionHanlder为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 获取GlobalExceptionHanlder实例 ,单例模式
     */
    public static UxExceptionHelper getInstance() {
        if (mHandler == null) {
            mHandler = new UxExceptionHelper();
        }
        return mHandler;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!CommonUtils.isNetConnectionAvailable(mContext)) {
            Toast.makeText(mContext, "网络异常，请检查网络设置", Toast.LENGTH_LONG).show();
        }

        if (!handleException(ex) && mDefaultHandler != null) {
            //如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
         /*   try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Log.e(TAG, "error : ", e);
            }

            systemExit();
        }*/
        }
    }

    /**
     * 退出程序
     */
    private void systemExit() {
        Intent intent = new Intent();
        intent.setAction(UxExceptionHelper.ACTION);
        android.os.Process.killProcess(android.os.Process.myPid());
        //    MobclickAgent.onKillProcess(mContext);  //友盟
        System.exit(0);
    }

    /**
     * 检查SD卡是否可用
     *
     * @return
     */
    public static boolean checkSDCard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED))
            return true;
        else
            return false;
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

        //使用Toast来显示异常信息
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                //       Toast.makeText(mContext, R.string.global_exception_tips, Toast.LENGTH_LONG).show();
                Looper.loop();
            }
        }.start();

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
     * @param ctx
     */
    public void collectDeviceInfo(Context ctx) {
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);
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
     * @param ex
     * @return 返回文件名称, 便于将文件传送到服务器
     */
    private String saveCrashInfo2File(Throwable ex) {

        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : infos.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key + "=" + value + "\n");
        }

        StringWriter errorsWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(errorsWriter);
        ex.printStackTrace(printWriter);
        String err = errorsWriter.toString();
        printWriter.close();
        sb.append(err);

        String toString = sb.toString();

        //上报错误
        // MobclickAgent.reportError(mContext, toString);

        LogUtil.e("saveCrashInfo2File" + toString);
        return toString;
    }

//        Writer writer = new StringWriter();
//        PrintWriter printWriter = new PrintWriter(writer);
//        ex.printStackTrace(printWriter);
//        Throwable cause = ex.getCause();
//        while (cause != null) {
//            cause.printStackTrace(printWriter);
//            cause = cause.getCause();
//        }
//        printWriter.close();
//        String result = writer.toString();
//        sb.append(result);
//        Log.e(TAG, result);//此处如果改成Log.d()；有些手机异常会打印不出来
//        FileOutputStream fos = null;
//
//
//        try {
//            long timestamp = System.currentTimeMillis();
//            String time = formatter.format(new Date());
//            String fileName = "qiumi-" + time + "-" + timestamp + ".log";
//            if (checkSDCard()) {
//                File dir = new File(logPath);
//                if (!dir.exists()) {
//                    dir.mkdirs();
//                }
//                fos = new FileOutputStream(logPath + fileName);
//
//                fos.write(sb.toString().getBytes());
//
//                //UMeng 异常  上报
//                // MobclickAgent.reportError(mContext, sb.toString());
//                MobclickAgent.reportError(mContext, ex);
//
//                LogUtil.e("saveCrashInfo2File:" + sb.toString());
//                //189bata 异常统计
//                //   UsrActionAgent.onError(mContext,sb.toString());
//
//            }
//            return fileName;
//        } catch (Exception e) {
//            Log.e(TAG, "an error occured while writing file...", e);
//        } finally {
//            try {
//                if (fos != null) {
//                    fos.close();
//                }
//            } catch (IOException e) {
//                Log.e(TAG, "an error occured while close file...", e);
//            }
//        }
//        return null;


    /**
     * 删除所有的日志文件
     */
    public void clearAllLogs(final Context ctx) {
        if (checkSDCard()) {
            File file = new File(logPath);
            if (file != null && file.exists()) {
                File[] files = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    files[i].delete();
                }
            }
        }
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

