package com.ant.anttestlibrary.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.viewpager.widget.ViewPager;

import com.ant.anttestlibrary.R;
import com.ant.app_base.BaseFragment;
import com.ant.app_base.ViewPageTabAdapter;
import com.ant.app_views.EmptyView;
import com.ant.app_views.dottablayout.DotTabLayout;

import java.util.ArrayList;
import java.util.List;


/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2019/2/13.
 * describe：
 */
public class TabLayoutFragment extends BaseFragment {

    DotTabLayout tabLayout;


    ViewPager viewPager;
    EmptyView emptyView;
    private List<String> titles;
    private List<BaseFragment> fragments;
    private ViewPageTabAdapter madapter;

    @Override
    public View getChenJinView() {
        return null;
    }


    @Override
    public void initComponents(Bundle savedInstanceState,View view) {

        viewPager = view.findViewById(R.id.view_page);
        tabLayout = view.findViewById(R.id.dotTab_layout);
        emptyView = view.findViewById(R.id.empty_view);
        titles = new ArrayList<>();
        fragments = new ArrayList<>();
        madapter = new ViewPageTabAdapter(getChildFragmentManager(), fragments, titles);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(madapter);

        emptyView.setNetClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initData();
            }
        });
    }


    @Override
    public void initData() {

        titles.add("首页1");
        titles.add("首页2");
        titles.add("首页3");
        titles.add("首页4");
        titles.add("首页5");
        titles.add("首页5");
        titles.add("首页5");


        for (int i = 0; i < titles.size(); i++) {
            HomeInnerFragment homeInnerFragment1 = new HomeInnerFragment();
            fragments.add(homeInnerFragment1);
        }
        madapter.notifyDataSetChanged();

    }

    @Override
    public int getMainContentViewId() {
        return R.layout.fragment_home_top;
    }
}
