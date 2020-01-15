package com.mg.app_test.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.ant.app_greendao.dataBean.Student;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "STUDENT".
*/
public class StudentDao extends AbstractDao<Student, Long> {

    public static final String TABLENAME = "STUDENT";

    /**
     * Properties of entity Student.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property StudentNo = new Property(1, int.class, "studentNo", false, "STUDENT_NO");
        public final static Property Age = new Property(2, int.class, "age", false, "AGE");
        public final static Property Name = new Property(3, int.class, "name", false, "NAME");
        public final static Property Sex = new Property(4, int.class, "sex", false, "SEX");
        public final static Property Address = new Property(5, String.class, "address", false, "ADDRESS");
        public final static Property SchoolName = new Property(6, String.class, "schoolName", false, "SCHOOL_NAME");
        public final static Property Grade = new Property(7, String.class, "grade", false, "GRADE");
    }


    public StudentDao(DaoConfig config) {
        super(config);
    }
    
    public StudentDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"STUDENT\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"STUDENT_NO\" INTEGER NOT NULL UNIQUE ," + // 1: studentNo
                "\"AGE\" INTEGER NOT NULL ," + // 2: age
                "\"NAME\" INTEGER NOT NULL ," + // 3: name
                "\"SEX\" INTEGER NOT NULL ," + // 4: sex
                "\"ADDRESS\" TEXT," + // 5: address
                "\"SCHOOL_NAME\" TEXT," + // 6: schoolName
                "\"GRADE\" TEXT);"); // 7: grade
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"STUDENT\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Student entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getStudentNo());
        stmt.bindLong(3, entity.getAge());
        stmt.bindLong(4, entity.getName());
        stmt.bindLong(5, entity.getSex());
 
        String address = entity.getAddress();
        if (address != null) {
            stmt.bindString(6, address);
        }
 
        String schoolName = entity.getSchoolName();
        if (schoolName != null) {
            stmt.bindString(7, schoolName);
        }
 
        String grade = entity.getGrade();
        if (grade != null) {
            stmt.bindString(8, grade);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Student entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getStudentNo());
        stmt.bindLong(3, entity.getAge());
        stmt.bindLong(4, entity.getName());
        stmt.bindLong(5, entity.getSex());
 
        String address = entity.getAddress();
        if (address != null) {
            stmt.bindString(6, address);
        }
 
        String schoolName = entity.getSchoolName();
        if (schoolName != null) {
            stmt.bindString(7, schoolName);
        }
 
        String grade = entity.getGrade();
        if (grade != null) {
            stmt.bindString(8, grade);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Student readEntity(Cursor cursor, int offset) {
        Student entity = new Student( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getInt(offset + 1), // studentNo
            cursor.getInt(offset + 2), // age
            cursor.getInt(offset + 3), // name
            cursor.getInt(offset + 4), // sex
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // address
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // schoolName
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7) // grade
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Student entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setStudentNo(cursor.getInt(offset + 1));
        entity.setAge(cursor.getInt(offset + 2));
        entity.setName(cursor.getInt(offset + 3));
        entity.setSex(cursor.getInt(offset + 4));
        entity.setAddress(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setSchoolName(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setGrade(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Student entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Student entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Student entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
