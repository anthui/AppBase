package com.ant.base;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * copyright：haoxin
 * author：anthui
 * Version：1.0
 * creation date：2018/5/14
 * describe： viewPage对应的适配器
 */
public class ViewPageTabAdapter extends FragmentStatePagerAdapter {
    private List<BaseFragment> list;
    private List<String> titles;

    public ViewPageTabAdapter(FragmentManager fm, List<BaseFragment> list, List<String> titles) {
        super(fm);
        this.list = list;
        this.titles = titles;
    }

    public ViewPageTabAdapter(FragmentManager fm, List<BaseFragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        if (titles != null && list.size() != 0 && position < titles.size()) {
            return titles.get(position);
        }
        return super.getPageTitle(position);
    }
//
//    @Override
//    public Parcelable saveState() {
//        return null;
//    }
}
