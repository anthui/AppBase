package com.ant.app_base.http;

import com.ant.app_http.Bean.HttpResult;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2018/11/14.
 * describe：
 */
public interface PostApiService {


    @GET(HttpConfig.HOME_DATA_INTT)
    Observable<HttpResult<Object>> getCurrentVersion();

}
