package com.ant.app_base.config;

/**
 * className:IntentBaseConfig
 * author:"anthui"
 * date:2017/6/10
 * describe: intent传递参数 所有的页面跳转，参数都必须在此记录
 * <p>
 * 这里存放 页面跳转默认的参数
 */
public class IntentBaseConfig extends AppBaseConfig {
    //*******************************请求码requestCode****************************

    // 默认的 跳转
    public final static int REQUEST_CODE_DEFAULT = 1000;


    //*******************************返回码resultCode****************************

    //正确返回码
    public final static int RESULT_CODE_OK = 200;

    //********************************跳转参数************************************

    //默认传递的 参数名称 key
    public static String KEY_OBJECT_DATA = "object_data";
    //默认传递的 页面类型 key
    public static String KEY_PAGE_TYPE = "PAGE_TYPE";


}
