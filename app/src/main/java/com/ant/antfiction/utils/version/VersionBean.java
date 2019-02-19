package com.ant.antfiction.utils.version;

import com.ant.base.BaseBean;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2018/11/27.
 * describe： 版本升级使用
 */
public class VersionBean extends BaseBean {


    /**
     * id : 1
     * update : 0
     * version : 1.0.1
     * size : 48982142
     * url : http://iec.bxguo.net/soft/android-1.0.1.apk
     * content : 修复BUG
     */

    private int id;
    private int update = 0;
    private String version;
    private int size;
    private String url;
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUpdate() {
        return update;
    }

    public void setUpdate(int update) {
        this.update = update;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

