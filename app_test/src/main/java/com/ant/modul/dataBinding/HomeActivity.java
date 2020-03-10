package com.ant.modul.dataBinding;

import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.ant.anttestlibrary.R;
import com.ant.anttestlibrary.databinding.HomeBind;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2020/3/10.
 * describe：
 */
public class HomeActivity extends AppCompatActivity {

    private HomeViewModel homeViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        HomeBind bind = DataBindingUtil.setContentView(this, R.layout.layout_binding);
        homeViewModel = new HomeViewModel();
//        homeViewModel.message.setValue("haha");

        bind.setHomeViewModel(homeViewModel);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                homeViewModel.message.setValue("0000");

            }
        }, 1000);

    }
}
