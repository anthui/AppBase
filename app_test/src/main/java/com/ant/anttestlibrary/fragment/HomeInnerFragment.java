package com.ant.anttestlibrary.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ant.anttestlibrary.R;
import com.ant.app_base.AppExceptionHelper;
import com.ant.app_base.BaseFragment;
import com.ant.app_utils.ImageLoadUtil;
import com.ant.app_utils.LogUtil;
import com.ant.app_views.antBanner.AntBanner;
import com.ant.app_views.antBanner.BannerAdapter;
import com.ant.app_views.antBanner.ZoomOutPageTransformer;

import java.io.File;
import java.util.ArrayList;


/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2019/2/13.
 * describe：
 */
public class HomeInnerFragment extends BaseFragment {

    /**
     * 自定义VIew
     */
    private void bannerThree(View view) {
        ArrayList<String> bannerList = new ArrayList<>();
        BannerAdapter adapter = new BannerAdapter<String>(bannerList) {
            @Override
            protected void bindTips(TextView tv, String s) {
                tv.setText("three");
            }

            @Override
            public void bindImage(ImageView imageView, String s) {
                ImageLoadUtil.LoadImgDefault(mContext, R.mipmap.bg_ad, s, imageView);
            }

            @Override
            public View getBannerItemView(String s) {

                LogUtil.e("执行到这里");
                View inflate = LayoutInflater.from(mContext).inflate(R.layout.beanner_item, null);
                ImageView viewById = inflate.findViewById(R.id.iv_pic);

                ImageLoadUtil.LoadImgDefault(mContext, R.mipmap.bg_ad, s, viewById);
                return inflate;
            }
        };
        AntBanner banner = view.findViewById(R.id.banner);
        banner.setPageTransformer(new ZoomOutPageTransformer());

        banner.setBannerAdapter(adapter);

        bannerList.add("");
        bannerList.add("");
        bannerList.add("");
        bannerList.add("");
        bannerList.add("");
        bannerList.add("");
        banner.notifyDataHasChanged(false);
    }

    private void bannerDefult(View view) {
        ArrayList<String> bannerList = new ArrayList<>();
        BannerAdapter adapter = new BannerAdapter<String>(bannerList) {
            @Override
            protected void bindTips(TextView tv, String s) {
                tv.setText("default");

            }

            @Override
            public void bindImage(ImageView imageView, String s) {
                ImageLoadUtil.LoadImgDefault(mContext, R.mipmap.bg_ad, s, imageView);
            }

        };
        AntBanner banner = view.findViewById(R.id.banner2);
        banner.setBannerAdapter(adapter);


        bannerList.add("");
        bannerList.add("");
        bannerList.add("");
        bannerList.add("");
        bannerList.add("");
        bannerList.add("");
        banner.notifyDataHasChanged(false);
    }

    @Override
    public void initComponents(Bundle savedInstanceState,View view) {
        bannerThree(view);
        bannerDefult(view);
    }


    @Override
    public void initData() {


    }

    @Override
    public int getMainContentViewId() {
        return R.layout.fragment_home_inner;
    }
}
