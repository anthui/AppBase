package com.ant.base.config;

/**
 * className:IntentConfig
 * author:"anthui"
 * date:2017/6/10
 * describe: intent传递参数 所有的页面跳转，参数都必须在此记录
 */
public class IntentConfig {
    //*******************************请求码requestCode****************************
    //登录页面的跳转
    public final static int REQUEST_CODE_LOGIN = 0;

    public final static int REQUEST_CODE_REGISTER = 1;

    //跳转城市页面
    public final static int REQUEST_CODE_PAY = 2;
    // 跳转 推广页面
    public final static int REQUEST_CODE_DEFAULT = 3;
    //跳转 选择视频页面
    public final static int REQUEST_CODE_VIDEO_CHECK = 4;
    //跳转 选择图片
    public static final int REQUEST_CODE_MYASSET = 5;

    public static final int REQUEST_CODE_PWD = 6;

    public static final int REQUEST_CODE_LANGUAGE = 7;
    public static final int REQUEST_CODE_CURRENCY = 8;
    public static final int REQUEST_CODE_USER_INFO = 9;
    public static final int REQUEST_CODE_UP_ADDRESS = 10;

    public static final int REQUEST_CODE_UP_ADDWALLET = 11;

    public static final int REQUEST_CODE_ZHUAN = 12;
    public static final int REQUEST_CODE_NOTIFY = 13;

    //*******************************返回码resultCode****************************

    //正确返回码
    public final static int RESULT_CODE_OK = 200;

    //********************************跳转参数************************************
    //用于判断是否登录 false为注册
    public final static String IS_LOGIN = "is_login";
    //传递用户手机号码
    public final static String USER_PHONE_NUM = "user_phone_num";
    //传递用户密码
    public final static String USER_PWD = "user_pwd";
    public static final String TITLE = "title";
    public static final String PRICE = "price";
    public static final String LEI_SHU = "lei_shu";
    public static final String NUM = "num";
    public static final String RED_ID = "red_id";
    public static final String WALLET_SN = "wallet_sn";
    public static final String WALLET_ADDRESS = "waller_address";
    public static final String WALLET_SYMBOL = "wallet_symbol";
    public static final String IMG_URL = "img_url";
    public static final String REMARK = "remark";
    public static final String OBJ_ASSET_LIST = "ob_asset_list";
    public static final String SHOW_ASSET = "show_asset";
    public static final String AUTH = "auth";

    //传递用户手机号码

    public static String OBJECT_DATA = "object_data";
    //需要的页面
    public static String PAGE_TYPE = "PAGE_TYPE";
    //页面id
    public static String PAGE_ID = "PAGE_ID";

    public static String TRANSACTION_TYPE = "type";


}
