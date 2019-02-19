package com.ant.http.Bean;

import com.ant.base.config.AppConfig;

import java.io.Serializable;

/**
 * copyright：haoxin
 * author：anthui
 * Version：1.0
 * creation date：2017/11/29
 * describe：所有网络请求 地址存放
 */
public class HttpBaseConfig implements Serializable {
    private static final long serialVersionUID = 1L;
    //默认超时时间
    public static int HttpConnectionTime = 10;
    //文件传输 超时时间
    public static int HttpFileConnectionTime = 15;
    //是否为debug 环境
    public static boolean isDebug = AppConfig.isDebug;
    //是否为网络测试环境环境
//    public static final boolean isHttpDebug = AppConfig.isHttpDebug;
    //基础域名---正式版本
    private static String BASE_URL_WDT = "https://api.wdt.one/";
    //    //基础域名---测试版本
    private static String BASE_URL_WDT_DEBUG = "http://wdt.bxguo.net/";
    //基础域名---正式版本
    private static String BASE_URL_INDEX = "http://index.bxguo.net/";
    //    //基础域名---测试版本
    private static String BASE_URL_INDEX_DEBUG = "http://index.bxguo.net/";
    //基础域名---正式版本
    private static String BASE_URL_HQGX = "http://hqgx.bxguo.net/";
    //    //基础域名---测试版本
    private static String BASE_URL_HQGX_DEBUG = "http://hqgx.bxguo.net/";


    public static String getHttpBaseUrl() {


        if (AppConfig.isHttpDebug) {
            return BASE_URL_HQGX_DEBUG;
        } else {
            return BASE_URL_HQGX;
        }

//
    }

}
