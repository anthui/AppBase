package com.ant.http;

import com.ant.base.AntApplication;
import com.ant.user.UserSp;
import com.ant.user.bean.UserBean;
import com.ant.utils.LogUtil;
import com.ant.utils.language.LocalManageUtil;

import java.io.IOException;
import java.util.Locale;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by jogger on 2018/3/6.请求拦截
 */

class AddCookiesInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        final Request.Builder builder = chain.request().newBuilder();

        String language = "";
        try {
            //   Locale locale = AntApplication.getInstance().getResources().getConfiguration().locale;
            Locale locale = LocalManageUtil.getSetLanguageLocale(AntApplication.getInstance());
            language = locale.getLanguage();
        } catch (Exception e) {

        }

        UserBean user = UserSp.getUser(AntApplication.getInstance());
        if (user != null) {
            builder.addHeader("Authorization", "Bearer " + user.getToken());
        }

        builder.addHeader("Lang", language);
       // LogUtil.e("lalla===" + language);
        return chain.proceed(builder.build());
    }
}
