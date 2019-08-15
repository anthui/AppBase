package com.ant.app_base.dabaBase.table;


import com.ant.app_base.user.UserDBConfig;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date：2017/11/27
 * describe：用户实体类
 */

@DatabaseTable(tableName = "user_bean")
public class UserBean implements Serializable {

    @DatabaseField(columnName = "user_nick_name")
    private String user_nick_name;

    @DatabaseField(columnName = "user_phone")
    private String user_phone;

    @DatabaseField(columnName = "user_name")
    private String user_name;

    @DatabaseField(columnName = "user_head")
    private String user_head;


    @DatabaseField(columnName = "user_id", id = true, canBeNull = false, unique = true)
    private String user_id;

    @DatabaseField(columnName = "user_token")
    private String user_token;

    //用户密码  自动登录可用
    @DatabaseField(columnName = "user_pwd")
    private String user_pwd;
    //最新用户
    @DatabaseField(columnName = UserDBConfig.columnName_user_now, dataType = DataType.BOOLEAN)
    private boolean is_new_login = false;


    public String getUser_nick_name() {
        return user_nick_name;
    }

    public void setUser_nick_name(String user_nick_name) {
        this.user_nick_name = user_nick_name;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_head() {
        return user_head;
    }

    public void setUser_head(String user_head) {
        this.user_head = user_head;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_token() {
        return user_token;
    }

    public void setUser_token(String user_token) {
        this.user_token = user_token;
    }

    public String getUser_pwd() {
        return user_pwd;
    }

    public void setUser_pwd(String user_pwd) {
        this.user_pwd = user_pwd;
    }

    public boolean isIs_new_login() {
        return is_new_login;
    }

    public void setIs_new_login(boolean is_new_login) {
        this.is_new_login = is_new_login;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "user_nick_name='" + user_nick_name + '\'' +
                ", user_phone='" + user_phone + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_head='" + user_head + '\'' +
                ", user_id='" + user_id + '\'' +
                ", user_token='" + user_token + '\'' +
                ", user_pwd='" + user_pwd + '\'' +
                "} " + super.toString();
    }
}
