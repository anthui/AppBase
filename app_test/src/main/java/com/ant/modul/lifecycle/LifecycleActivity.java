package com.ant.modul.lifecycle;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.ant.anttestlibrary.R;
import com.ant.anttestlibrary.databinding.Aview;
import com.ant.app_base.BaseBindActivity;
import com.ant.app_utils.LogUtil;
import com.ant.modul.lifecycle.viewmodel.LifeViewModel;
import com.ant.modul.lifecycle.viewmodel.fragment.FragmentA;
import com.ant.modul.lifecycle.viewmodel.fragment.FragmentB;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2020/3/9.
 * describe：
 */
public class LifecycleActivity extends BaseBindActivity<Aview> {

    private FragmentA fragment;
    private FragmentB fragment1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    Aview viewDataBinding;
    Data datas;

    @Override
    public int getMainContentViewId() {

        return R.layout.activity_lifecycle;
    }

    @Override
    public void initData() {


        fragment = new FragmentA();
        fragment1 = new FragmentB();
        getSupportFragmentManager().beginTransaction().add(R.id.frag_a, fragment).add(R.id.frag_2, fragment1).commit();

    }

    @Override
    protected void onResume() {
        super.onResume();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                LiveModel.getInstance().setLiveData("onResume 2222222222222");
            }
        }, 100);

    }

    LifeViewModel viewModel;

    @Override
    public void initComponents(Bundle savedInstanceState, View rootView) {

//        ViewModelProvider.AndroidViewModelFactory instance = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication());

        viewModel = new ViewModelProvider(this).get(LifeViewModel.class);
        LogUtil.e("获取 ViewMOdeo activity  == " + viewModel.hashCode());

        viewModel.getLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                LogUtil.e("msg,数据改变 " + s);
                Log.e("msg", "msg == =========================================" + s);
                viewModel.setMessage(s);


            }
        });

    }


}
