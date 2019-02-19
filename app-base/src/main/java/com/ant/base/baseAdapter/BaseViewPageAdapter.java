//package com.ant.base.baseAdapter;
//
//
//import android.app.Fragment;
//import android.app.FragmentManager;
//import android.support.v4.app.FragmentPagerAdapter;
//
//import com.ant.base.BaseFragment;
//
//import java.util.List;
//
///**
// * copyright：
// * author：anthui
// * Version：1.0
// * creation date：2018/2/6
// * describe：适用于viewpage
// */
//public class BaseViewPageAdapter extends FragmentPagerAdapter {
//    private final List<BaseFragment> list;
//    private final List<String> titles;
//
//    public BaseViewPageAdapter(FragmentManager fm, List<BaseFragment> list, List<String> titles) {
//        super(fm);
//        this.list = list;
//        this.titles = titles;
//    }
//
//    @Override
//    public Fragment getItem(int position) {
//        return list.get(position);
//    }
//
//    @Override
//    public int getCount() {
//        return list.size();
//    }
//
//    @Override
//    public CharSequence getPageTitle(int position) {
//
//        if (titles != null && list.size() != 0 && position < titles.size()) {
//            return titles.get(position);
//        }
//        return super.getPageTitle(position);
//    }
//}
