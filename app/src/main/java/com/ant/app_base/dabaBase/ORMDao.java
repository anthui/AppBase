package com.ant.app_base.dabaBase;

import android.content.Context;

import com.ant.app_database.db.BaseDao;
import com.ant.app_database.db.DatabaseHelper;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2019/8/8.
 * describe：数据库 Dao 需要对某一个类进行操作时，直接 new就可
 */
public class ORMDao<T> extends BaseDao<T> {
    public ORMDao(Class<T> clazz) {
        super(clazz);
    }


    @Override
    protected DatabaseHelper getDataBaseHeplper(Context context) {
        return ORMLiteHelper.getMyDataBaseHelper(context);
    }
}
