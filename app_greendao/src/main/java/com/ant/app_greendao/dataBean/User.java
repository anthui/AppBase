package com.ant.app_greendao.dataBean;

import com.mg.app_test.dao.DaoSession;
import com.mg.app_test.dao.StudentDao;
import com.mg.app_test.dao.UserDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.NotNull;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2020/2/20.
 * describe：
 */

@Entity
public class User {

    private long studentId;
    @ToOne(joinProperty = "studentId")
    private Student student;
    private Long id;
    @Id()
    private String userId;
    private String userName;//不可更改的
    private String nickName;//可以更改
    private String password;
    private String avatar;
    private String phone;
    private long regisrTime;
    private String token;
    private String loginTime;
    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /**
     * Used for active entity operations.
     */
    @Generated(hash = 1507654846)
    private transient UserDao myDao;
    @Generated(hash = 79695740)
    private transient Long student__resolvedKey;


    @Generated(hash = 586692638)
    public User() {
    }


    @Generated(hash = 1651310151)
    public User(long studentId, Long id, String userId, String userName, String nickName,
            String password, String avatar, String phone, long regisrTime, String token,
            String loginTime) {
        this.studentId = studentId;
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.nickName = nickName;
        this.password = password;
        this.avatar = avatar;
        this.phone = phone;
        this.regisrTime = regisrTime;
        this.token = token;
        this.loginTime = loginTime;
    }


    @Override
    public String toString() {
        return "User{" +
                "studentId=" + studentId +
                ", student=" + student +
                ", id=" + id +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", password='" + password + '\'' +
                ", avatar='" + avatar + '\'' +
                ", phone='" + phone + '\'' +
                ", regisrTime=" + regisrTime +
                ", token='" + token + '\'' +
                ", loginTime='" + loginTime + '\'' +
                ", daoSession=" + daoSession +
                ", myDao=" + myDao +
                ", student__resolvedKey=" + student__resolvedKey +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return this.userId;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getUserName() {
        return this.userName;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getNickName() {
        return this.nickName;
    }


    public void setNickName(String nickName) {
        this.nickName = nickName;
    }


    public String getPassword() {
        return this.password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public String getAvatar() {
        return this.avatar;
    }


    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }


    public String getPhone() {
        return this.phone;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }


    public long getRegisrTime() {
        return this.regisrTime;
    }


    public void setRegisrTime(long regisrTime) {
        this.regisrTime = regisrTime;
    }


    public String getToken() {
        return this.token;
    }


    public void setToken(String token) {
        this.token = token;
    }


    public String getLoginTime() {
        return this.loginTime;
    }


    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public Long getStudentId() {
        return this.studentId;
    }


    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }


    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1299365531)
    public Student getStudent() {
        long __key = this.studentId;
        if (student__resolvedKey == null || !student__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            StudentDao targetDao = daoSession.getStudentDao();
            Student studentNew = targetDao.load(__key);
            synchronized (this) {
                student = studentNew;
                student__resolvedKey = __key;
            }
        }
        return student;
    }


    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1384923540)
    public void setStudent(@NotNull Student student) {
        if (student == null) {
            throw new DaoException(
                    "To-one property 'studentId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.student = student;
            studentId = student.getId();
            student__resolvedKey = studentId;
        }
    }


    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }


    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }


    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }


    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 2059241980)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getUserDao() : null;
    }


    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }



}
