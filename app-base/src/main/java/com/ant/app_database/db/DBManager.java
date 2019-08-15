package com.ant.app_database.db;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedDelete;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.PreparedUpdate;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.DatabaseConnection;

import java.security.InvalidParameterException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date：2018/9/29
 * describe：
 */
public class DBManager {

    private static DBManager dbManager;

    public static DBManager getDaManager() {
        if (dbManager == null) {
            dbManager = new DBManager();
        }
        return dbManager;

    }

    //********************************插入**********************

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
    public <T> int insert(T t, Dao dao) throws SQLException {
        //   Dao<T, Integer> dao = getDao();

        DatabaseConnection databaseConnection = null;
        try {
            databaseConnection = dao.startThreadConnection();
            dao.setAutoCommit(databaseConnection, false);
            int save = dao.create(t);
            dao.commit(databaseConnection);
            return save;
        } catch (SQLException e) {
            dao.rollBack(databaseConnection);
            e.printStackTrace();
        } finally {
            dao.endThreadConnection(databaseConnection);
        }
        return 0;
    }

    /**
     * 增，带事务操作
     *
     * @param t 泛型实体类集合
     * @return 影响的行数
     * @throws SQLException SQLException异常
     */
    public <T> int insert(List<T> t, Dao dao) throws SQLException {

        delete(t, dao);

        DatabaseConnection databaseConnection = null;
        try {
            databaseConnection = dao.startThreadConnection();
            dao.setAutoCommit(databaseConnection, false);
            for (T item : t) {
                dao.create(item);
            }
            dao.commit(databaseConnection);
            return t.size();
        } catch (SQLException e) {
            dao.rollBack(databaseConnection);
            e.printStackTrace();
        } finally {
            dao.endThreadConnection(databaseConnection);
        }
        return 0;
    }


    /**
     * 增或更新，带事务操作
     *
     * @param t 泛型实体类
     * @return Dao.CreateOrUpdateStatus
     * @throws SQLException SQLException异常
     */
    public <T> Dao.CreateOrUpdateStatus insertOrUpdate(T t, Dao dao) throws SQLException {
        // Dao<T, Integer> dao = getDao();
        DatabaseConnection databaseConnection = null;
        try {
            databaseConnection = dao.startThreadConnection();
            dao.setAutoCommit(databaseConnection, false);
            Dao.CreateOrUpdateStatus orUpdate = dao.createOrUpdate(t);
            dao.commit(databaseConnection);
            return orUpdate;
        } catch (SQLException e) {
            dao.rollBack(databaseConnection);
            e.printStackTrace();
        } finally {
            dao.endThreadConnection(databaseConnection);
        }
        return null;
    }


    //*************************************查找*****************************

