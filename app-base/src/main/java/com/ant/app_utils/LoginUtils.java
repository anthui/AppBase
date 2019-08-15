//package com.ant.utils;
//
//import android.text.TextUtils;
//import android.util.Log;
//import android.widget.Toast;
//
//import com.ant.base.BaseActivity;
//
//import java.io.UnsupportedEncodingException;
//import java.net.URLEncoder;
//import java.util.HashMap;
//
//import cn.sharesdk.framework.Platform;
//import cn.sharesdk.framework.PlatformActionListener;
//import cn.sharesdk.framework.ShareSDK;
//import cn.sharesdk.sina.weibo.SinaWeibo;
//import cn.sharesdk.tencent.qq.QQ;
//import cn.sharesdk.wechat.friends.Wechat;
//
//public class LoginUtils implements PlatformActionListener {
//
//
//    public final static String LOGIN_QQ = "QQ";
//    public final static String LOGIN_WX = "WX";
//    public final static String LOGIN_WB = "WB";
//    //   private SignPopupWindow popwindow = null;
//    private String loginType = "";//默认为0 正常情况
//    private final BaseActivity mContext;
//
//    public LoginUtils(BaseActivity context) {
//        mContext = context;
//    }
//
//
//    //========================
////执行授权,获取用户信息
//
//    public void login(String loginType) {
//
//        this.loginType = loginType;
//
//
//        //统计第三方登录按钮点击量
////
//        Platform plat = null;
//        switch (loginType) {
//            case LOGIN_QQ:
//                plat = ShareSDK.getPlatform(QQ.NAME);
//                break;
//
//            case LOGIN_WX:
//                plat = ShareSDK.getPlatform(Wechat.NAME);
//                if (!plat.isClientValid()) {
//                    Toast.makeText(mContext, "请先安装微信", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                break;
//            case LOGIN_WB:
//                plat = ShareSDK.getPlatform(SinaWeibo.NAME);
//                break;
//        }
//        if (plat != null) {
//            if (plat.isAuthValid()) {
//                plat.removeAccount(true);
//            }
//            Log.e("msg", "authorize: =============ko");
//            plat.setPlatformActionListener(this);
//            //true不使用SSO授权，false使用SSO授权
//            plat.SSOSetting(false);
//            plat.showUser(null);
//
//            //    mContext.showProgressDialog(mContext.getString(R.string.str_login));
//        }
//    }
//
//
//    @Override
//    public void onComplete(Platform platform, int action, HashMap<String, Object> res) {
//
//
//        if (action == Platform.ACTION_USER_INFOR) {
//            //登录成功,获取需要的信息
//            Log.e("msg", "onComplete: 正在登入中" + res.toString());
//            login(platform);
//        }
//    }
//
//    @Override
//    public void onError(Platform platform, int action, Throwable t) {
//        //   mContext.dismissProgressDialog();
//        //  new AntToast(mContext).ShowFail("登录失败");
//        LogUtil.e("onError+登录失败");
//
////        if (action == Platform.ACTION_USER_INFOR) {
////          //  Log.e("msg", "onError: 登录错误");
////            new AntToast(mContext).ShowFail("登录失败");
////
////          //  MyDialogFail dialogFail = new MyDialogFail();
////         //   dialogFail.showLoadingDialog(mContext, "登录失败", MyDialogFail.LNEG_SHOW);
////        }
//        t.printStackTrace();
//    }
//
//    @Override
//    public void onCancel(Platform platform, int action) {
//        //   mContext.dismissProgressDialog();
//        if (action == Platform.ACTION_USER_INFOR) {
//            Log.e("msg", "onCancel: 取消登录");
//        }
//    }
//
//    private void login(Platform platform) {
//
//        // Map<String, String> map = new HashMap<String, String>();
//        String nickName = platform.getDb().get("nickname");
//        nickName = encodeUnicode(nickName);
//
//        String city = platform.getDb().get("city");
//
//        String unionid = platform.getDb().get("unionid");
//        if (!TextUtils.isEmpty(unionid)) {
//            //   map.put("unionid", unionid);
//        } else {
//            unionid = platform.getDb().getUserId();
//            // map.put("unionid", unionid);
//
//        }
//        String province = platform.getDb().get("province");
//       /* if (!TextUtils.isEmpty(province)) {
//         //   map.put("province", province);
//        }*/
//        String gender = platform.getDb().get("gender");
//        String sex = "";
////性别，１男，２女，０保密
//        if (gender.equals("1") || gender.equals("2")) {
//            sex = gender;
//        } else {
//            sex = "0";
//        }
//        //        if (gender equals("0")){
//        //
//        //        }
//     /*   if (!TextUtils.isEmpty(sex)) {
//            map.put("sex", sex);
//        }*/
//        String headimgurl = platform.getDb().get("icon");
//       /* if (!TextUtils.isEmpty(headimgurl)) {
//            map.put("headimgurl", headimgurl);
//        }*/
//        String openid = platform.getDb().get("openid");
//        if (!TextUtils.isEmpty(openid)) {
//            //   map.put("openid", openid);
//        } else {
//            openid = platform.getDb().getUserId();
//            // map.put("openid", openid);
//
//        }
//        String type = platform.getDb().getPlatformNname().equalsIgnoreCase("wechat") ? "weixin" : platform.getDb().getPlatformNname().equalsIgnoreCase("qq") ? "qq" : "sina";
////
////        new HttpManage(mContext)
////                .getApi(ApiService.class)
////                .loginThird(nickName, city, unionid, province, sex, headimgurl, openid, type)
////                .enqueue(new retrofit2.Callback<PersonBean>() {
////                    @Override
////                    public void onResponse(Call<PersonBean> call, Response<PersonBean> response) {
////                        mContext.dismissProgressDialog();
////                        //   Log.e(TAG, "onResponse: ", );
////                        if (response.body().getErrno().equals("200")) {
////
////                            PersonBean userBean = response.body();
////
////                            UserSp.clearUserDbData(mContext);
////                            UserSp.saveUser(mContext, userBean.getData());
////
////                            if (StringUtil.isEmpty(loginType)) {
////                                MobclickAgent.onProfileSignIn("userID");//友盟统计账号
////                            } else {
////                                MobclickAgent.onProfileSignIn(loginType, "userID");//友盟统计账号
////                            }
////
////                            mContext.setResult(200);
////                            mContext.showToast(response.body().getData().getPoint());
////                            // UIHandler.sendEmptyMessage(MSG_AUTH_COMPLETE, LoginActivity.this);
////
////                            if (mContext instanceof JingJuActivity) {
////                                if (popwindow != null) {
////
////                                    popwindow.setLName(userBean.getData().getUser_avatar(), userBean.getData().getUser_name());
////                                }
////                                return;
////                            }
////                            if (popwindow != null) {
////                                popwindow.dismiss();
////                                if (mContext instanceof NewHomeActivity) {
////                                    NewHomeActivity activity = (NewHomeActivity) mContext;
////                                    activity.init();
////                                }
////                            } else {
////                                mContext.finish();
////                            }
////                        } else {
////                            new AntToast(mContext).ShowFail(response.body().getErrmsg());
////                        }
////                    }
////
////                    @Override
////                    public void onFailure(Call<PersonBean> call, Throwable t) {
////
////                    }
////                });
//
//
//    }
//
//    public String encodeUnicode(final String gbString) {
//
//        //return gbString;
//        String encode = null;
//        try {
//            encode = URLEncoder.encode(gbString, "UTF-8");//进行utf-8编码
//            // String decode = URLDecoder.decode(encode, "UTF-8"); 反编码
//            // Log.e("msg", "encodeUnicode: old==" + gbString + "   new==" + encode + "   end2===" + decode+"   3=="+de);
//            return encode;
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//
//        return gbString;
//
////        char[] utfBytes = gbString.toCharArray();
////        String unicodeBytes = "";
////        for (int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++) {
////            String hexB = Integer.toHexString(utfBytes[byteIndex]);
////            if (hexB.length() <= 2) {
////                hexB = "00" + hexB;
////            }
////            unicodeBytes = unicodeBytes + "\\u" + hexB;
////        }
////        return unicodeBytes;
//    }
//}
