package com.ant.base.config;

import android.content.Context;

import com.ant.base.AntApplication;
import com.ant.base.BuildConfig;
import com.ant.base.R;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2018/10/22.
 * describe：
 */
public class AppConfig {

    //所有debug控制
    public static final boolean isDebug = BuildConfig.IS_DEBUG;
    public static final boolean isHttpDebug = isDebug;

    //应用包名
    public final static String APP_NAME_WDT = "com.wallet.wdts";
    public final static String APP_NAME_INDEX = "com.wallet.indexs";
    public final static String APP_NAME_HQGX = "com.wallet.hqgx";


    public static final String getAuthority(Context mContext) {
        return mContext.getApplicationContext().getPackageName() + ".fileProvider";
    }

    public static String getLoginActivityName() {
        return "com.wallet.wdt.loginModule.activity.LoginActivity";
    }

    /**
     * 获取应用名称
     */
    public static String getAppName(Context mContext) {
        switch (AntApplication.getInstance().getPackageName()) {
            case APP_NAME_WDT:
                return mContext.getString(R.string.app_wdt);

            case APP_NAME_INDEX:
                return mContext.getString(R.string.app_index);

            case APP_NAME_HQGX:
                return mContext.getString(R.string.app_name);
            default:
        }
        return "";

    }


}
