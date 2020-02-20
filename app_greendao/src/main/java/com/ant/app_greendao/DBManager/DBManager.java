package com.ant.app_greendao.DBManager;

import android.database.sqlite.SQLiteDatabase;

import com.ant.app_base.BaseApplication;
import com.ant.app_greendao.dataBean.Student;
import com.ant.app_greendao.dataBean.User;
import com.ant.app_utils.LogUtil;
import com.mg.app_test.dao.DaoMaster;
import com.mg.app_test.dao.DaoSession;
import com.mg.app_test.dao.UserDao;

import java.util.List;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2020/2/20.
 * describe：
 */
public class DBManager {
    private static final String DB_NAME = "ImDb";
    private final SQLiteDatabase mWritableDatabase;
    private final DaoMaster mDaoMaster;
    private final DaoSession _daoSession;

    private DBManager() {
        mWritableDatabase = new DevOpenHelper(BaseApplication.getInstance(), DB_NAME).getWritableDatabase();
        mDaoMaster = new DaoMaster(mWritableDatabase);
        _daoSession = mDaoMaster.newSession();
    }

    public static DBManager getInstance() {
        return Instance.instance;
    }

    private static class Instance {
        static DBManager instance = new DBManager();
    }

    private UserDao getUsersDao() {
        return _daoSession.getUserDao();
    }

    //=============================用户表数据========================

    /**
     * 获取所有用户信息
     */
    public List<User> loadAllUserList() {
        List<User> users = getUsersDao().loadAll();
        return users;
    }


    /**
     * 插入或者更新数据表
     */
    public boolean insertOrUpdateUser(User users) {
        long l = getUsersDao().insertOrReplace(users);
        LogUtil.e("insertOrUpdateUser  " + l);
        return true;
    }

    /**
     * 插入或者更新数据表
     */
    public boolean insertOrUpdateStudent(Student users) {
        long l = _daoSession.getStudentDao().insertOrReplace(users);
        LogUtil.e("insertOrUpdateUser  " + l);
        return true;
    }



    public void update(User users) {
        getUsersDao().update(users);
    }

    public void deleteAllUser() {

        getUsersDao().deleteAll();

    }



    public void saveUser(User user) {
        getUsersDao().save(user);
    }

    public void deleteUser(User users) {
        getUsersDao().delete(users);

    }


}
