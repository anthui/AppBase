package com.ant.anttestlibrary.DB.table;

import com.ant.app_database.db.DBBaseBean;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2019/8/8.
 * describe：
 */


@DatabaseTable(tableName = "user_bean")
public class UserBean extends DBBaseBean {

    @DatabaseField(columnName = "contry")
    private String country;

    @DatabaseField(columnName = "token")
    private String token;

    @DatabaseField(columnName = "nikename")
    private String nickname;

    @DatabaseField(columnName = "telephone")
    private String telephone;

    @DatabaseField(columnName = "username")
    private String username;


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getUser_name() {
        return username;
    }

    public void setUser_name(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "country='" + country + '\'' +
                ", token='" + token + '\'' +
                ", nickname='" + nickname + '\'' +
                ", telephone='" + telephone + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
