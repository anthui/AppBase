package com.ant.modul.mvvm;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ant.anttestlibrary.R;
import com.ant.anttestlibrary.databinding.ActivityHomeVmBinding;
import com.ant.app_base.BaseBindActivity;
import com.ant.app_utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2020/3/11.
 * describe：MVVM实际使用 测试用例
 */
public class HomeActivity extends BaseBindActivity<ActivityHomeVmBinding> {
    HomeViewModel viewModel;

    @Override
    public int getMainContentViewId() {
        return R.layout.activity_home_vm;
    }

    @Override
    public void initData() {

        viewModel.getList().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {

                if (strings != null) {

                    LogUtil.e("msg=========================== 数据回调Activity" + strings.toString());
                } else {
                    LogUtil.e("msg=========================== 数据回调Activity null");

                    return;
                }


                datas.addAll(strings);
                homAdapter.notifyDataSetChanged();
            }
        });
        viewModel.onRefresh(null);
    }

    HomAdapter homAdapter;
    List<String> datas;

    @Override
    public void initComponents(Bundle savedInstanceState, View rootView) {

        viewModel = getViewModel(HomeViewModel.class);
        dataBinding.setModel(viewModel);


        datas = new ArrayList<>();
        homAdapter = new HomAdapter(mContext, datas);

        dataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        dataBinding.recyclerView.setAdapter(homAdapter);


    }


}
