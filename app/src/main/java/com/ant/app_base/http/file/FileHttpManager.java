package com.ant.app_base.http.file;

import com.ant.app_http.HttpBaseAction;
import com.ant.app_http.Bean.HttpBaseConfig;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date：2018/7/19
 * describe： 网络请求 单利模式
 */
public class FileHttpManager extends HttpBaseAction {
    private static FileHttpManager httpManager;

    FilePostService filePostService;

    private FileHttpManager() {
        //文件上传 不打印日志 否则会报错
        super(20, "", false);
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


}
