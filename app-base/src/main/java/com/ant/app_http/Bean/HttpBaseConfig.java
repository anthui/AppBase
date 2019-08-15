package com.ant.app_http.Bean;

import com.ant.app_base.config.AppBaseConfig;

/**
 * copyright：""
 * author：anthui
 * Version：1.0
 * creation date：2017/11/29
 * describe：所有网络请求 地址存放
 */
public class HttpBaseConfig extends AppBaseConfig {
    private static final long serialVersionUID = 1L;
    //默认超时时间
    public static int HttpConnectionTime = 10;
    //文件传输 超时时间
    public static int HttpFileConnectionTime = 15;
    //是否为debug 环境
    public static final boolean isHttpDebug = isDebug;


    //    //基础域名---测试版本
    private static String BASE_URL_DEBUG = "https://api.fox.boonw.com/";

    private static String BASE_URL_RELEASE = "https://api.fox.boonw.com/";

    public static String getHttpBaseUrl() {
        if (AppBaseConfig.isDebug) {
            return BASE_URL_DEBUG;
        } else {
            return BASE_URL_RELEASE;
        }
    }

}
