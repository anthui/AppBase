package com.ant.antfiction.home.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ant.antfiction.R;
import com.ant.base.BaseFragment;
import com.ant.utils.ImageLoadUtil;
import com.ant.views.antBanner.Banner;
import com.ant.views.antBanner.BannerAdapter;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2019/2/13.
 * describe：
 */
public class HomeInnerFragment extends BaseFragment {
    @BindView(R.id.banner)
    Banner banner;

    //    BannerAdapter adapter;
    ArrayList<String> bannerList;

    @Override
    protected void initComponents(View view) {

        bannerList = new ArrayList<>();
        BannerAdapter adapter = new BannerAdapter<String>(bannerList) {
            @Override
            protected void bindTips(TextView tv, String s) {

            }

            @Override
            public void bindImage(ImageView imageView, String s) {
                ImageLoadUtil.LoadImgDefault(mContext, R.mipmap.bg_ad, s, imageView);
            }

        };
        banner.setBannerAdapter(adapter);
    }

    @Override
    protected void initData() {


        bannerList.add("");
        bannerList.add("");
        bannerList.add("");
        bannerList.add("");
        bannerList.add("");
        bannerList.add("");
        banner.notifyDataHasChanged(false);


    }

    @Override
    protected int getMainContentViewId() {
        return R.layout.fragment_home_inner;
    }
}
