package com.ant.app_greendao.dataBean;


import com.ant.app_utils.LogUtil;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

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
    @Generated(hash = 586692638)
    public User() {
    }



    @Generated(hash = 1651310151)
    public User(long studentId, Long id, String userId, String userName,
            String nickName, String password, String avatar, String phone,
            long regisrTime, String token, String loginTime) {
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
        return "\n"+"userId='" + userId + '\'' +
                "regisrTime=" + regisrTime ;

    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return this.userId;
    }


    public void setUserId(String userId) {
        this.userId = userId;
        LogUtil.e("msg============3333333333333333333333====================");

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


    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }



}
