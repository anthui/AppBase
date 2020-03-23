package com.ant.modul.mvvm;

import android.content.Intent;
import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.ant.app_http.Bean.ErrBean;
import com.ant.app_http.Bean.TokenInfoBean;
import com.ant.app_http.listener.OnHttpRequestListener;
import com.ant.app_utils.LogUtil;
import com.ant.modul.lifecycle.LifecycleActivity2;

import java.util.List;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2020/3/11.
 * describe：
 */
public class HomeViewModel extends androidx.lifecycle.ViewModel implements HomViewClick {

    public HomeViewModel() {
        if (homeModel == null) {
        }
        homeModel = new HomeModel();
    }

    public View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            index++;
            getData();
            v.getContext().startActivity(new Intent(v.getContext(), LifecycleActivity2.class));
        }
    };

    private MutableLiveData<List<String>> list = new MutableLiveData<>();

    public MutableLiveData<List<String>> getList() {
        return list;
    }

    int index = 1;
    int size = 20;

    HomeModel homeModel;

    @Override
    public void onRefresh(View view) {

        LogUtil.e("刷新数据");
        index = 1;
        getData();


    }

    @Override
    public void loadMore(View view) {
        index++;
        getData();
    }

    private void getData() {


//        homeModel.getMessage(index, size, new OnHttpRequestListener<List<String>>() {
//            @Override
//            public void onSuccess(List<String> strings, TokenInfoBean tokenInfoBean) {
//
//                LogUtil.e("数据返回===========ViewModel============" + strings.toString());
//                list.setValue(strings);
//            }
//
//            @Override
//            public void onFailure(ErrBean errBean) {
//
//            }
//        });
    }
}
