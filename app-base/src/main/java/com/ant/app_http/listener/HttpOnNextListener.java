package com.ant.app_http.listener;

import com.ant.app_http.Bean.ErrBean;
import com.ant.app_http.Bean.TokenInfoBean;

/**
 * copyright：""
 * author：anthui
 * Version：1.0
 * creation date：2018/7/23
 * describe：网络 接口监听
 */
public interface HttpOnNextListener<T> {

    //成功 后的接口
    void onSuccess(T t, TokenInfoBean tokenInfoBean);

    void onFailure(ErrBean errBean);

}
