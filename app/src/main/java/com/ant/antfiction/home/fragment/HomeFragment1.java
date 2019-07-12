package com.ant.antfiction.home.fragment;

import androidx.viewpager.widget.ViewPager;
import android.view.View;

import com.ant.antfiction.R;
import com.ant.base.BaseFragment;
import com.ant.base.ViewPageTabAdapter;
import com.ant.views.EmptyView;
import com.ant.views.dottablayout.DotTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2019/2/13.
 * describe：
 */
public class HomeFragment1 extends BaseFragment {

    @BindView(R.id.tab_layout)
    DotTabLayout tabLayout;


    @BindView(R.id.view_page)
    ViewPager viewPager;
    @BindView(R.id.empty_view)
    EmptyView emptyView;
    Unbinder unbinder;
    private List<String> titles;
    private List<BaseFragment> fragments;
    private ViewPageTabAdapter madapter;

    @Override
    public View getChenJinView() {
        return tabLayout;
    }


    @Override
    protected void initComponents(View view) {
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
    protected void initData() {

        titles.add("首页1");
        titles.add("首页2");
        titles.add("首页3");
        titles.add("首页4");
        titles.add("首页5");


        for (int i = 0; i < titles.size(); i++) {
            HomeInnerFragment homeInnerFragment1 = new HomeInnerFragment();
            fragments.add(homeInnerFragment1);
        }
        madapter.notifyDataSetChanged();

    }

    @Override
    protected int getMainContentViewId() {
        return R.layout.fragment_home;
    }
}
