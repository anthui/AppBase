package com.ant.modul.lifecycle;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.ant.anttestlibrary.R;
import com.ant.anttestlibrary.databinding.Aview;
import com.ant.app_base.BaseActivity;
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
public class LifecycleActivity extends BaseActivity {
    //    @BindView(R.id.iv_back)
//    ImageView ivBack;
//    @BindView(R.id.tv_title)
//    TextView tvTitle;
//    @BindView(R.id.tv_right)
//    TextView tvRight;
//    @BindView(R.id.iv_right)
//    ImageView ivRight;
//    @BindView(R.id.view_line_bar)
//    View viewLineBar;
//    @BindView(R.id.tb_toolbar)
//    Toolbar tbToolbar;
//    @BindView(R.id.tv_content)
//    TextView tvContent;
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
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_lifecycle);
        viewModel = new ViewModelProvider(this).get(LifeViewModel.class);
        viewDataBinding.setModel(viewModel);
        datas = new Data();
        datas.setMessage("hahahh-------------");
        viewDataBinding.setDatas(datas);
        return 0;
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

//        ViewModelProvider.AndroidViewModelFactory.getInstance(LifeViewModel.class)


        LogUtil.e("获取 ViewMOdeo activity  == " + viewModel.hashCode());

        viewModel.getLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                LogUtil.e("msg,数据改变 " + s);
                Log.e("msg", "msg == =========================================" + s);
//                viewDataBinding.tvContent.setText(s);
                viewModel.setMessage(s);

                datas.setMessage("00000000000000000000000000" + s);
                viewDataBinding.setDatas(datas);
//                tvContent.setText(s);
            }
        });

//        viewModel.getLiveData().setValue("哈哈哈哈哈");
    }


}
