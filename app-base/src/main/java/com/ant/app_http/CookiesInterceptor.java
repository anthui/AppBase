package com.ant.app_http;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * copyright：
 * author：
 * Version：1.0
 * creation date：2019/8/8
 * describe： 公共参数 设置Cookies
 */
public abstract class CookiesInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        final Request.Builder builder = chain.request().newBuilder();

//        String language = "";
//        try {
//            //   Locale locale = BaseApplication.getInstance().getResources().getConfiguration().locale;
//            Locale locale = LocalManageUtil.getSetLanguageLocale(BaseApplication.getInstance());
//            language = locale.getLanguage();
//        } catch (Exception e) {
//
//        }
//
////        UserBean user = UserSp.getUser(BaseApplication.getInstance());
//
//
////        if (user != null) {
////            builder.addHeader("Authorization", "Bearer " + user.getToken());
////        }
//
//        builder.addHeader("Lang", language);
//
        addHead(builder);
        return chain.proceed(builder.build());
    }

    public abstract void addHead(Request.Builder builder);

}
