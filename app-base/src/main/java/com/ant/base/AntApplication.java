package com.ant.base;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.support.multidex.MultiDex;

import com.ant.utils.language.LocalManageUtil;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date：2018/9/29
 * describe：
 */
public class AntApplication extends Application {


    private static Context instance;

    //当前选中的钱包

    public static Context getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        LocalManageUtil.setApplicationLanguage(this);

        //   LocalManageUtil.getSetLanguageLocale(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
//        super.attachBaseContext(base);
//        LocalManageUtil.saveSystemCurrentLanguage(base);

        LocalManageUtil.saveSystemCurrentLanguage(base);
        super.attachBaseContext(LocalManageUtil.setLocal(base));
        MultiDex.install(this);

    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //保存系统选择语言
        LocalManageUtil.onConfigurationChanged(getApplicationContext());
    }


}
