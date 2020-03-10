package com.ant.modul.lifecycle.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ant.app_utils.LogUtil;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2020/3/9.
 * describe：
 */
public class LifeViewModel extends ViewModel {

    public MutableLiveData<String> liveData = new MutableLiveData<>();

    private String message;


    public void setMessage(String message) {
        this.message = message;
        LogUtil.e("msg,数据改变message=== " + message);

    }

    public String getMessage() {
        return message;
    }

    public MutableLiveData<String> getLiveData() {

        return liveData;
    }

    public void setData(String message) {
        this.liveData.setValue(message);
    }
}
