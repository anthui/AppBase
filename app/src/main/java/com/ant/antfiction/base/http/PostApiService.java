package com.ant.antfiction.base.http;

import com.ant.antfiction.utils.version.VersionBean;
import com.ant.http.Bean.HttpResult;
import com.ant.utils.bean.ShareBean;
import com.igexin.assist.MessageBean;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2018/11/14.
 * describe：
 */
public interface PostApiService {


    @FormUrlEncoded
    @POST(HttpConfig.VERSION_TYPE)
    Observable<HttpResult<VersionBean>> getCurrentVersion(@Field("type") String type);

    @FormUrlEncoded
    @POST(HttpConfig.GETUI_CLIENT_ID)
    Observable<HttpResult<Object>> upClientId(@Field("token") String clientid);



//    @POST(HttpConfig.WALLET_LIST)
//    Observable<HttpResult<WalletBean>> getWalletList();

    //发送验证码 0注册 1忘记密码  terminal  0：app 1:微信
//    @FormUrlEncoded
//    @POST(HttpConfig.GET_CODE)
//    Observable<HttpResult<String>> getCode(@Field("tel") String tel, @Field("scene") String codeType, @Field("terminal") String terminalType);
//    @FormUrlEncoded
//    @POST(HttpConfig.WALLET_CREATE_INIT)
//    Observable<HttpResult<String>> getCode(@FieldMap HashMap<String, String> hashMap);
//
//    @FormUrlEncoded
//    @POST(HttpConfig.WALLET_CREATE_INIT)
//    Observable<HttpResult<List<WalletListBean>>> getWalletData(@FieldMap HashMap<String, String> hashMap);
//
//    @FormUrlEncoded
//    @POST(HttpConfig.WALLET_TERMS_APPLY)
//    Observable<HttpResult<CopyTermBean>> getTerms(@Field("symbol") String title);
//
//    @FormUrlEncoded
//    @POST(HttpConfig.WALLET_ADDRESS_APPLY)
//    Observable<HttpResult<WalletBean>> applyAddress(@Field("id") String termsId, @Field("phase") String msg, @Field("symbol") String title);
//
////    @FormUrlEncoded
////    @POST(HttpConfig.WALLET_ZHUANG_ZHANG)
////    Observable<HttpResult<PayBean>> zhuanzhangAlipy(@FieldMap HashMap<String, String> hashMap);
////
////    @POST(HttpConfig.WALLET_GET_ASSET)
////    Observable<HttpResult<NewAssetBean>> getAsset();
////
////    @FormUrlEncoded
////    @POST(HttpConfig.WALLET_GET_TRANS_DETAIL)
////    Observable<HttpResult<TransDetailBean>> getTransDetail(@Field("transaction_sn") String transaction_sn);
////
////    @FormUrlEncoded
////    @POST(HttpConfig.WALLET_GET_TRANS_LIST)
////    Observable<HttpResult<TransListBean>> gettransList(@FieldMap HashMap<String, Object> hashMap);
////
////    @FormUrlEncoded
////    @POST(HttpConfig.WALLET_GET_TYPE)
////    Observable<HttpResult<TypeBean>> getType(@Field("wallet_sn") String wallet_sn);
//
//
////    @POST(HttpConfig.HOME_LIST_AD)
////    Observable<HttpResult<List<HomeListBean>>> getHomeList();
//
//    //创建钱包
//    @FormUrlEncoded
//    @POST(HttpConfig.CREATE_WALLET)
//    Observable<HttpResult<Object>> createWallet(@Field("symbol") String symbol);
//
//    //创建钱包
////    @FormUrlEncoded
////    @POST(HttpConfig.WALLET_FEE)
////    Observable<HttpResult<AssetFree>> getAsseFree(@Field("symbol") String symbo);
//
//    //创建钱包
//    @FormUrlEncoded
//    @POST(HttpConfig.TRANS_CHECK)
//    Observable<HttpResult<Object>> transCheck(@FieldMap HashMap<String, String> hashMap);
//
//    //创建钱包
////    @FormUrlEncoded
////    @POST(HttpConfig.TRANS_PAY)
////    Observable<HttpResult<PayBean>> transPay(@FieldMap HashMap<String, String> hashMap);
//
//    //修改支付密码
//    @FormUrlEncoded
//    @POST(HttpConfig.UP_PAY_PWD)
//    Observable<HttpResult<Object>> upPayPwd(@FieldMap HashMap<String, String> hashMap);
//
//    //修改支付密码
//    @FormUrlEncoded
//    @POST(HttpConfig.CODE_CHECK_PWD)
//    Observable<HttpResult<CodeCheckBean>> codeCheck(@FieldMap HashMap<String, String> hashMap);
//
//
//    @FormUrlEncoded
//    @POST(HttpConfig.CODE_RESET_PAY_PWD)
//    Observable<HttpResult<Object>> resetPayPwd(@FieldMap HashMap<String, String> hashMap);
//
//    @FormUrlEncoded
//    @POST(HttpConfig.PAY_PWD_CHECK)
//    Observable<HttpResult<CodeCheckBean>> pwdCheck(@Field("password") String pwd);

//    @POST(HttpConfig.VERIFICATION_CHECK)
//    Observable<HttpResult<VerificationStateBean>> checkVerificationState();
//
//    @FormUrlEncoded
//    @POST(HttpConfig.SUBMIT_VERIFICATION)
//    Observable<HttpResult<VerificationStateBean>> submitVerification(@FieldMap HashMap<String, String> hashMap);

