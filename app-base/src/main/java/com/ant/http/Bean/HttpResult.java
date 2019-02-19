package com.ant.http.Bean;

/**
 * copyright：haoxin
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
                ", success=" + success +
                ", result=" + data +
                '}';
    }

    private int code;
    private String message;
    private boolean success;
    private T data;

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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }


    public TokenInfoBean getTokenInfo() {
        return tokenInfo;
    }

    public void setTokenInfo(TokenInfoBean tokenInfo) {
        this.tokenInfo = tokenInfo;
    }


}
