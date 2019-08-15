package com.ant.app_database.db;

import android.content.Context;

import com.ant.app_base.BaseApplication;
import com.ant.app_utils.LogUtil;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedDelete;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.PreparedUpdate;

import java.security.InvalidParameterException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 数据库CRUD操作的Dao，子类继承实现抽象方法，也提供一个简单的泛型实现类
 * <p>
 * Date:2015-08-26
 * Time: 12:25
 */
public abstract class BaseDao<T> {

    protected DatabaseHelper mDatabaseHelper;


    //helper
    protected Context mContext;

    Class<T> clazz;

    //上下文
    public BaseDao(Class<T> clazz) {
        this.clazz = clazz;
        mContext = BaseApplication.getInstance();
        //避免产生内存泄露，使用getApplicationContext()
        mDatabaseHelper = getDataBaseHeplper(mContext);
        //获得单例helper
    }

    //mDatabaseHelper 为单利模式，所以这里不管创建几个对象，一个类型获取的dao都是唯一的
    private static HashMap<Class, Dao> hashMap = new HashMap<>();


    public Dao<T, Integer> getDao() throws SQLException {

        Dao dao = hashMap.get(clazz);
        LogUtil.e("getDao=====" + hashMap.size());
        if (dao == null) {
            dao = mDatabaseHelper.getDao(clazz);
            hashMap.put(clazz, dao);
            LogUtil.e("getDao  创建dao ");
        } else {
            LogUtil.e("getDao  获取dao ");

        }

        return dao;
//
//        Dao<T, ?> dao = mDatabaseHelper.getDao(clazz);
//
//
//        return mDatabaseHelper.getDao(clazz);
    }
    // public abstract Dao<T, Integer> getDao() throws SQLException;

    protected abstract DatabaseHelper getDataBaseHeplper(Context context);

