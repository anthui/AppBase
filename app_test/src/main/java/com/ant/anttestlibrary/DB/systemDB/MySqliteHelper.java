package com.ant.anttestlibrary.DB.systemDB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2019/8/8.
 * describe：
 */
public class MySqliteHelper extends SQLiteOpenHelper {
    public MySqliteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //创建数据库sql语句 并 执行
        String sql = "create table user(name varchar(20))";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