    /**
     * 查，带事务操作
     *
     * @return 查询结果集合
     * @throws SQLException SQLException异常
     */
    public <T> List<T> queryForAll(Dao dao) throws SQLException {

        DatabaseConnection databaseConnection = null;
        try {
            databaseConnection = dao.startThreadConnection();
            dao.setAutoCommit(databaseConnection, false);
            List<T> query = dao.queryForAll();

            dao.commit(databaseConnection);
            return query;
        } catch (SQLException e) {
            dao.rollBack(databaseConnection);
            e.printStackTrace();
        } finally {
            dao.endThreadConnection(databaseConnection);
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
    public <T> List<T> query(PreparedQuery preparedQuery, Dao dao) throws SQLException {

        DatabaseConnection databaseConnection = null;
        try {
            databaseConnection = dao.startThreadConnection();
            dao.setAutoCommit(databaseConnection, false);
            List<T> query = dao.query(preparedQuery);
            dao.commit(databaseConnection);
            return query;
        } catch (SQLException e) {
            dao.rollBack(databaseConnection);
            e.printStackTrace();
        } finally {
            dao.endThreadConnection(databaseConnection);
        }
        return null;
    }

    /**
     * 查，带事务操作
     *
     * @param columnName  列名
     * @param columnValue 列名对应值
     * @return 查询结果集合
     * @throws SQLException SQLException异常
     */
    public <T> List<T> query(String columnName, Object columnValue, Dao dao) throws SQLException {
        QueryBuilder<T, Integer> queryBuilder = dao.queryBuilder();
        queryBuilder.where().eq(columnName, columnValue);
        PreparedQuery<T> preparedQuery = queryBuilder.prepare();
        //    Dao<T, Integer> dao = getDao();
        DatabaseConnection databaseConnection = null;
        try {
            databaseConnection = dao.startThreadConnection();
            dao.setAutoCommit(databaseConnection, false);
            List<T> query = dao.query(preparedQuery);
            //also can use dao.queryForEq(columnName,columnValue);
            dao.commit(databaseConnection);
            return query;
        } catch (SQLException e) {
            dao.rollBack(databaseConnection);
            e.printStackTrace();
        } finally {
            dao.endThreadConnection(databaseConnection);
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
    public <T> List<T> query(String[] columnNames, Object[] columnValues, Dao dao) throws SQLException {
        if (columnNames.length != columnNames.length) {
            throw new InvalidParameterException("params size is not equal");
        }
        QueryBuilder<T, Integer> queryBuilder = dao.queryBuilder();
        Where<T, Integer> wheres = queryBuilder.where();
        for (int i = 0; i < columnNames.length; i++) {
            if (i == 0) {
                wheres.eq(columnNames[i], columnValues[i]);
            } else {
                wheres.and().eq(columnNames[i], columnValues[i]);
            }

        }
        PreparedQuery<T> preparedQuery = queryBuilder.prepare();
        DatabaseConnection databaseConnection = null;
        try {
            databaseConnection = dao.startThreadConnection();
            dao.setAutoCommit(databaseConnection, false);
            List<T> query = dao.query(preparedQuery);
            dao.commit(databaseConnection);
            return query;
        } catch (SQLException e) {
            dao.rollBack(databaseConnection);
            e.printStackTrace();
        } finally {
            dao.endThreadConnection(databaseConnection);
        }
        return null;
    }


    /**
     * 查，带事务操作
     *
     * @param map 列名与值组成的map
     * @return 查询结果集合
     * @throws SQLException SQLException异常
     */
    public <T> List<T> query(Map<String, Object> map, Dao dao) throws SQLException {
        QueryBuilder<T, Integer> queryBuilder = dao.queryBuilder();
        if (!map.isEmpty()) {
            Where<T, Integer> wheres = queryBuilder.where();
            Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
            String key = null;
            Object value = null;
            for (int i = 0; iterator.hasNext(); i++) {
                Map.Entry<String, Object> next = iterator.next();
                key = next.getKey();
                value = next.getValue();
                if (i == 0) {
                    wheres.eq(key, value);
                } else {
                    wheres.and().eq(key, value);
                }
            }
        }
        PreparedQuery<T> preparedQuery = queryBuilder.prepare();
        DatabaseConnection databaseConnection = null;
        try {
            databaseConnection = dao.startThreadConnection();
            dao.setAutoCommit(databaseConnection, false);
            List<T> query = dao.query(preparedQuery);
            dao.commit(databaseConnection);
            return query;
        } catch (SQLException e) {
            dao.rollBack(databaseConnection);
            e.printStackTrace();
        } finally {
            dao.endThreadConnection(databaseConnection);
        }
        return null;
    }

    /**
     * 查，带事务操作
     *
     * @param id id值
     * @return 查询结果集合
     * @throws SQLException SQLException异常
     */
    public <T> T queryById(Object id, Dao dao) throws SQLException {
        DatabaseConnection databaseConnection = null;
        try {
            databaseConnection = dao.startThreadConnection();
            dao.setAutoCommit(databaseConnection, false);
            T t = null;
            Object o = dao.queryForId(id);
            if (o != null) {

                t = (T) o;
            }
            dao.commit(databaseConnection);
            return t;
        } catch (SQLException e) {
            dao.rollBack(databaseConnection);
            e.printStackTrace();
        } finally {
            dao.endThreadConnection(databaseConnection);
        }
        return null;
    }


    //**********************************查找 记录数*************************

    /**
     * 获得记录数
     *
     * @return 记录数
     * @throws SQLException SQLException异常
     */
    public <T> long count(Dao dao) throws SQLException {
        //Dao<T, Integer> dao = getDao();
        DatabaseConnection databaseConnection = null;
        try {
            databaseConnection = dao.startThreadConnection();
            dao.setAutoCommit(databaseConnection, false);
            long count = dao.countOf();
            dao.commit(databaseConnection);
            return count;
        } catch (SQLException e) {
            dao.rollBack(databaseConnection);
            e.printStackTrace();
        } finally {
            dao.endThreadConnection(databaseConnection);
        }
        return 0;
    }

    /**
     * 获得记录数
     *
     * @param preparedQuery PreparedQuery类
     * @return 记录数
     * @throws SQLException SQLException异常
     */
    public <T> long count(PreparedQuery<T> preparedQuery, Dao dao) throws SQLException {
        DatabaseConnection databaseConnection = null;
        try {
            databaseConnection = dao.startThreadConnection();
            dao.setAutoCommit(databaseConnection, false);

            long count = dao.countOf(preparedQuery);
            dao.commit(databaseConnection);
            return count;
        } catch (SQLException e) {
            dao.rollBack(databaseConnection);
            e.printStackTrace();
        } finally {
            dao.endThreadConnection(databaseConnection);
        }
        return 0;
    }
    //**********************************查找 更新记录*************************

    /**
     * 改，带事务操作
     *
     * @param preparedUpdate PreparedUpdate对象
     * @return 影响的行数
     * @throws SQLException SQLException异常
     */
    public <T> int update(PreparedUpdate<T> preparedUpdate, Dao dao) throws SQLException {
        //Dao<T, Integer> dao = getDao();
        DatabaseConnection databaseConnection = null;
        try {
            databaseConnection = dao.startThreadConnection();
            dao.setAutoCommit(databaseConnection, false);
            int update = dao.update(preparedUpdate);
            dao.commit(databaseConnection);
            return update;
        } catch (SQLException e) {
            dao.rollBack(databaseConnection);
            e.printStackTrace();
        } finally {
            dao.endThreadConnection(databaseConnection);
        }
        return 0;
    }


    /**
     * 改，带事务操作
     *
     * @param t 泛型实体类
     * @return 影响的行数
     * @throws SQLException SQLException异常
     */
    public <T> int update(T t, Dao dao) throws SQLException {


        DatabaseConnection databaseConnection = null;
        try {
            databaseConnection = dao.startThreadConnection();
            dao.setAutoCommit(databaseConnection, false);
            int update = dao.update(t);
            dao.commit(databaseConnection);
            return update;
        } catch (SQLException e) {
            dao.rollBack(databaseConnection);
            e.printStackTrace();
        } finally {
            dao.endThreadConnection(databaseConnection);
        }
        return 0;
    }


    //**************************************删除***************************

    /**
     * 删，带事务操作
     *
     * @param t 泛型实体类
     * @return 影响的行数
     * @throws SQLException SQLException异常
     */
    public <T> int delete(T t, Dao dao) throws SQLException {
        //  Dao<T, Integer> dao = getDao();
        DatabaseConnection databaseConnection = null;
        try {
            databaseConnection = dao.startThreadConnection();
            dao.setAutoCommit(databaseConnection, false);
            int delete = dao.delete(t);
            dao.commit(databaseConnection);
            return delete;
        } catch (SQLException e) {
            dao.rollBack(databaseConnection);
            e.printStackTrace();
        } finally {
            dao.endThreadConnection(databaseConnection);
        }
        return 0;
    }


    /**
     * 删，带事务操作
     *
     * @param list 泛型实体类集合
     * @return 影响的行数
     * @throws SQLException SQLException异常
     */
    public <T> int delete(List<T> list, Dao dao) throws SQLException {
        // Dao<T, Integer> dao = getDao();
        DatabaseConnection databaseConnection = null;
        try {
            databaseConnection = dao.startThreadConnection();
            dao.setAutoCommit(databaseConnection, false);
            int delete = dao.delete(list);
            dao.commit(databaseConnection);
            return delete;
        } catch (SQLException e) {
            dao.rollBack(databaseConnection);
            e.printStackTrace();
        } finally {
            dao.endThreadConnection(databaseConnection);
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
    public <T> int delete(String[] columnNames, Object[] columnValues, Dao dao) throws SQLException, InvalidParameterException {
        List<T> list = query(columnNames, columnValues, dao);
        if (null != list && !list.isEmpty()) {
            //  Dao<T, Integer> dao = getDao();
            DatabaseConnection databaseConnection = null;
            try {
                databaseConnection = dao.startThreadConnection();
                dao.setAutoCommit(databaseConnection, false);
                int delete = dao.delete(list);
                dao.commit(databaseConnection);
                return delete;
            } catch (SQLException e) {
                dao.rollBack(databaseConnection);
                e.printStackTrace();
            } finally {
                dao.endThreadConnection(databaseConnection);
            }
        }
        return 0;
    }


    /**
     * 删，带事务操作
     *
     * @param id id值
     * @return 影响的行数
     * @throws SQLException SQLException异常
     */
    public <T> int deleteById(Integer id, Dao dao) throws SQLException {
        //     Dao<T, Integer> dao = getDao();
        DatabaseConnection databaseConnection = null;
        try {
            databaseConnection = dao.startThreadConnection();
            dao.setAutoCommit(databaseConnection, false);
            int delete = dao.deleteById(id);
            dao.commit(databaseConnection);
            return delete;
        } catch (SQLException e) {
            dao.rollBack(databaseConnection);
            e.printStackTrace();
        } finally {
            dao.endThreadConnection(databaseConnection);
        }
        return 0;
    }

    /**
     * 删，带事务操作
     *
     * @param ids id集合
     * @return 影响的行数
     * @throws SQLException SQLException异常
     */
    public <T> int deleteByIds(List<Integer> ids, Dao dao) throws SQLException {

        DatabaseConnection databaseConnection = null;
        try {
            databaseConnection = dao.startThreadConnection();
            dao.setAutoCommit(databaseConnection, false);
            int delete = dao.deleteIds(ids);
            dao.commit(databaseConnection);
            return delete;
        } catch (SQLException e) {
            dao.rollBack(databaseConnection);
            e.printStackTrace();
        } finally {
            dao.endThreadConnection(databaseConnection);
        }
        return 0;
    }

    /**
     * 删，带事务操作
     *
     * @param preparedDelete PreparedDelete类
     * @return 影响的行数
     * @throws SQLException SQLException异常
     */
    public <T> int delete(PreparedDelete<T> preparedDelete, Dao dao) throws SQLException {
        DatabaseConnection databaseConnection = null;
        try {
            databaseConnection = dao.startThreadConnection();
            dao.setAutoCommit(databaseConnection, false);
            int delete = dao.delete(preparedDelete);
            dao.commit(databaseConnection);
            return delete;
        } catch (SQLException e) {
            dao.rollBack(databaseConnection);
            e.printStackTrace();
        } finally {
            dao.endThreadConnection(databaseConnection);
        }
        return 0;
    }
}
