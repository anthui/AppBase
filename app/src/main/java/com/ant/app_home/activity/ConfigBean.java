package com.ant.app_home.activity;

import android.content.Context;

import com.ant.app_base.config.IntentConfig;
import com.ant.app_database.sp.SharedPreferencesUtil;

/**
 * copyright：
 *
 * @author：anthui Version：1.0
 * creation date： 2020/6/3.
 * describe：
 */
public class ConfigBean {
    private int num;
    private int jiange;
    private int jiangeTime;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getJiange() {
        return jiange;
    }

    public void setJiange(int jiange) {
        this.jiange = jiange;
    }

    public int getJiangeTime() {
        return jiangeTime;
    }

    public void setJiangeTime(int jiangeTime) {
        this.jiangeTime = jiangeTime;
    }


    public static ConfigBean getInstance(Context mContext) {
        ConfigBean configBean = new ConfigBean();


        boolean aBoolean = SharedPreferencesUtil.getBoolean(mContext, IntentConfig.KEY_moren, true);

        if (aBoolean) {
            configBean.jiange = SharedPreferencesUtil.getInteger(mContext, IntentConfig.KEY_jiange, 2);
            configBean.num = SharedPreferencesUtil.getInteger(mContext, IntentConfig.KEY_jiange_num, 50);
            configBean.jiangeTime = SharedPreferencesUtil.getInteger(mContext, IntentConfig.KEY_jiange_time, 60);

            SharedPreferencesUtil.setBoolean(mContext, IntentConfig.KEY_moren, false);

        } else {
            configBean.jiange = SharedPreferencesUtil.getInteger(mContext, IntentConfig.KEY_jiange, 0);
            configBean.num = SharedPreferencesUtil.getInteger(mContext, IntentConfig.KEY_jiange_num, 0);
            configBean.jiangeTime = SharedPreferencesUtil.getInteger(mContext, IntentConfig.KEY_jiange_time, 0);
        }


        return configBean;
    }

    @Override
    public String toString() {
        return "ConfigBean{" +
                "num=" + num +
                ", jiange=" + jiange +
                ", jiangeTime=" + jiangeTime +
                '}';
    }
}
