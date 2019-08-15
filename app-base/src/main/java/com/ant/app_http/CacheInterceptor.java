package com.ant.app_http;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Jogger on 2018/6/10.
 * 缓存拦截器
 * <p>
 * 当前缓存 使用的是 okHttp自带的缓存Cache-Control，目前只适用于Get请求
 * post请求目前还不支持： 目的在于 post请求的参数 会变动，如需要 可以另外做数据库、文件缓存等方式
 * <p>
 * 当前为啥不做post请求-----主要是用不到，从get和post请求理解
 * get方式 主要适用于 查询数据，列表等---基本不会变化的数据
 * post方式 主要适用于 修改数据、信息等---参数会一直变化，不适合做缓存处理
 */

public class CacheInterceptor implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {
        //获取请求体
        Request request = chain.request();
        boolean networkAvailable = isNetworkAvailable();
        if (!networkAvailable) {
            //没有网络时 重新构建请求体
            request = request.newBuilder()
                    //从缓存中获取请求数据
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }
        //返回体
        Response response = chain.proceed(request);
        //重新构建返回体数据
        if (networkAvailable) {
            int maxAge = 60 * 60; // read from cache for 1 minute
            response.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .build();
        } else {
            int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
            response.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .build();
        }
        return response;
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) Util.getApp()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        assert cm != null;
        NetworkInfo current = cm.getActiveNetworkInfo();
        return current != null && (current.isAvailable());
    }
}
