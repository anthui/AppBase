package com.ant.app_base;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import androidx.multidex.MultiDex;

import cat.ereza.customactivityoncrash.config.CaocConfig;

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
public class BaseApplication extends Application {

    private static BaseApplication instance;

    public static BaseApplication getInstance() {
        return instance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
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
//        AppExceptionHelper.getInstance().register(this);
        defaultCrash();

    }

    /**
     * @author：anthui creation date：2020/5/11
     * describe：初始化 错误监听
     */
    public void defaultCrash() {
        CaocConfig.Builder.create()
                .backgroundMode(CaocConfig.BACKGROUND_MODE_SILENT) //背景模式,开启沉浸式
                .enabled(true) //是否启动全局异常捕获
                .showErrorDetails(true) //是否显示错误详细信息
                .showRestartButton(true) //是否显示重启按钮
                .trackActivities(true) //是否跟踪Activity
                .minTimeBetweenCrashesMs(2000) //崩溃的间隔时间(毫秒)
                .errorDrawable(R.mipmap.bg_load) //错误图标
//                .restartActivity(LoginActivity.class) //重新启动后的activity
//                .errorActivity(YourCustomErrorActivity.class) //崩溃后的错误activity
//                .eventListener(new YourCustomEventListener()) //崩溃后的错误监听
                .apply();
    }

}
