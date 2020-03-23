package com.ant.app_base;

import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2020/3/11.
 * describe：  集成DataBinding
 */
public abstract class BaseBindActivity<T extends ViewDataBinding> extends BaseActivity {

    protected T dataBinding;

    protected View setContentView() {
        dataBinding = DataBindingUtil.setContentView(this, getMainContentViewId());
        return dataBinding.getRoot();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dataBinding != null) {
            dataBinding.unbind();
            dataBinding = null;
        }
    }
}
