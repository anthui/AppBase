package com.ant.app_http;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;

import com.ant.app_base.BaseApplication;


/**
 * Created by jogger on 2018/5/25.控制中心
 */

public class Util {
    @SuppressLint("StaticFieldLeak")
    private static Context sApplication;

    public static void init(@NonNull final Context context) {
        init((Application) context.getApplicationContext());
    }

    public static void init(@NonNull final Application app) {
        if (sApplication == null) {
            sApplication = app;
        }

//        //初始化数据库
//        DBManager.initDB(app);
//        PushManager.initPush();
    }

    public static Context getApp() {
        if (sApplication == null) {
            sApplication = BaseApplication.getInstance();
        }
        return sApplication;


    }


    /**
     * 获取AppLication
     */
    public Context getApplication() {
        try {
            @SuppressLint("PrivateApi")
            Class<?> activityThread = Class.forName("android.app.ActivityThread");
            Object at = activityThread.getMethod("currentActivityThread").invoke(null);
            Object app = activityThread.getMethod("getApplication").invoke(at);
            if (app == null) {
                throw new NullPointerException("u should init first");
            }
//            init((Application) app);
            return (Context) app;
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new NullPointerException("u should init first");
    }

}
