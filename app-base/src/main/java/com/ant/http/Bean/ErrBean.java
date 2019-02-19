package com.ant.http.Bean;

import java.io.Serializable;

/**
 * copyright：haoxin
 * author：anthui
 * Version：1.0
 * creation date：2018/7/18
 * describe：
 */
public class ErrBean implements Serializable {

    //网络错误
    public static int ERR_CODE_NET = -1;
    //json解析错误
    public static String ERR_CODE_JSON = "2";


    private int code;

    private ErrBean() {
    }

    @Override
    public String toString() {
        return "ErrBean{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }

    public ErrBean(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private String msg;
}
