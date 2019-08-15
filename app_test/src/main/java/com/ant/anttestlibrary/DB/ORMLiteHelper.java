package com.ant.anttestlibrary.DB;

import android.content.Context;

import com.ant.anttestlibrary.DB.table.UserBean;
import com.ant.app_database.db.DatabaseHelper;

import java.util.List;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2019/8/8.
 * describe：
 */
public class ORMLiteHelper extends DatabaseHelper {
    private static ORMLiteHelper dbHelper;

    private ORMLiteHelper(Context context, String databaseName, int databaseVersion) {
        super(context, databaseName, databaseVersion);
    }

    @Override
    protected void updateTables(List<Class> dBtables) {
        dBtables.add(UserBean.class);
    }

    @Override
    protected void createTables(List<Class> dBtables) {
        dBtables.add(UserBean.class);
    }

    public static ORMLiteHelper getMyDataBaseHelper(Context context) {
        if (dbHelper == null) {
            dbHelper = new ORMLiteHelper(context, "ants.db", 1);
        }
        return dbHelper;
    }


}
