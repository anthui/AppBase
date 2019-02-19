package com.ant.utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ant.base.R;
import com.ant.base.config.AppConfig;


/**
 * copyright：haoxin
 * author：anthui
 * Version：1.0
 * creation date：2017/12/1
 * describe：弹窗工具类 所有的调试都走此方法
 */

public class ToastUtil {

    private static boolean isDebug = AppConfig.isDebug;
    ;
    private static int duration = Toast.LENGTH_SHORT;


    /**
     * toast显示
     *
     * @param
     * @param content
     */
    public static void showDebugToast(Context mContext, String content, final int duration) {
        if (isDebug) {
            Toast toast = Toast.makeText(mContext, content, duration);
            toast.show();
        }
    }

    /**
     * toast显示
     *
     * @param
     * @param resId 字符串id
     */
    public static void showDebugToast(Context mContext, int resId, final int duration) {
        if (isDebug) {
            Toast toast = Toast.makeText(mContext, resId, duration);
            toast.show();
        }
    }

    /**
     * toast显示
     */
    public static void showDebugToast(Context mContext, String msg) {
        if (isDebug) {


            Toast toast = Toast.makeText(mContext, msg + "", duration);
            toast.show();


        }
    }


    public static void showDebugCneterToast(Context mcontext, String msg) {
        showDebugCenterToast(mcontext, msg, 0);
    }


    public static void showCneterToast(Context mContext, String msg, int resid) {
        if (msg == null || mContext == null) {
            LogUtil.e("消息内容为空");

            return;
        }


//        if (msg.length() > 7) {
//            showToast(mContext, msg);
//            return;
//        }

        Toast toast1 = new Toast(mContext);
        toast1.setDuration(Toast.LENGTH_SHORT);

        View inflate = LayoutInflater.from(mContext).inflate(R.layout.toast_center, null);

        TextView tvMessage = inflate.findViewById(R.id.tv_message);
        if (resid != 0) {
            ImageView ivPic = inflate.findViewById(R.id.iv_pic);
            ivPic.setImageResource(resid);
            //return;
        }


        tvMessage.setText(msg);
        toast1.setView(inflate);
        toast1.setGravity(Gravity.CENTER, 0, 0);
        toast1.show();

    }

    public static void showCenterToast(Context mContext, String msg, boolean isSuccess) {
        if (isSuccess) {

            showCneterToast(mContext, msg, R.mipmap.icon_success);
        } else {

            showCneterToast(mContext, msg, R.mipmap.icon_fail2);
        }

    }

    /**
     * toast显示
     */
    public static void showDebugCenterToast(Context mContext, String msg, int resid) {
        if (isDebug) {
            showCneterToast(mContext, msg, resid);

        }
    }

    /**
     * toast显示
     */
    public static void showDebugCenterToast(Context mContext, String msg) {
        if (isDebug) {
            showCneterToast(mContext, msg, 0);

        }
    }

    /**
     * toast显示
     * 这里用于所有弹窗 非debug下的弹窗，这里集成，方便日子修改自定义弹窗、判断数据等
     */
    public static void showToast(Context context, String msg) {

        try {
            if (context == null || TextUtils.isEmpty(msg)) {
                return;
            }
            Toast toast = Toast.makeText(context, msg, duration);
            toast.show();

        } catch (Exception e) {

        }

    }

}
