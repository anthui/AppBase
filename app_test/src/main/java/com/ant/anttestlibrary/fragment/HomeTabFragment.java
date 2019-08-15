package com.ant.anttestlibrary.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.viewpager.widget.ViewPager;

import com.ant.anttestlibrary.R;
import com.ant.app_base.BaseFragment;
import com.ant.app_views.EmptyView;
import com.ant.app_views.dottablayout.DotTabLayout;

import butterknife.BindView;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2019/8/6.
 * describe：
 */
public class HomeTabFragment extends BaseFragment {
    @BindView(R.id.dotTab_layout)
    DotTabLayout dotTabLayout;
    @BindView(R.id.view_page)
    ViewPager viewPage;
    @BindView(R.id.empty_view)
    EmptyView emptyView;

    @Override
    public void initComponents(Bundle savedInstanceState,View view) {

        dotTabLayout.setupWithViewPager(viewPage);


    }

    @Override
    public void initData() {

    }

    @Override
    public int getMainContentViewId() {
        return R.layout.fragment_home_top;
    }
}
