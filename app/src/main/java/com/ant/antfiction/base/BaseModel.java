package com.ant.antfiction.base;


import com.ant.antfiction.base.http.HttpManager;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2018/11/19.
 * describe：
 */
public class BaseModel {

    public HttpManager httpManager;

    public BaseModel() {
        httpManager = HttpManager.getHttpAction();
    }
}
