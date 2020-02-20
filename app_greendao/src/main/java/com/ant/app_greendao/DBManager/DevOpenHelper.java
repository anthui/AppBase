package com.ant.app_greendao.DBManager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.mg.app_test.dao.DaoMaster;
import com.mg.app_test.dao.UserDao;

import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.StandardDatabase;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2020/2/20.
 * describe：数据库 OpenHelper
 */
public class DevOpenHelper extends DaoMaster.DevOpenHelper {

    DevOpenHelper(Context context, String name) {
        super(context, name, null);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        DaoMaster.dropAllTables(new StandardDatabase(db), true);
        onCreate(db);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        if (oldVersion >= newVersion) {
            return;
        }
        // 这边添加要升级的表
        MigrationHelper.migrate((StandardDatabase) db,
                UserDao.class
        );
    }
}