    @FormUrlEncoded
    @POST(HttpConfig.UP_NIKE_NAME)
    Observable<HttpResult<Object>> upNikeName(@Field("nickname") String nikeName);
//
//    @POST(HttpConfig.GET_CONTENT_TERMS)
//    Observable<HttpResult<ContentBean>> getContentTerms();

//    @FormUrlEncoded
//    @POST(HttpConfig.ADDRESS_LIST)
//    Observable<HttpResult<AddressBean>> getAddressList(@Field("page") int pageIndex);

    @FormUrlEncoded
    @POST(HttpConfig.ADD_ADDRESS)
    Observable<HttpResult<Object>> addAddress(@FieldMap HashMap<String, String> hashMap);

    @FormUrlEncoded
    @POST(HttpConfig.DELE_ADDRESS)
    Observable<HttpResult<Object>> delete(@Field("id") String address_id);

    @FormUrlEncoded
    @POST(HttpConfig.UP_ADDRESS)
    Observable<HttpResult<Object>> upAddress(@FieldMap HashMap<String, String> hashMap);

//    @POST(HttpConfig.GET_HELPER)
//    Observable<HttpResult<List<HeplerBean>>> getHelper();
//
//    @FormUrlEncoded
//    @POST(HttpConfig.GET_HELPER_detail)
//    Observable<HttpResult<ContentBean>> getHelperDetail(@Field("id") String content_id);

    @FormUrlEncoded
    @POST(HttpConfig.GET_MESSAGE_LIST)
    Observable<HttpResult<MessageBean>> getMessageList(@Field("page") int page);

    @FormUrlEncoded
    @POST(HttpConfig.BIND_EMAIL)
    Observable<HttpResult<Object>> bindEmail(@FieldMap HashMap<String, String> hashMap);

//    @FormUrlEncoded
//    @POST(HttpConfig.CHECK_EMAIL)
//    Observable<HttpResult<CodeCheckBean>> checkEmail(@FieldMap HashMap<String, String> hashMap);
//

//    @POST(HttpConfig.WALLET_CREATE_INIT)
//    Observable<HttpResult<List<CurrencyBean>>> getTotalWalletList();
//
//    @FormUrlEncoded
//    @POST(HttpConfig.WALLET_HIDE)
//    Observable<HttpResult<Object>> hide(@Field("symbol") String symbol);
//
//    //创建钱包
//    @FormUrlEncoded
//    @POST(HttpConfig.WALLET_FEE)
//    Observable<HttpResult<AssetFree>> getAsseFree(@Field("symbol") String symbo);
//
//    @FormUrlEncoded
//    @POST(HttpConfig.TRANS_PAY)
//    Observable<HttpResult<PayBean>> transPay(@FieldMap HashMap<String, String> hashMap);
//
//    @FormUrlEncoded
//    @POST(HttpConfig.TRANS_DETAIL)
//    Observable<HttpResult<TransDetailBean>> getTransActionDetail(@Field("transaction_sn") String transaction_sn);
//
//    @FormUrlEncoded
//    @POST(HttpConfig.TRANS_LIST)
//    Observable<HttpResult<TranBean>> getTransActionList(@FieldMap HashMap<String, String> hashMap);
//
//    @FormUrlEncoded
//    @POST(HttpConfig.NOTIFY_LIST)
//    Observable<HttpResult<NotifyBean>> getNotifyList(@Field("page") int page);
//
//
//    @FormUrlEncoded
//    @POST("portal/mineral/mining")
//    Observable<HttpResult<GetMineraBean>> getMining(@Field("symbol") String paramString1, @Field("unique_code") String paramString2);

    @FormUrlEncoded
    @POST("portal/mineral/get-mineral-amount")
    Observable<HttpResult<ShareBean>> getNewMineral(@Field("unique_code") String paramString);

//    @POST(HttpConfig.HOME_INIT)
//    Observable<HttpResult<MIneraBean>> gerMineralList();

