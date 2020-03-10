package com.ant.modul.dataBinding;

import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.lifecycle.MutableLiveData;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2020/3/10.
 * describe：
 */
public class HomeViewModel  extends BaseObservable {

    public final MutableLiveData<String> message = new MutableLiveData<>();

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            message.setValue("haha" + System.currentTimeMillis());
        }
    };
}
