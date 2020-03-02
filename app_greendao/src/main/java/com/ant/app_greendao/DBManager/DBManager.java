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
        long currentTimeMillis = System.currentTimeMillis();
        List<User> users = getUsersDao().loadAll();
        long l = System.currentTimeMillis() - currentTimeMillis;
        LogUtil.e("查找 数据" + users.size() + "  用时 " + l);
        return users;
    }


    /**
     * 插入或者更新数据表
     */
    public boolean insertOrUpdateUser(User users) {

        long l = getUsersDao().insertOrReplace(users);
        addNum++;
        LogUtil.e("insertOrUpdateUser  " + l);
        return true;
    }



    public void insertOrReplaceInTx(List<User> list) {

        long currentTimeMillis = System.currentTimeMillis();
        getUsersDao().insertOrReplaceInTx(list);
        long l = System.currentTimeMillis() - currentTimeMillis;
        LogUtil.e("插入 数据" + list.size() + "  用时 " + l);
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

    private static int addNum = 0;

    /**
     * 分页加载
     *
     * @param pageSize 当前第几页(程序中动态修改pageSize的值即可)
     * @param pageNum  每页显示多少个
     * @return
     */
    public List<User> queryPaging(int pageSize, int pageNum) {

        if (pageSize == 0) {
            addNum = 0;
        }
        UserDao studentDao = getUsersDao();
        List<User> listMsg = studentDao.queryBuilder().orderDesc(UserDao.Properties.RegisrTime)
                .offset(pageSize * pageNum + addNum).limit(pageNum).list();
        return listMsg;
    }

    /**
     * 分页加载
     *
     * @param pageIndex 当前第几页(程序中动态修改pageSize的值即可)
     * @param pageNum   每页显示多少个
     * @return 精确查询
     */
//    public List<User> queryPaging(int pageIndex, int pageNum) {
//        long currentTimeMillis = System.currentTimeMillis();
//        UserDao studentDao = getUsersDao();
//        List<User> listMsg = studentDao.queryBuilder().where(UserDao.Properties.UserName.eq("哈哈"), UserDao.Properties.StudentId.eq("333"))
//                .offset(pageIndex * pageNum).limit(pageNum).list();
//
//        long l = System.currentTimeMillis() - currentTimeMillis;
//        LogUtil.e("查询的时间为：" + l + "第" + pageIndex + " 页 共得到 " + listMsg.size() + " 条数据");
//        return listMsg;
//    }


}
