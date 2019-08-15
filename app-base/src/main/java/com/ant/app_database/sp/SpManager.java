package com.ant.app_database.sp;

import android.content.Context;


/**
 * copyright：""
 * author：anthui
 * Version：1.0
 * creation date：2018/2/1
 * describe： 数据存储管理类
 */
public class SpManager {

    private static final String PUSH_CLIENT_ID = "client_id";

    private static final String IS_FIRST = "is_first";
    private static final String TOUCH_ID = "touch_id";
    private static final String NOTIFICATION = "notification";
    private static final String PRIVATE_MODE = "private_mode";
    private static final String MESSAGE_NOTIFY = "message_notify";

    private static final String SP_DOWNLOAD_PATH = "download.path";
    private static final String LANGUAGE_CHANGE = "language_change";

    /**
     * 保存语言
     */
    public static void saveMessageNotifyPosition(Context context, int language) {
        SharedPreferencesUtil.setInteger(context, MESSAGE_NOTIFY, language);
    }


    /**
     * 获取当前语言
     */
    public static int getMessageNotifyPosition(Context context) {
        return SharedPreferencesUtil.getInteger(context, MESSAGE_NOTIFY, 0);
    }

    /**
     * 保存语言
     */
    public static void saveLanguage(Context context, int language) {
        SharedPreferencesUtil.setInteger(context, LANGUAGE_CHANGE, language);
    }


    /**
     * 获取当前语言
     */
    public static int getLanguageChange(Context context) {
        return SharedPreferencesUtil.getInteger(context, LANGUAGE_CHANGE, -1);
    }

    public static void saveClinetId(Context context, String clientId) {
        SharedPreferencesUtil.setValue(context, PUSH_CLIENT_ID, clientId);
    }

    public static String getClientId(Context context) {
        return SharedPreferencesUtil.getValue(context, PUSH_CLIENT_ID);
    }

    public static void saveTouchId(Context context, boolean open) {
        SharedPreferencesUtil.setBoolean(context, TOUCH_ID, open);
    }

    public static boolean getTouchId(Context context) {
        return SharedPreferencesUtil.getBoolean(context, TOUCH_ID, false);
    }

    public static void saveNotification(Context context, boolean open) {
        SharedPreferencesUtil.setBoolean(context, NOTIFICATION, open);
    }

    public static boolean getTNotification(Context context) {
        return SharedPreferencesUtil.getBoolean(context, NOTIFICATION, false);
    }

    public static void savePrivateMode(Context context, boolean open) {
        SharedPreferencesUtil.setBoolean(context, PRIVATE_MODE, open);
    }

    public static boolean getTPrivateMode(Context context) {
        return SharedPreferencesUtil.getBoolean(context, PRIVATE_MODE, false);
    }


    public static void isFirst(Context context, boolean isfirst) {

        SharedPreferencesUtil.setBoolean(context, IS_FIRST, isfirst);
    }

    public static boolean getIsFirst(Context context) {
        return SharedPreferencesUtil.getBoolean(context, IS_FIRST, true);

    }


    public static String getApkPath(Context context) {
        return SharedPreferencesUtil.getValue(context, SP_DOWNLOAD_PATH);
    }

    public static void putApkPath(Context context, String path) {
        SharedPreferencesUtil.setValue(context, SP_DOWNLOAD_PATH, path);

    }


}
