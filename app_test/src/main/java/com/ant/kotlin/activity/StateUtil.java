package com.ant.kotlin.activity;

import android.content.Context;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2020/3/17.
 * describe：
 */
public class StateUtil {
    private static Context context;


    public StateUtil(Context con) {
        context = con;
    }

    public static StateUtil getIntent(Context mcontext) {
        return new StateUtil(mcontext);
    }
}
