package com.ant.modul.test.javaTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2020/3/30.
 * describe：
 */
public class JsonUtilTest {

    Respon respon;

    private void loge(String msg) {
//        LogUtil.e("msg========" + msg);



    }

    String json = "{\"code\":200,\"data\":null,\"message\":\"操作成功\"}";

    @Before
    public void setUp() throws Exception {
        loge("setUp");
        respon = JsonUtil.toObject(json, Respon.class);
    }

    @After
    public void tearDown() throws Exception {
        loge("tearDown");

    }

    @Test
    public void toJson() {
        loge("tearDown");

    }

    @Test
    public void testToJson() {
        loge("tearDown");

    }

    @Test
    public void toObject() {
        loge("tearDown");

    }

    @Test
    public void parseJSON() {
        loge("tearDown");

    }
}