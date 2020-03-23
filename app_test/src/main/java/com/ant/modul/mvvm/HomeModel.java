package com.ant.modul.mvvm;

import android.os.Handler;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import com.ant.app_http.listener.OnHttpRequestListener;

import java.util.ArrayList;
import java.util.List;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2020/3/11.
 * describe：
 */
public class HomeModel {

    public void getMessage(int page, int size, OnHttpRequestListener<List<String>> onHttpRequestListener, LifecycleOwner lifecycle) {



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ArrayList<String> strings = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    strings.add("新增数据" + i);
                }
                onHttpRequestListener.onSuccess(strings, null);

            }
        }, 10000);

    }
}
