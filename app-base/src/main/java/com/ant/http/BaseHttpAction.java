package com.ant.http;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;

import com.ant.base.AntApplication;
import com.ant.base.R;
import com.ant.base.config.AppConfig;
import com.ant.base.config.IntentConfig;
import com.ant.http.Bean.ErrBean;
import com.ant.http.Bean.HttpResult;
import com.ant.http.listener.OnHttpRequestListener;
import com.ant.user.UserSp;
import com.ant.utils.ActivityStackManager;
import com.ant.utils.LogUtil;
import com.ant.utils.StringUtil;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * copyright：haoxin
 * author：anthui
 * Version：1.0
 * creation date：2018/7/18
 * describe：
 */
public class BaseHttpAction {
    // public IHttpRequest mHttpRequest;
    public Retrofit retrofit;

    protected BaseHttpAction(int connectTime, String baseUrl, boolean showLog) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.cache(new Cache(Util.getApp().getCacheDir(), 10 * 1024 * 1024))
                .connectTimeout(connectTime, TimeUnit.SECONDS)
                .writeTimeout(connectTime, TimeUnit.SECONDS)
                .readTimeout(connectTime, TimeUnit.SECONDS);
        //日志拦截
        if (showLog) {
            builder.addInterceptor(new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY));
        }

        builder.interceptors().add(new ReceivedCookiesInterceptor());
        builder.interceptors().add(new AddCookiesInterceptor());
        builder.interceptors().add(new CacheInterceptor());
        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

    }

    /**
     * 自动解析
     *
     * @param call
     * @param listener
     */

    protected <T> void enqueue(Observable<HttpResult<T>> call, final OnHttpRequestListener
            listener) {
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HttpResult<T>>() {
                    @Override
                    public void accept(HttpResult<T> tHttpResult) throws Exception {
                        LogUtil.e("msg===", "json数据====" + tHttpResult.toString());
                        if (listener != null) {
                            if (tHttpResult.getCode() == 200) {
                                listener.onSuccess(tHttpResult.getData(), tHttpResult.getTokenInfo());
                            } else {

                                ErrBean errBean = new ErrBean(tHttpResult.getCode(), tHttpResult.getMessage());

                                if (errBean.getCode() == 401) {

                                    String loginActivityName = AppConfig.getLoginActivityName();
                                    if (!StringUtil.isEmpty(loginActivityName)) {

                                        Activity topActivity = ActivityStackManager.getInstance().getTopActivity();

                                        if (topActivity != null && loginActivityName.contains(topActivity.getLocalClassName())) {
                                            return;
                                        }

                                        //为空时 表示已经没有数据了
                                        if (UserSp.getUser(AntApplication.getInstance()) == null) {
                                            LogUtil.e("用户数据丢失，本地数据拦截");
                                            //   return;
                                        }
                                        //清楚本地用户数据
                                        UserSp.clearUserDbData(AntApplication.getInstance());
                                        Intent intent = new Intent();
                                        intent.setComponent(new ComponentName(AntApplication.getInstance().getPackageName(), loginActivityName));
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        intent.putExtra(IntentConfig.OBJECT_DATA, true);
                                        AntApplication.getInstance().startActivity(intent);

                                        return;

                                    }

                                }
                                listener.onFailure(errBean);
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        LogUtil.e("接口数据===", throwable);

                        // LogUtil.e("err=========" + throwable.getCause() + "   message===" + throwable.getMessage());
                        if (listener != null) {
                            ErrBean errBean = new ErrBean(ErrBean.ERR_CODE_NET, AntApplication.getInstance().getString(R.string.str_net_err));
                            listener.onFailure(errBean);
                        }
                    }
                });
    }

//    /**
//     * 自动解析 提供 登录注册使用
//     *
//     * @param call
//     * @param listener
//     */
//
//    protected <T> void loginEnqueue(Observable<HttpResult<T>> call, final OnHttpRequestListener
//            listener) {
//        call.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<HttpResult<T>>() {
//                    @Override
//                    public void accept(HttpResult<T> tHttpResult) throws Exception {
//                        Log.e("msg===", tHttpResult.toString());
//                        if (listener != null) {
//                            if (tHttpResult.isSuccess()) {
//                                listener.onSuccess(tHttpResult.getResult());
//                            } else {
//                                ErrBean errBean = new ErrBean(tHttpResult.getCode(), tHttpResult.getMessage());
//                                listener.onFailure(errBean);
//                            }
//                        }
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//
//                        LogUtil.e("数据错误问题====" + throwable.toString());
//                        if (listener != null) {
//                            ErrBean errBean = new ErrBean(ErrBean.ERR_CODE_NET, "网络错误");
//                            listener.onFailure(errBean);
//                        }
//                    }
//                });
//    }

}