    @FormUrlEncoded
    @POST("portal/message/read")
    Observable<HttpResult<Object>> readMessage(@Field("id") String paramString);

//    @POST("portal/version/invite-friends")
//    Observable<HttpResult<MyShareBean>> getShareUrl();
//
//    @POST("portal/message/unread-count")
//    Observable<HttpResult<MessageNumBean>> getMessageNum();
//
//    @FormUrlEncoded
//    @POST(HttpConfig.FLASH_LSIT)
//    Observable<HttpResult<FlashListBean>> getFlashList(@Field("p") int page, @Field("t") int type);
//
//    @FormUrlEncoded
//    @POST(HttpConfig.FLASH_BILI)
//    Observable<HttpResult<BiliBean>> getBili(@Field("t") String symbol);
//
//    @FormUrlEncoded
//    @POST(HttpConfig.FLASH_TRANS_LIST)
//    Observable<HttpResult<TransBean>> getFlashTransList(@Field("p") int pageIndex);
//
//    @FormUrlEncoded
//    @POST(HttpConfig.FLASH_EXCHANGE)
//    Observable<HttpResult<Object>> buyOrSell(@FieldMap HashMap<String, String> hashMap);
//
//    @FormUrlEncoded
//    @POST(HttpConfig.FLASH_PENGDING)
//    Observable<HttpResult<PendingBean>> getPendingList(@Field("p") int pageIndex);

    @FormUrlEncoded
    @POST(HttpConfig.FLASH_CANCEL)
    Observable<HttpResult<Object>> cancle(@Field("mid") String id);

    @FormUrlEncoded
    @POST(HttpConfig.FLASH_EXCHANGE_BUY)
    Observable<HttpResult<Object>> exChange(@Field("n") String num, @Field("mid") String id);

//    @FormUrlEncoded
//    @POST(HttpConfig.FLASH_EXCHANGE_DETAIL)
//    Observable<HttpResult<ExChangeDetailBean>> getExchangeDetail(@Field("oid") String stringExtra);

    //    @FormUrlEncoded
    @POST(HttpConfig.ON_CHECK_IN)
    Observable<HttpResult<Object>> onCheckIn();

//    @POST(HttpConfig.HORSEPOWER_INIT)
//    Observable<HttpResult<HosepowderUpBean>> horsepowerInit();
//
//    //完成指定任务
//    @FormUrlEncoded
//    @POST(HttpConfig.FINISH_TASK)
//    Observable<HttpResult<FinishTaskBean>> finishTask(@FieldMap HashMap<String, String> type);
//
//    //完成指定任务
//    @FormUrlEncoded
//    @POST(HttpConfig.SUBMIT_VERIFICATION)
//    Observable<HttpResult<VerificationStateBean>> submitVerification(@FieldMap HashMap<String, String> hashMap);
//
//    @POST(HttpConfig.USER_INFO_INIT)
//    Observable<HttpResult<UserInfoBean>> userInfoInit();
//
//    @FormUrlEncoded
//    @POST(HttpConfig.HORSEPOWER_LIST)
//    Observable<HttpResult<Hoserposer>> getList(@Field("page") int pageIndex);
//
//    @POST(HttpConfig.GET_SHARE)
//    Observable<HttpResult<com.wallet.wdt.home.bean.MyShareBean>> getShare();
//
//    @FormUrlEncoded
//    @POST(HttpConfig.GET_REWART)
//    Observable<HttpResult<RewardBean>> getReward(@Field("page") int pageIndex);
//
//    @FormUrlEncoded
//    @POST(HttpConfig.GET_AD_LIST)
//    Observable<HttpResult<AdBean>> getAdList(@Field("page") int page);
//
//    @FormUrlEncoded
//    @POST(HttpConfig.GET_ZB_LIST)
//    Observable<HttpResult<AdBean>> getZbList(@Field("page") int page);
//
//    @FormUrlEncoded
//    @POST()
//    Observable<HttpResult<TeamBean>> getTeamList(@Url String url, @Field("page") int page);
//
//    @FormUrlEncoded
//    @POST(HttpConfig.GET_AD_DETAIL)
//    Observable<HttpResult<TeamBean.ListBean>> getTeamDetail(@Field("id") String id, @Field("type") int pageType);
//
//    @FormUrlEncoded
//    @POST(HttpConfig.GET_MAP_COMPANY)
//    Observable<HttpResult<BlockListBean>> getCompanyList(@Field("points_x") String lat, @Field("points_y") String lng);
//
//    @FormUrlEncoded
//    @POST(HttpConfig.UP_AUTH)
//    Observable<HttpResult<FinishTaskBean>> upAuth(@FieldMap HashMap<String, String> hashMap);
//
//    @POST(HttpConfig.ALIPAY_AUTH)
//    Observable<HttpResult<AliPayBean>> getAlipayAuth();
}
