package com.ant.app_base.http;


import com.ant.app_base.dabaBase.table.UserBean;
import com.ant.app_base.user.UserSp;
import com.ant.app_http.Bean.HttpBaseConfig;
import com.ant.app_http.Bean.HttpResult;
import com.ant.app_http.HttpBaseAction;
import com.ant.app_http.listener.OnHttpRequestListener;

import io.reactivex.Observable;
import okhttp3.Request;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date：2018/7/19
 * describe： 网络请求 单利模式
 */
public class HttpManager extends HttpBaseAction {
    private static HttpManager httpManager;

    PostApiService postApiService;

    private HttpManager() {
        super(HttpBaseConfig.HttpConnectionTime, HttpBaseConfig.getHttpBaseUrl(), HttpBaseConfig.isHttpDebug);
        postApiService = retrofit.create(PostApiService.class);
    }

    public static HttpManager getHttpAction() {
        if (httpManager == null)
            synchronized (HttpManager.class) {
                if (httpManager == null)
                    httpManager = new HttpManager();
            }
        return httpManager;
    }


    /**
     * 添加公共参数 头部
     */
    @Override
    protected void addHead(Request.Builder builder) {
        super.addHead(builder);
        UserBean httpUserBean = UserSp.getUserBean();
        if (httpUserBean == null) {
            return;
        }
        builder.addHeader("token", httpUserBean.getUser_token() + "");
    }

    public void getCurrentVersion(OnHttpRequestListener onHttpRequestListener) {
        Observable<HttpResult<Object>> observable = postApiService.getCurrentVersion();
        enqueue(observable, onHttpRequestListener);

    }


}
