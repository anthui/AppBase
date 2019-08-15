//package com.ant.anttestlibrary.DB.systemDB;
//
//import android.content.ContentValues;
//import android.database.sqlite.SQLiteDatabase;
//import android.os.Bundle;
//
//import com.ant.anttestlibrary.R;
//import com.ant.app_base.BaseActivity;
//
///**
// * copyright：
// * author：anthui
// * Version：1.0
// * creation date： 2019/8/8.
// * describe：
// */
//public class DefaultSqliteActivity extends BaseActivity {
//    @Override
//    protected void initComponents(Bundle savedInstanceState) {
//
//
//        MySqliteHelper mySqliteHelper = new MySqliteHelper(mContext, "ddd.db", null, 1);
//
//        SQLiteDatabase sqLiteDatabase = mySqliteHelper.getWritableDatabase();
//        sqLiteDatabase.execSQL("");
//
//
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("name", "哈哈");
//        sqLiteDatabase.insert("user", null, contentValues);
//
//        sqLiteDatabase.beginTransaction();
//
//
//    }
//
//    @Override
//    public void initData() {
//
//    }
//
//    @Override
//    public int getMainContentViewId() {
//        return R.layout.activity_home;
//    }
//}
