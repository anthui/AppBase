package com.ant.app_views.antBanner;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date：2018/1/31
 * describe： 数据适配器，这里只是 作为Banner对外的 数据对接，并未真实适配器
 * <p>
 * banner 实际适配器为 viewPageAdapter
 */
public abstract class BannerAdapter<T> {
    private List<T> mDataList;

    List<T> getDataList() {
        return mDataList;
    }

    protected BannerAdapter(List<T> dataList) {
        mDataList = dataList;
    }

    //****************************Banner内部使用，对应获取外部 3个方法，多次一步为了 做判空处理**************************
    void setImageViewSource(ImageView imageView, int position) {
        if (mDataList != null && mDataList.size() > 0) {
            bindImage(imageView, mDataList.get(position));
        }
    }

    void selectTips(TextView tv, int position) {
        if (mDataList != null && mDataList.size() > 0) {
            bindTips(tv, mDataList.get(position));
        }
    }


    View getBannerItemView(int position) {
        if (mDataList != null && mDataList.size() > 0) {
            return getBannerItemView(mDataList.get(position));
        }
        return null;
    }


    //    ****************************内部数据绑定**********************
    protected abstract void bindTips(TextView tv, T t);

    public abstract void bindImage(ImageView imageView, T t);

    /**
     * 获取Banner的子View
     * 如果重写 此方法 setImageViewSource()方法就不会在执行
     * 此处为自定义布局 ，默认的布局只是一个ImageView
     */
    public View getBannerItemView(T t) {
        return null;
    }


}
