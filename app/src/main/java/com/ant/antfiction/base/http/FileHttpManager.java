package com.ant.antfiction.base.http;

import com.ant.http.BaseHttpAction;
import com.ant.http.Bean.HttpBaseConfig;
import com.ant.http.listener.OnHttpRequestListener;

import java.util.HashMap;

import okhttp3.MultipartBody;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date：2018/7/19
 * describe： 网络请求 单利模式
 */
@SuppressWarnings("unchecked")
public class FileHttpManager extends BaseHttpAction {
    private static FileHttpManager httpManager;


    FilePostService filePostService;

    private FileHttpManager() {
        //文件上传 不打印日志 否则会报错
        super(20, HttpBaseConfig.getHttpBaseUrl(), false);
        filePostService = retrofit.create(FilePostService.class);

    }

    public static FileHttpManager getHttpAction() {
        if (httpManager == null)
            synchronized (FileHttpManager.class) {
                if (httpManager == null)
                    httpManager = new FileHttpManager();
            }
        return httpManager;
    }




    /**
     * 上传护照
     */
//    public void upVerificationImage(MultipartBody.Part file, OnHttpRequestListener onHttpRequestListener) {
//
//        enqueue(filePostService.upVerificationImage(new HashMap<String, String>(), file), onHttpRequestListener);
//    }




}
