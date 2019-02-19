package com.ant.utils;

import android.content.Context;

/**
 * copyright：haoxin
 * author：anthui
 * Version：1.0
 * creation date：2018/1/31
 * describe：大小
 */

public class SizeUtil {
    /**
     * 转换dip为px
     *
     * @param context
     * @param dip     值
     * @return
     */
    public static int dipToPx(Context context, double dip) {
        float scale = AppHelper.getScreenDensity(context);
        return (int) (dip * scale + 0.5f * (dip >= 0 ? 1 : -1));
    }

    /**
     * 转换px为dip
     *
     * @param context
     * @param px      值
     * @return
     */
    public static int pxTodip(Context context, int px) {
        float scale = AppHelper.getScreenDensity(context);
        return (int) (px / scale + 0.5f * (px >= 0 ? 1 : -1));
    }

    /**
     * 将PX转SP
     *
     * @param context
     * @param pxValue px值
     * @return
     */
    public static int pxTosp(Context context, float pxValue) {
        float fontScale = AppHelper.getScaledDensity(context);
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将SP转PX
     *
     * @param context
     * @param spValue sp值
     * @return
     */
    public static int spTopx(Context context, float spValue) {
        float fontScale = AppHelper.getScaledDensity(context);
        return (int) (spValue * fontScale + 0.5f);
    }
}
