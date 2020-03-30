package com.ant.modul.test.javaTest;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2020/3/30.
 * describe：
 */
public class Respon {

    //{"code":200,"data":null,"message":"操作成功"}

    /**
     * code : 200
     * data : null
     * message : 操作成功
     */

    private int code;
    private Object data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
