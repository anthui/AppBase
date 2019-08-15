package com.ant.app_utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.Display;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>Title: 常用工具类         </p>
 * <p>Description: 包括
 * 1. 判断网络是否连接正常
 * 2. 隐藏键盘
 * 3. 显示EditText错误信息
 * 4. 创建快捷方式
 * 5. 删除快捷方式
 * 6. 图片切换特效
 * 7. px,dip,sp的相互转换
 * 8. 截屏
 * <p>Update Comments: 修复：当没有网络权限时， isNetConnectionAvailable抛异常BUG               </p>
 * <p>Update Comments: 添加：判断是否3G2G，wifi        </p>
 */
public class CommonUtils {

    /**
     * 判断apk是否存在
     */
    public static boolean checkApkExist(Context context, String packageName) {//检查QQ包是否存在
        if (packageName == null || "".equals(packageName)) {
            return false;
        }
        try {
            ApplicationInfo info = context.getPackageManager().getApplicationInfo(packageName
                    , PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    /**
     * 判断网络是否可用
     *
     * @param context
     * @return true可用 false 不可用
     */
    public static boolean isNetConnectionAvailable(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetInfo != null && activeNetInfo.isAvailable()) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public static boolean is3g2g(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null && activeNetInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            return true;
        }
        return false;
    }

    public static boolean isWifi(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }

    /**
     * 显示键盘
     *
     * @param mContext
     * @param v
     */
    public static void showKeyboard(Context mContext, View v) {
        v.requestFocus();
        ((InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE))
                .showSoftInput(v, 0);
    }

    /**
     * 隐藏键盘
     *
     * @param a
     */
    public static void hideKeyboard(final Activity a) {
        if (a == null || a.getCurrentFocus() == null)
            return;
        InputMethodManager inputManager = (InputMethodManager) a.getApplicationContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManager != null) {
            inputManager.hideSoftInputFromWindow(a.getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * EidtText控件显示错误信息
     * 参数以String传入
     *
     * @param et
     * @param error     字符串参数
     * @param animation
     */
    public static void showErrorByEditText(EditText et, String error, Animation animation) {
        et.requestFocus();
        SpannableString ss = new SpannableString(error);
        ss.setSpan(new ForegroundColorSpan(Color.BLACK), 0, error.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        et.setError(ss);
        et.startAnimation(animation);
    }

    /**
     * EditText控件显示错误信息
     * 参数以R.string.xxx传入
     *
     * @param et
     * @param resId     资源ID
     * @param animation
     */
    public static void showErrorByEditText(EditText et, int resId, Animation animation) {
        String error = et.getResources().getString(resId);
        et.requestFocus();

        SpannableString ss = new SpannableString(error);
        ss.setSpan(new ForegroundColorSpan(Color.BLACK), 0, error.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        et.setError(ss);
        et.startAnimation(animation);
    }

    /**
     * 隐藏EditText控件错误信息
     *
     * @param et
     * @param
     */
    public static void hideErrorByEditText(EditText et) {
        et.requestFocus();
        et.setError(null);
    }

    /**
     * 图片切换特效
     *
     * @param imageView
     */
    public static void showImageChange(ImageView imageView) {
        AlphaAnimation animation = new AlphaAnimation(0.1f, 1.0f);
        animation.setDuration(1000);
        imageView.startAnimation(animation);
    }

    /**
     * 正则匹配手机号码
     *
     * @param mobileNo
     * @return
     */
    public static boolean isMobileNoValid(String mobileNo) {
        String regExp = "^[1][0-9]{10}$";

        Pattern p = Pattern.compile(regExp);

        Matcher m = p.matcher(mobileNo);

        return m.find();

    }

    /**
     * 判断是否存在快捷方式
     *
     * @param ctx
     * @return
     */
    public static boolean hasShortcut(Context ctx, int app_name) {
        boolean isInstallShortcut = false;
        final ContentResolver cr = ctx.getContentResolver();
        final String AUTHORITY;
        // 在andriod 2.1即SDK7以上，是读取launcher.settings中的favorites表的数据；
        // 在andriod 2.2即SDK8以上，是读取launcher2.settings中的favorites表的数据。
        if (getSystemVersion() < 8) {
            AUTHORITY = "com.android.launcher.settings";
        } else {
            AUTHORITY = "com.android.launcher2.settings";
        }

        final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/favorites?notify=true");
        Cursor c = cr.query(CONTENT_URI, new String[]{"title", "iconResource"}, "title=?",
                new String[]{ctx.getString(app_name).trim()}, null);
        if (c != null && c.getCount() > 0) {
            isInstallShortcut = true;
        }
        return isInstallShortcut;
    }

    /**
     * 添加快捷方式
     *
     * @param
     */
    public static void createShortcut(Context ctx, int app_name, int icon, Class<?> clazz) {
        Intent shortcut = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        // 快捷方式的名称
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, ctx.getString(app_name));
        shortcut.putExtra("duplicate", false); // 不允许重复创建
        // 指定当前的Activity为快捷方式启动的对象: 如 com.everest.video.VideoPlayer
        // 注意: ComponentName的第二个参数必须加上点号(.)，否则快捷方式无法启动相应程序
        // String appClass = act.getPackageName() + "." + launchActivity;
        // ComponentName comp = new ComponentName(act.getPackageName(), clazz);
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT,
                new Intent(Intent.ACTION_MAIN).setClass(ctx, clazz));
        // 快捷方式的图标
        ShortcutIconResource iconRes = ShortcutIconResource.fromContext(ctx, icon);
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconRes);
        ctx.sendBroadcast(shortcut);
    }

    /**
     * 删除快捷方式
     *
     * @param
     */
    public static void removeShortcut(Context ctx, int app_name, Class<?> clazz) {
        Intent shortcut = new Intent("com.android.launcher.action.UNINSTALL_SHORTCUT");
        // 快捷方式的名称
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, ctx.getString(app_name));
        // 指定当前的Activity为快捷方式启动的对象: 如 com.everest.video.VideoPlayer
        // 注意: ComponentName的第二个参数必须是完整的类名（包名+类名），否则无法删除快捷方式
        // String appClass = act.getPackageName() + ".WelcomeIndexActivity";
        // ComponentName comp = new ComponentName(act.getPackageName(),
        // appClass);
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT,
                new Intent(Intent.ACTION_MAIN).setClass(ctx, clazz));
        ctx.sendBroadcast(shortcut);
    }

    /**
     * 返回系统SDK版本号
     *
     * @return
     */
    public static int getSystemVersion() {
        return android.os.Build.VERSION.SDK_INT;
    }

    /**
     * 获取网络制式
     *
     * @param context
     * @return
     */
    public static String getAccessType(Context context) {
        // 网络类型
        String access = "";
        ConnectivityManager connMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connMgr.getActiveNetworkInfo();
        if (info != null) {
            int netType = info.getType();
            if (netType == ConnectivityManager.TYPE_WIFI) {
                access = "wifi";
            } else if (netType == ConnectivityManager.TYPE_MOBILE) {
                access = "2G/3G";
            }
        }

        return access;
    }


    /**
     * 截屏(无状态栏)
     *
     * @param activity 被截屏的activity
     * @return 截下后图片的bitmap
     */
    @SuppressWarnings("deprecation")
    public static Bitmap shot(Activity activity) {
        Activity wisdowsActivity = activity;
        if (activity.getParent() != null) {
            wisdowsActivity = activity.getParent();
        }
        View view = wisdowsActivity.getWindow().getDecorView();
        Display display = wisdowsActivity.getWindowManager().getDefaultDisplay();
        int width = 0;
        int height = 0;
        if (CommonUtils.getSystemVersion() < 13) {
            width = display.getWidth();
            height = display.getHeight();
        } else {
            Point p = new Point();
            display.getSize(p);
            width = p.x;
            height = p.y;
        }

        view.layout(0, 0, width, height);
        view.setDrawingCacheEnabled(true);
        int statusBarHeight = AppHelper.getStatusBarHeight(activity);// 获取状态栏高度
        // 去除状态栏
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache(), 0, statusBarHeight, width,
                height - statusBarHeight);
        view.setDrawingCacheEnabled(false);
        return bitmap;
    }

