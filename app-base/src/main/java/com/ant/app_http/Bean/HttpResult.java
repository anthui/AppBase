package com.ant.app_http.Bean;

/**
 * copyright：""
 * author：anthui
 * Version：1.0
 * creation date：2018/7/18
 * describe： 数据 结构
 */

public class HttpResult<T> {


    @Override
    public String toString() {
        return "HttpResult{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", result=" + data +
                '}';
    }

    //返回的错误码
    private int code;
    //错误信息
    private String message;
    //返回的消息类型
    private T data;

    //此处预留，防止在data层中 添加奇怪数据
    private TokenInfoBean tokenInfo;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public TokenInfoBean getTokenInfo() {
        return tokenInfo;
    }

    public void setTokenInfo(TokenInfoBean tokenInfo) {
        this.tokenInfo = tokenInfo;
    }


}
