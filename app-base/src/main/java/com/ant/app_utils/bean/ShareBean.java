package com.ant.app_utils.bean;


import java.io.Serializable;


/**
 * copyright：""
 * author：anthui
 * Version：1.0
 * creation date：2018/6/11
 * describe： 分享 实体类
 */

/*private void showShare() {
 OnekeyShare oks = new OnekeyShare();
 //关闭sso授权
 oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
 //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
 // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
 oks.setTitle(getString(R.string.share));
 // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
 oks.setTitleUrl("http://sharesdk.cn");
 // text是分享文本，所有平台都需要这个字段
 oks.setText("我是分享文本");
 // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
 oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
 // url仅在微信（包括好友和朋友圈）中使用
 oks.setUrl("http://sharesdk.cn");
 // comment是我对这条分享的评论，仅在人人网和QQ空间使用
 oks.setComment("我是测试评论文本");
 // site是分享此内容的网站名称，仅在QQ空间使用
 oks.setSite(getString(R.string.app_name));
 // siteUrl是分享此内容的网站地址，仅在QQ空间使用
 oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
 oks.show(this);
 }*/
public class ShareBean implements Serializable {

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getShare_type() {
        return share_type;
    }

    public void setShare_type(int share_type) {
        this.share_type = share_type;
    }

    public String getShare_plat() {
        return share_plat;
    }

    public void setShare_plat(String share_plat) {
        this.share_plat = share_plat;
    }

    private String title = "";//分享标题
    private String url = "";//分享点击 的连接
    private String imgUrl = "";//头像 连接
    private String text = "";//分享文本
    // private int share_type = Platform.SHARE_IMAGE;//分享类型
    private int share_type;//
    private String share_plat = "";//分享平台qq\wx\wb\pyq

    public final static String SHARE_QQ = "SHARE_QQ";
    public final static String SHARE_WX = "SHARE_WX";
    public final static String SHARE_WB = "SHARE_WB";
    public final static String SHARE_PYQ = "SHARE_PYQ";
    public final static String SHARE_QZON = "SHARE_QZON";

}