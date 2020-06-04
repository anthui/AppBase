package com.ant.app_home.activity;

import com.ant.app_base.BaseBean;

/**
 * copyright：
 *
 * @author：anthui Version：1.0
 * creation date： 2020/6/2.
 * describe：
 */
public class MessageBean extends BaseBean {
    private String phoneNum;
    private String userName;
    private String message;

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
