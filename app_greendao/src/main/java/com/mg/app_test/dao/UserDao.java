package com.mg.app_test.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.ant.app_greendao.dataBean.User;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "USER".
*/
public class UserDao extends AbstractDao<User, String> {

    public static final String TABLENAME = "USER";

    /**
     * Properties of entity User.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property StudentId = new Property(0, long.class, "studentId", false, "STUDENT_ID");
        public final static Property Id = new Property(1, Long.class, "id", false, "ID");
        public final static Property UserId = new Property(2, String.class, "userId", true, "USER_ID");
        public final static Property UserName = new Property(3, String.class, "userName", false, "USER_NAME");
        public final static Property NickName = new Property(4, String.class, "nickName", false, "NICK_NAME");
        public final static Property Password = new Property(5, String.class, "password", false, "PASSWORD");
        public final static Property Avatar = new Property(6, String.class, "avatar", false, "AVATAR");
        public final static Property Phone = new Property(7, String.class, "phone", false, "PHONE");
        public final static Property RegisrTime = new Property(8, long.class, "regisrTime", false, "REGISR_TIME");
        public final static Property Token = new Property(9, String.class, "token", false, "TOKEN");
        public final static Property LoginTime = new Property(10, String.class, "loginTime", false, "LOGIN_TIME");
    }


    public UserDao(DaoConfig config) {
        super(config);
    }
    
    public UserDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USER\" (" + //
                "\"STUDENT_ID\" INTEGER NOT NULL ," + // 0: studentId
                "\"ID\" INTEGER," + // 1: id
                "\"USER_ID\" TEXT PRIMARY KEY NOT NULL ," + // 2: userId
                "\"USER_NAME\" TEXT," + // 3: userName
                "\"NICK_NAME\" TEXT," + // 4: nickName
                "\"PASSWORD\" TEXT," + // 5: password
                "\"AVATAR\" TEXT," + // 6: avatar
                "\"PHONE\" TEXT," + // 7: phone
                "\"REGISR_TIME\" INTEGER NOT NULL ," + // 8: regisrTime
                "\"TOKEN\" TEXT," + // 9: token
                "\"LOGIN_TIME\" TEXT);"); // 10: loginTime
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USER\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, User entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getStudentId());
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(2, id);
        }
 
        String userId = entity.getUserId();
        if (userId != null) {
            stmt.bindString(3, userId);
        }
 
        String userName = entity.getUserName();
        if (userName != null) {
            stmt.bindString(4, userName);
        }
 
        String nickName = entity.getNickName();
        if (nickName != null) {
            stmt.bindString(5, nickName);
        }
 
        String password = entity.getPassword();
        if (password != null) {
            stmt.bindString(6, password);
        }
 
        String avatar = entity.getAvatar();
        if (avatar != null) {
            stmt.bindString(7, avatar);
        }
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(8, phone);
        }
        stmt.bindLong(9, entity.getRegisrTime());
 
        String token = entity.getToken();
        if (token != null) {
            stmt.bindString(10, token);
        }
 
        String loginTime = entity.getLoginTime();
        if (loginTime != null) {
            stmt.bindString(11, loginTime);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, User entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getStudentId());
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(2, id);
        }
 
        String userId = entity.getUserId();
        if (userId != null) {
            stmt.bindString(3, userId);
        }
 
        String userName = entity.getUserName();
        if (userName != null) {
            stmt.bindString(4, userName);
        }
 
        String nickName = entity.getNickName();
        if (nickName != null) {
            stmt.bindString(5, nickName);
        }
 
        String password = entity.getPassword();
        if (password != null) {
            stmt.bindString(6, password);
        }
 
        String avatar = entity.getAvatar();
        if (avatar != null) {
            stmt.bindString(7, avatar);
        }
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(8, phone);
        }
        stmt.bindLong(9, entity.getRegisrTime());
 
        String token = entity.getToken();
        if (token != null) {
            stmt.bindString(10, token);
        }
 
        String loginTime = entity.getLoginTime();
        if (loginTime != null) {
            stmt.bindString(11, loginTime);
        }
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2);
    }    

    @Override
    public User readEntity(Cursor cursor, int offset) {
        User entity = new User( //
            cursor.getLong(offset + 0), // studentId
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // id
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // userId
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // userName
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // nickName
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // password
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // avatar
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // phone
            cursor.getLong(offset + 8), // regisrTime
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // token
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10) // loginTime
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, User entity, int offset) {
        entity.setStudentId(cursor.getLong(offset + 0));
        entity.setId(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setUserId(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setUserName(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setNickName(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setPassword(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setAvatar(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setPhone(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setRegisrTime(cursor.getLong(offset + 8));
        entity.setToken(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setLoginTime(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
     }
    
    @Override
    protected final String updateKeyAfterInsert(User entity, long rowId) {
        return entity.getUserId();
    }
    
    @Override
    public String getKey(User entity) {
        if(entity != null) {
            return entity.getUserId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(User entity) {
        return entity.getUserId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
