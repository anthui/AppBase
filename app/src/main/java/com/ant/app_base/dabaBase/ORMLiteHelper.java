package com.ant.app_base.dabaBase;

import android.content.Context;

import com.ant.app_database.db.DatabaseHelper;
import com.ant.app_base.dabaBase.table.UserBean;

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

    private ORMLiteHelper(Context context) {
        super(context, "ants.db", 1);
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
            dbHelper = new ORMLiteHelper(context);
        }
        return dbHelper;
    }


}
