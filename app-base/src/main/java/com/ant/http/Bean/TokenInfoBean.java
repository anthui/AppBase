package com.ant.http.Bean;

import java.io.Serializable;

/**
 * copyright：haoxin
 * author：anthui
 * Version：1.0
 * creation date：2018/7/19
 * describe：
 */
public class TokenInfoBean implements Serializable {
    /**
     * timestamp : 1532020238
     * _T_ID : 00003
     * nstr : JpjfFx
     * AccessToken : aad6d572dc527504ae1560bb25130f98dc688133d7aa77db12c1c6b02a2c32afb253b5e7f10ba56d
     */

    private int timestamp;
    private String _T_ID;
    private String nstr;
    private String AccessToken;

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public String get_T_ID() {
        return _T_ID;
    }

    public void set_T_ID(String _T_ID) {
        this._T_ID = _T_ID;
    }

    public String getNstr() {
        return nstr;
    }

    public void setNstr(String nstr) {
        this.nstr = nstr;
    }

    public String getAccessToken() {
        return AccessToken;
    }

    public void setAccessToken(String AccessToken) {
        this.AccessToken = AccessToken;
    }
}