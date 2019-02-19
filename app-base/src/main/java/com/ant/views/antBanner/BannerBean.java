package com.ant.views.antBanner;


/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date：2018/1/31
 * describe： 轮播数据实体类   必须要包含imageurl
 */
public class BannerBean {

    private String imageUrl;
    private String mTips;

    public String getTips() {
        return mTips;
    }

    public void setTips(String tips) {
        mTips = tips;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
