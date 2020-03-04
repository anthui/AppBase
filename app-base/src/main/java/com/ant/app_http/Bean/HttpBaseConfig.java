package com.ant.app_http.Bean;

import android.text.TextUtils;
import android.webkit.URLUtil;

import com.ant.app_base.BuildConfig;
import com.ant.app_base.config.AppBaseConfig;

/**
 * copyright：""
 * author：anthui
 * Version：1.0
 * creation date：2017/11/29
 * describe：所有网络请求 地址存放
 */
public class HttpBaseConfig extends AppBaseConfig {

    //******************************环境配置 start  修改 LOCAL_HOST就可*****************************
    //开发环境------走测试服务器
    public static final String LOCAL_HOST_DEV = "dev";
    //生产环境------正常多余
    public static final String LOCAL_HOST_BETA = "beta";
    //正式环境----走正式服务器
    public static final String LOCAL_HOST_API = "api";
    //本地环境切换，打包奇幻这个就可
    public static final String LOCAL_HOST = LOCAL_HOST_API;
    //******************************环境配置 end*****************************
    private static final long serialVersionUID = 1L;
    //默认超时时间 10秒
    public static int httpConnectionTime = 10;
    //文件传输 超时时间
    public static int httpFileConnectionTime = 15;
    //是否为debug 环境
    public static final boolean isHttpDebug = isDebug;

    public static final String URL_HOST;  //192.168.99.243:8104

    private static final String HOST_DEV = "http://dev.api.mgshop.shop/"; //测试域名
    private static final String HOST_BETA = "http://dev.api.mgshop.shop/";//预生产域名
    private static final String HOST_API = "https://api.mgshop.shop/";//正式域名
    /**
     * H5域名
     */
    public static final String H5_HOST;
    private static final String HOST_H5_DEV = "http://dev.100.mgshop.shop/"; //测试域名
    private static final String HOST_H5_BETA = "https://beta.100.mgshop.shop/";//预生产域名
    private static final String HOST_H5_API = "https://100.mgshop.shop/";//正式域名
    private static String host;
    public static String host_last = BuildConfig.HOST;

    static {
        //如果支持环境切换，优先获取缓存环境
        host = !TextUtils.isEmpty(BuildConfig.HOST) ? BuildConfig.HOST : LOCAL_HOST;
        //这种情况是环境为服务端本地ip地址，不支持H5
        if (URLUtil.isNetworkUrl(host)) {
            URL_HOST = host;
            H5_HOST = HOST_H5_DEV;
        } else {
            switch (host) {
                case "dev":
                    URL_HOST = HOST_DEV;
                    H5_HOST = HOST_H5_DEV;
                    break;
                case "beta":
                    URL_HOST = HOST_BETA;
                    H5_HOST = HOST_H5_BETA;
                    break;
                case "api":
                    URL_HOST = HOST_API;
                    H5_HOST = HOST_H5_API;
                    break;
                default:
                    URL_HOST = HOST_API;
                    H5_HOST = HOST_H5_API;
                    break;
            }
        }
    }


}
