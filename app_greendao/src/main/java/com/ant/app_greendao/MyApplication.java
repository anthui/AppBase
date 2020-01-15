package com.ant.app_greendao;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.dao.DaoManager;
import com.mg.app_test.dao.DaoMaster;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2019/12/30.
 * describe：
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();


        //创建数据库名称
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "test.db");
        SQLiteDatabase writableDatabase = devOpenHelper.getWritableDatabase();


    }
}
