package com.ant.app_http;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;

import com.ant.app_base.BaseApplication;
import com.ant.app_base.R;
import com.ant.app_base.config.AppBaseConfig;
import com.ant.app_base.config.IntentBaseConfig;
import com.ant.app_http.Bean.ErrBean;
import com.ant.app_http.Bean.HttpResult;
import com.ant.app_http.listener.OnHttpRequestListener;
import com.ant.app_utils.ActivityStackManager;
import com.ant.app_utils.CommonUtils;
import com.ant.app_utils.LogUtil;
import com.ant.app_utils.StringUtil;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * copyright：""
 * author：anthui
 * Version：1.0
 * creation date：2018/7/18
 * describe：
 */
public abstract class HttpBaseAction {
    // public IHttpRequest mHttpRequest;
    public Retrofit retrofit;


    protected HttpBaseAction(int connectTime, String baseUrl, boolean showLog) {

        //初始化okHttpClient构建
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //添加缓存大小 10M
        builder.cache(new Cache(Util.getApp().getCacheDir(), 10 * 1024 * 1024))
                .connectTimeout(connectTime, TimeUnit.SECONDS)//超时时间
                .writeTimeout(connectTime, TimeUnit.SECONDS)//写入时间
                .readTimeout(connectTime, TimeUnit.SECONDS);//读取时间
        //日志拦截器
        if (showLog) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.level(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
        }

        //添加拦截器  公共参数
        builder.interceptors().add(new CookiesInterceptor() {
            @Override
            public void addHead(Request.Builder builder) {
                addHeads(builder);
            }
        });

        //添加缓存器
        builder.interceptors().add(new CacheInterceptor());
        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())//Gson解析器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//RxAndroid适配器
                .build();

    }

    /**
     * 添加公共参数
     */
    protected void addHeads(Request.Builder builder) {
    }

    /**
     * 自动解析
     *
     * @param observable 观察者
     * @param listener   观察者模式
     *                   理解为：观察的的事件正在
     *                   1、发起线程调度（事件在哪个线程运行）---事件发生的线程
     *                   2、观察线程（事件的结果 处理）------处理事件发送后的线程
     *                   3、发起订阅关系（相当于事件的结果，回调）
     *                   4、错误回调，中途错误时，回调
     */

    protected <T> void enqueue(Observable<HttpResult<T>> observable, final OnHttpRequestListener listener) {


        //对象赋值 这里多余，只是忽略报 警告
        Disposable subscribe = observable
                //Schedulers.io()线程调度 使用io线程，处理网络请求、文件存储等耗时操作，会有类似于有线程缓存的存在----
                // 其他：immediate当前线程执行，
                // newthread新的线程执行，AndroidSchedulers.mainThread()主线程种运行，computation计算任务，for循环之类的，普通任务使用
                .subscribeOn(Schedulers.io())
                //被观察 ，结果回归主线程
                .observeOn(AndroidSchedulers.mainThread())
                //发起订阅-------------事件处理完后，会主动调用 回调，或者 走错误方法
                .subscribe(new Consumer<HttpResult<T>>() {
                    @Override
                    public void accept(HttpResult<T> tHttpResult) throws Exception {
//                        LogUtil.e("msg===", "json数据====" + tHttpResult.toString());
                        if (listener == null) {
                            return;
                        }
                        if (tHttpResult.getCode() == ErrBean.CODE_SUCCESS) {
                            listener.onSuccess(tHttpResult.getData(), tHttpResult.getTokenInfo());
                        } else {
                            ErrBean errBean = new ErrBean(tHttpResult.getCode(), tHttpResult.getMessage());
//                            401错误，单点登录问题
                            if (errBean.getCode() == ErrBean.CODE_SHOULD_LOGIN) {
                                String loginActivityName = AppBaseConfig.getLoginActivityName();
                                if (!StringUtil.isEmpty(loginActivityName)) {

                                    Activity topActivity = ActivityStackManager.getInstance().getTopActivity();

                                    if (topActivity != null && loginActivityName.contains(topActivity.getLocalClassName())) {
                                        return;
                                    }
                                    //清楚本地用户数据
                                    Intent intent = new Intent();
                                    intent.setComponent(new ComponentName(BaseApplication.getInstance().getPackageName(), loginActivityName));
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    intent.putExtra(IntentBaseConfig.KEY_OBJECT_DATA, true);
                                    BaseApplication.getInstance().startActivity(intent);

                                    return;

                                }

                            }
                            listener.onFailure(errBean);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        LogUtil.e("接口数据===", throwable);


                        if (listener == null) {
                            return;
                        }
                        if (CommonUtils.isNetConnectionAvailable(BaseApplication.getInstance())) {
                            if (listener != null) {
                                ErrBean errBean = new ErrBean(ErrBean.CODE_ERR_NET, BaseApplication.getInstance().getString(R.string.str_net_err));
                                listener.onFailure(errBean);
                            }
                        }
                        // LogUtil.e("err=========" + throwable.getCause() + "   message===" + throwable.getMessage());

                    }
                });


    }


}
