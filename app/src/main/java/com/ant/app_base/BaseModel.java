package com.ant.app_base;


import android.content.Context;

import com.ant.app_base.http.HttpManager;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2018/11/19.
 * describe：
 */
public class BaseModel {

    public HttpManager httpManager;

    public BaseModel(Context context) {
        httpManager = HttpManager.getHttpAction();

    }
}
