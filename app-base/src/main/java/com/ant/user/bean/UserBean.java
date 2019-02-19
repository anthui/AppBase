package com.ant.user.bean;


import com.ant.base.BaseBean;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date：2017/11/27
 * describe：用户实体类
 */
public class UserBean extends BaseBean {


    /**
     * email :
     * token : WhnTj0eAH6veBpN7
     * nickname : null
     * avatar :
     * telephone : 13055512558
     * last_login : 1539673736
     * country_code : 86
     * country : 中国大陆
     * username : imjoker
     */

    private String country;
    private String email;
    private String token;
    private String nickname;
    private boolean has_payment;
    private boolean is_subscribe;
    private String uid;
    private String avatar;
    private String telephone;
    private String last_login;
    private String country_code;
    private String username;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getLast_login() {
        return last_login;
    }

    public void setLast_login(String last_login) {
        this.last_login = last_login;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;

    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public boolean isIs_subscribe() {
        return is_subscribe;
    }

    public void setIs_subscribe(boolean is_subscribe) {
        this.is_subscribe = is_subscribe;
    }

    public boolean isHas_payment() {
        return has_payment;
    }

    public void setHas_payment(boolean has_payment) {
        this.has_payment = has_payment;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
