package com.ant.utils;

import android.text.TextUtils;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * copyright：haoxin
 * author：anthui
 * Version：1.0
 * creation date：2017/12/1
 * describe： json解析工具类
 */
public class JsonUtil {

    /**
     * 对象转Json字符串
     *
     * @param entity 需要转换的对象实体
     * @return
     */
    public static String toJson(Object entity) {
        if (entity == null) {
            return "";
        }
        try {
            Gson gson = new Gson();
            return gson.toJson(entity);
        } catch (Exception e) {
            LogUtil.e("toJson 异常。", e);
            return "";
        }
    }

    /**
     * 对象转Json字符串
     *
     * @param entity 需要转换的对象实体
     * @return
     */
    public static String toJson(List<Object> entity) {
        if (entity == null) {
            return "";
        }
        try {
            Gson gson = new Gson();
            return gson.toJson(entity);
        } catch (Exception e) {
            LogUtil.e("toJson 异常。", e);
            return "";
        }
    }

    /**
     * Json字符串转对象
     *
     * @param <T>
     * @param result Json字符串
     * @return
     */
    public static <T> T toObject(String result, Class<T> clazz) {
        if (clazz == null || TextUtils.isEmpty(result)) {
            return null;
        }

        try {
            Gson gson = new Gson();
            return gson.fromJson(result, clazz);
        } catch (Exception e) {
            LogUtil.e("JSON 转换异常！", e);
            try {
                return clazz.newInstance();
            } catch (IllegalAccessException e1) {
                LogUtil.e("toObject IllegalAccessException 实例化异常", e1);
            } catch (InstantiationException e1) {
                LogUtil.e("toObject IllegalAccessException 实例化异常", e1);
            }
        }
        return null;
    }

    /**
     * 转换字符串为Json对象
     *
     * @param json json字符串
     * @return
     */
    public static JSONObject parseJSON(String json) {
        try {
            return new JSONObject(json);
        } catch (JSONException e) {
            LogUtil.e("JSONException :" + e);
        }
        return new JSONObject();
    }
}
