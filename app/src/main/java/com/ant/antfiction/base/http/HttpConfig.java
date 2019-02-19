package com.ant.antfiction.base.http;

import com.ant.http.Bean.HttpBaseConfig;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date：2017/11/29
 * describe：所有网络请求 地址存放
 */
public class HttpConfig extends HttpBaseConfig {

    private static final long serialVersionUID = 1L;

    public static final String WALLET_LIST = "portal/wallet/list";


//登录模块**********************

    //登录

    // 创建账号初始化
    public static final String WALLET_CREATE_INIT = "portal/currency/list";
    //申请助记词
    public static final String WALLET_TERMS_APPLY = "portal/phase/request";
    //地址申请 验证助记词
    public static final String WALLET_ADDRESS_APPLY = "portal/phase/generate";


    //  public static final String WALLET_ZHUANG_ZHANG = "portal/transaction/add";

    public static final String WALLET_GET_ASSET = "portal/wallet/list";
    public static final String WALLET_GET_TRANS_DETAIL = "portal/transaction/view";
    public static final String WALLET_GET_TRANS_LIST = "portal/transaction/history";
    public static final String WALLET_GET_TYPE = "portal/transaction/condition";
    public static final String WALLET_INPORT = "portal/wallet/import";


//登录模块**********************


    public static final String HOME_LIST_AD = "portal/content/home";
    public static final String CREATE_WALLET = "portal/wallet/create";
    public static final String WALLET_FEE = "portal/currency/fee";
    //验证 数据
    public static final String TRANS_CHECK = "portal/transaction/add-check";
    public static final String TRANS_PAY = "portal/transaction/add";
    public static final String UP_PAY_PWD = "portal/user/update-transaction-password";
    public static final String CODE_CHECK_PWD = "portal/user/reset-password-check";
    //支付密码 验证
    public static final String CODE_CHECK_PAY_PWD = "portal/user/reset-payment-check";
    public static final String CODE_RESET_PAY_PWD = "portal/user/reset-payment";
    //支付密码验证接口
    public static final String PAY_PWD_CHECK = "portal/user/validate-payment";
    //认证查询
    public static final String VERIFICATION_CHECK = "portal/verification/view";
    //图片上传
    public static final String VERFICATION_IMG = "portal/media/upload";
    public static final String SUBMIT_VERIFICATION = "portal/verification/submit";
    public static final String PIC_UP = "portal/user/avatar";
    public static final String UP_NIKE_NAME = "portal/user/nickname";
    //服务条款
    public static final String GET_CONTENT_TERMS = "portal/content/terms";
    //地址列表
    public static final String ADDRESS_LIST = "portal/address/list";
    //新增地址
    public static final String ADD_ADDRESS = "portal/address/create";
    //删除地址
    public static final String DELE_ADDRESS = "portal/address/delete";
    public static final String UP_ADDRESS = "portal/address/update";
    //帮助文档
    public static final String GET_HELPER = "portal/content/help";
    //帮助文档 详情
    public static final String GET_HELPER_detail = "portal/content/help-view";
    public static final String GET_MESSAGE_LIST = "portal/message/list";
    //邮箱 code
    public static final String USER_EMAIL_CODE = "portal/user/request-email";
    //绑定邮箱
    public static final String BIND_EMAIL = "portal/user/bind-email";
    //检查邮箱
    public static final String CHECK_EMAIL = "portal/user/bind-email-check";
    //个推
    public static final String GETUI_CLIENT_ID = "portal/user/push-token";
    //隐藏 钱包
    public static final String WALLET_HIDE = "portal/wallet/hide";
    //交易详情
    public static final String TRANS_DETAIL = "portal/transaction/view";
    //交易历史
    public static final String TRANS_LIST = "portal/transaction/history";
    public static final String NOTIFY_LIST = "portal/message/list";
    //版本升级
    public static final String VERSION_TYPE = "portal/version/index";
    //山兑列表
    public static final String FLASH_LSIT = "portal/speed-exchange/list";
    //获取比例
    public static final String FLASH_BILI = "portal/speed-exchange/wallet-balance";
    //获取比例
    public static final String FLASH_TRANS_LIST = "portal/speed-exchange/order-list";

    //交易 买入卖出
    public static final String FLASH_EXCHANGE = "portal/speed-exchange/place";

    //我的挂单
    public static final String FLASH_PENGDING = "portal/speed-exchange/my-market";
    //取消订单
    public static final String FLASH_CANCEL = "portal/speed-exchange/revoke-booth";
    //交易
    public static final String FLASH_EXCHANGE_BUY = "portal/speed-exchange/deal";
    //交易详情
    public static final String FLASH_EXCHANGE_DETAIL = "portal/speed-exchange/order-details";
    //首页数据初始化
    public static final String HOME_INIT = "portal/index/view";
    //签到
    public static final String ON_CHECK_IN = "portal/index/signin";
    //马力初始化
    public static final String HORSEPOWER_INIT = "portal/horsepower/index";
    //马力任务完成
    public static final String FINISH_TASK = "portal/index/finish-task";
    //用户数据初始化
    public static final String USER_INFO_INIT = "portal/verification/view";
    //马力提升记录
    public static final String HORSEPOWER_LIST = "portal/horsepower-log/list";
    //我的邀请
    public static final String GET_SHARE = "portal/invite/view";
    //我的奖励
    public static final String GET_REWART = "portal/invite/invite-log";
    //广告列表
    public static final String GET_AD_LIST = "portal/company/ads-list";
    public static final String GETE_TEAM_LIST = "portal/company/team";
    public static final String GETE_COMPANY_LIST = "portal/company/show";
    public static final String GET_AD_DETAIL = "portal/company/detail";
    public static final String GET_MAP_COMPANY = "portal/company/service";
    public static final String GET_ZB_LIST = "portal/company/live-list";
    public static final String UP_AUTH = "portal/index/create-oauth";


    public static final String ALIPAY_AUTH = "portal/index/alipay";
}