    //判断是否为正确手机号码
    public static boolean isMobile(Context context, String mobile, boolean showToast) {
        if (StringUtil.isEmpty(mobile)) {
            //   Toast.makeText(context, "手机号不能为空", Toast.LENGTH_SHORT).show();
            if (showToast) {
                ToastUtil.showToast(context, "手机号不能为空");
            }
            return false;
        }
        //判断是否为正确手机号码格式
        if (!isMobileNoValid(mobile)) {
            if (showToast) {
                ToastUtil.showToast(context, "手机号输入有误");
            }

            // Toast.makeText(context, R.string.str_login_err_phone, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    //判断是否为正确手机号码
    public static boolean isMobile(Context context, String mobile) {
        return isMobile(context, mobile, true);
//        if (StringUtil.isEmpty(mobile)) {
//            //   Toast.makeText(context, "手机号不能为空", Toast.LENGTH_SHORT).show();
//            ToastUtil.showToast(context, "手机号不能为空");
//            return false;
//        }
//        //判断是否为正确手机号码格式
//        if (!isMobileNoValid(mobile)) {
//            ToastUtil.showToast(context, "手机号输入有误");
//
//            // Toast.makeText(context, R.string.str_login_err_phone, Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        return true;
    }


    //判断密码格式是否正常
    public static boolean isPassword(Context context, String password, boolean shoToast) {
        //判断验证码格式
        if (StringUtil.isEmpty(password)) {
            if (shoToast) {
                //    Toast.makeText(context, R.string.str_login_empty_pwd, Toast.LENGTH_SHORT).show();
                ToastUtil.showToast(context, "密码不能为空");
            }

            return false;
        }
        if (password.length() < 6) {
            //  CommonUtils.showErrorByEditText(et_pw, "密码不能少于6位数", shake);
            //       Toast.makeText(context, R.string.str_login_less6_pwd, Toast.LENGTH_SHORT).show();
            if (shoToast) {
                ToastUtil.showToast(context, "密码不能少于6位数");
            }

            return false;
        }
        return true;
    }

    //判断密码格式是否正常
    public static boolean isPassword(Context context, String password) {

        return isPassword(context, password, true);
        //判断验证码格式
//        if (StringUtil.isEmpty(password)) {
//            //    Toast.makeText(context, R.string.str_login_empty_pwd, Toast.LENGTH_SHORT).show();
//            ToastUtil.showToast(context, "密码不能为空");
//
//            return false;
//        }
//        if (password.length() < 6) {
//            //  CommonUtils.showErrorByEditText(et_pw, "密码不能少于6位数", shake);
//            //       Toast.makeText(context, R.string.str_login_less6_pwd, Toast.LENGTH_SHORT).show();
//            ToastUtil.showToast(context, "密码不能少于6位数");
//
//            return false;
//        }
//        return true;
    }

    //设置下划线
    public static void setTextUnderLine(TextView textView) {
        textView.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//设置textview下划线
    }

    //设置删除线
    public static void setTextStrinke(TextView textView) {
        textView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);//设置textview中划线
    }


    public static boolean idCode(String code) {

        String regExp = "[0-9]$";

        Pattern p = Pattern.compile(regExp);

        Matcher m = p.matcher(code);

        return m.find();


    }

    public static boolean idCode(Context mContext, String code) {
        if (StringUtil.isEmpty(code)) {
            ToastUtil.showToast(mContext, "验证码不能为空");
            return false;
        }

        if (idCode(code)) {
            return true;
        } else {
            ToastUtil.showToast(mContext, "验证码输入有误");
            return false;
        }


    }

}
