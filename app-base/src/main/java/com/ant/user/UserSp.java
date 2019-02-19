package com.ant.user;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.ant.user.bean.UserBean;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date：2017/11/27
 * describe：保存用户信息  后期需要统一使用数据库
 */
public class UserSp {
    private static final String USER_FILE_NAME = "user_info";
    private static final String KEY_USER_SERIALIZE = "user_serialize";

    /**
     * 保存User对象
     *
     * @param context
     */
    public static void saveUser(Context context, UserBean user) {
        clearUserDbData(context);
        SharedPreferences sp = context.getSharedPreferences(USER_FILE_NAME, Context.MODE_PRIVATE);
        try {
            String ser = serializeObject(user);
            sp.edit().putString(KEY_USER_SERIALIZE, ser).commit();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    /**
     * 获取User 对象
     *
     * @param context
     * @return
     */
    public static UserBean getUser(Context context) {
        if (context == null)
            return null;
        SharedPreferences sp = context.getSharedPreferences(USER_FILE_NAME, Context.MODE_PRIVATE);
        String deSer = sp.getString(KEY_USER_SERIALIZE, "");
        if (!TextUtils.isEmpty(deSer)) {
            try {
                UserBean user = deSerializationObject(deSer);
                if (user == null) {
                    clearUserDbData(context);
                    return null;
                }
                return user;
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                clearUserDbData(context);

                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                clearUserDbData(context);

                e.printStackTrace();
            }
        }
        return null;
    }


    /**
     * 序列化对象
     *
     * @return
     * @throws IOException
     */

    private static String serializeObject(UserBean bean) throws IOException {
        long startTime = System.currentTimeMillis();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(bean);
        String serStr = byteArrayOutputStream.toString("ISO-8859-1");
        serStr = java.net.URLEncoder.encode(serStr, "UTF-8");
        objectOutputStream.close();
        byteArrayOutputStream.close();
        long endTime = System.currentTimeMillis();
        System.out.println("序列化耗时为:" + (endTime - startTime));
        return serStr;
    }

    /**
     * 反序列化对象
     *
     * @param str
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private static UserBean deSerializationObject(String str) throws IOException,
            ClassNotFoundException {
        long startTime = System.currentTimeMillis();
        String redStr = java.net.URLDecoder.decode(str, "UTF-8");
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
                redStr.getBytes("ISO-8859-1"));
        ObjectInputStream objectInputStream = new ObjectInputStream(
                byteArrayInputStream);
        UserBean object = (UserBean) objectInputStream.readObject();
        objectInputStream.close();
        byteArrayInputStream.close();
        //  long endTime = System.currentTimeMillis();
        //   UxApplication.userHasChange = true;
        return object;
    }


    /**
     * 清除UserDb数据
     *
     * @param context
     */
    public static void clearUserDbData(Context context) {
        SharedPreferences sp = context.getSharedPreferences(USER_FILE_NAME, Context.MODE_PRIVATE);
        sp.edit().clear().commit();
        //UxApplication.userHasChange = true;
    }


}