    /**
     * 抽象方法，重写提供Dao,在BaseDaoImpl里提供了简单的泛型实现，传递实体类Class即可
     *
     * @param t 泛型实体类
     * @return 影响的行数
     * @throws SQLException SQLException异常
     *                      <p>
     *                      /**
     *                      增，带事务操作
     * @throws SQLException SQLException异常
     */
    public int insert(T t) {//Dao<T, Integer> dao = getDao();

        try {
            return DBManager.getDaManager().insert(t, getDao());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    /**
     * 增或更新，带事务操作
     *
     * @param t 泛型实体类
     * @return Dao.CreateOrUpdateStatus
     * @throws SQLException SQLException异常
     */
    public Dao.CreateOrUpdateStatus insertOrUpdate(T t) {
        try {
            return DBManager.getDaManager().insertOrUpdate(t, getDao());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 增，带事务操作
     *
     * @param t 泛型实体类集合
     * @return 影响的行数
     * @throws SQLException SQLException异常
     */
    public int insert(List<T> t) {


        try {
            return DBManager.getDaManager().insert(t, getDao());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;

    }

    /**
     * 删，带事务操作
     *
     * @param t 泛型实体类
     * @return 影响的行数
     * @throws SQLException SQLException异常
     */
    public int delete(T t) throws SQLException {

        return DBManager.getDaManager().delete(t, getDao());
    }

    /**
     * 删，带事务操作
     *
     * @param list 泛型实体类集合
     * @return 影响的行数
     * @throws SQLException SQLException异常
     */
    public int delete(List<T> list) {
        try {
            return DBManager.getDaManager().delete(list, getDao());
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return 0;
    }

    /**
     * 删，带事务操作
     *
     * @param columnNames  列名数组
     * @param columnValues 列名对应值数组
     * @return 影响的行数
     * @throws SQLException              SQLException异常
     * @throws InvalidParameterException InvalidParameterException异常
     */
    public int delete(String[] columnNames, Object[] columnValues) throws SQLException, InvalidParameterException {
        return DBManager.getDaManager().delete(columnNames, columnValues, getDao());

    }

    /**
     * 删，带事务操作
     *
     * @param id id值
     * @return 影响的行数
     * @throws SQLException SQLException异常
     */
    public int deleteById(int id) throws SQLException {
        return DBManager.getDaManager().deleteById(id, getDao());

    }

    /**
     * 删，带事务操作
     *
     * @param ids id集合
     * @return 影响的行数
     * @throws SQLException SQLException异常
     */
    public int deleteByIds(List<Integer> ids) throws SQLException {
        return DBManager.getDaManager().deleteByIds(ids, getDao());
//        return 0;
    }

    /**
     * 删，带事务操作
     *
     * @param preparedDelete PreparedDelete类
     * @return 影响的行数
     * @throws SQLException SQLException异常
     */
    public int delete(PreparedDelete<T> preparedDelete) throws SQLException {

        return DBManager.getDaManager().delete(preparedDelete, getDao());
    }

    /**
     * 改，带事务操作
     *
     * @param t 泛型实体类
     * @return 影响的行数
     * @throws SQLException SQLException异常
     */
    public int update(T t) {

        try {
            return DBManager.getDaManager().update(t, getDao());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;

    }

    /**
     * 改，带事务操作
     *
     * @param preparedUpdate PreparedUpdate对象
     * @return 影响的行数
     * @throws SQLException SQLException异常
     */
    public int update(PreparedUpdate<T> preparedUpdate) throws SQLException {
        return DBManager.getDaManager().update(preparedUpdate, getDao());
    }

    /**
     * 查，带事务操作
     *
     * @return 查询结果集合
     * @throws SQLException SQLException异常
     */
    public List<T> queryForAll() {
        try {
            return DBManager.getDaManager().queryForAll(getDao());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 查，带事务操作
     *
     * @param preparedQuery PreparedQuery对象
     * @return 查询结果集合
     * @throws SQLException SQLException异常
     */
    public List<T> query(PreparedQuery<T> preparedQuery) throws SQLException {
        return DBManager.getDaManager().query(preparedQuery, getDao());
    }

    /**
     * 查，带事务操作
     *
     * @param columnName  列名
     * @param columnValue 列名对应值
     * @return 查询结果集合
     * @throws SQLException SQLException异常
     */
    public List<T> query(String columnName, Object columnValue) {

        try {
            return DBManager.getDaManager().query(columnName, columnValue, getDao());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 查，带事务操作
     *
     * @param columnNames
     * @param columnValues
     * @return 查询结果集合
     * @throws SQLException SQLException异常
     */
    public List<T> query(String[] columnNames, Object[] columnValues) throws SQLException {

        return DBManager.getDaManager().query(columnNames, columnValues, getDao());

    }

    /**
     * 查，带事务操作
     *
     * @param map 列名与值组成的map
     * @return 查询结果集合
     * @throws SQLException SQLException异常
     */
    public List<T> query(Map<String, Object> map) throws SQLException {
        return DBManager.getDaManager().query(map, getDao());
    }

    /**
     * 查，带事务操作
     *
     * @param id id值
     * @return 查询结果集合
     * @throws SQLException SQLException异常
     */
    public T queryById(Object id) {
        if (id == null) {
            return null;
        }

        try {
            return DBManager.getDaManager().queryById(id, getDao());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 判断表是否存在
     *
     * @return 表是否存在
     * @throws SQLException SQLException异常
     */
    public boolean isTableExists() throws SQLException {
        return getDao().isTableExists();
    }


    /**
     * 获得记录数
     *
     * @return 记录数
     * @throws SQLException SQLException异常
     */
    public long count() throws SQLException {
        return DBManager.getDaManager().count(getDao());
    }

    /**
     * 获得记录数
     *
     * @param preparedQuery PreparedQuery类
     * @return 记录数
     * @throws SQLException SQLException异常
     */
    public long count(PreparedQuery<T> preparedQuery) throws SQLException {
        return DBManager.getDaManager().count(preparedQuery, getDao());
    }
}
