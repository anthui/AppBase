package com.ant.app_database.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.ant.app_utils.LogUtil;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Helper类，提供单例Helper
 * User:
 * Date:2015-08-26
 * Time: 12:04
 */
public abstract class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private List<Class> DBtables = new ArrayList();

    public DatabaseHelper(Context context, String databaseName, int databaseVersion) {
        super(context, databaseName, null, databaseVersion);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {

        LogUtil.e("onCreate 数据库创建");
        try {
            DBtables.clear();

            createTables(DBtables);
            for (Class table : DBtables) {
                TableUtils.createTable(connectionSource, table);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, final ConnectionSource connectionSource, int oldVersion, int newVersion) {

        LogUtil.e("onCreate 数据库升级");

        try {
            TransactionManager.callInTransaction(connectionSource, new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    DBtables.clear();
                    updateTables(DBtables);
                    try {
                        for (Class table : DBtables) {
                            TableUtils.dropTable(connectionSource, table, true);
                            TableUtils.createTable(connectionSource, table);
                        }
                    } catch (SQLException e) {
                        return false;
                    }
                    return true;
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected abstract void updateTables(List<Class> dBtables);

    protected abstract void createTables(List<Class> dBtables);


}
