package com.ant.app_base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.multidex.MultiDex;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date：2018/9/29
 * describe：
 * BaseApplication
 * 主要处理
 * 1、注册异常信息
 * 2、dex分包
 * 3、语言（正常用不到）
 */
public  class BaseApplication extends Application {

    private static BaseApplication instance;

    public static BaseApplication getInstance() {
        return instance;
    }



    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
        //语言切换
//        LocalManageUtil.setApplicationLanguage(this);
        //注册异常信息
        registerException();

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        //语言切换
//        LocalManageUtil.saveSystemCurrentLanguage(base);
//        super.attachBaseContext(LocalManageUtil.setLocal(base));
        //分包
        MultiDex.install(this);

    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //保存系统选择语言
//        LocalManageUtil.onConfigurationChanged(getApplicationContext());
    }


    /**
     * 注册全局异常处理类
     */
    private void registerException() {
        AppExceptionHelper.getInstance().register(this);

    }

}
