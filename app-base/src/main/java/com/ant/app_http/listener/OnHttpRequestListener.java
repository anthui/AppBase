package com.ant.app_http.listener;

/**
 * copyright：""
 * author：anthui
 * Version：1.0
 * creation date：2018/7/18
 * describe： 统一接口回调  单独创建，为日后某些接口做单独处理 做储备
 */
public abstract class OnHttpRequestListener<T> implements HttpOnNextListener<T> {

//
//    @Override
//    public void onSuccess(T t, TokenInfoBean tokenInfoBean) {
//
//    }
//
//    @Override
//    public void onFailure(ErrBean errBean) {
//
//    }

    /**
     * 进入
     */

    public void onProgressChange(int progress) {

    }
}
