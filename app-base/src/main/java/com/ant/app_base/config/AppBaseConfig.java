package com.ant.app_base.config;

import android.content.Context;

import com.ant.app_base.BuildConfig;
import com.ant.app_base.R;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2018/10/22.
 * describe：所有配置文件的顶级父类
 */
public class AppBaseConfig {

    //所有debug控制
    public static final boolean isDebug = BuildConfig.IS_DEBUG;

    //应用包名
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

        return mContext.getString(R.string.app_name);

    }


}
