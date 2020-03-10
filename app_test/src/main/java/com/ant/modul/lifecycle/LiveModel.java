package com.ant.modul.lifecycle;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2020/3/9.
 * describe：
 */
public class LiveModel {


    private static LiveModel liveModel;

    public static LiveModel getInstance() {

        if (liveModel == null) {
            liveModel = new LiveModel();
        }
        return liveModel;


    }


    private MutableLiveData<String> liveData = new MutableLiveData<>();

    public void addObserver(Observer<String> observer) {
        liveData.observeForever(observer);
    }

    public void setLiveData(String message) {
        liveData.setValue(message);

    }
}
