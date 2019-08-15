//package com.ant.utils;
//
//import android.util.Log;
//import android.widget.Toast;
//
//import com.ant.base.BaseActivity;
//import com.ant.utils.bean.ShareBean;
//
//import java.util.HashMap;
//
//import cn.sharesdk.framework.Platform;
//import cn.sharesdk.framework.PlatformActionListener;
//import cn.sharesdk.framework.ShareSDK;
//import cn.sharesdk.sina.weibo.SinaWeibo;
//import cn.sharesdk.tencent.qq.QQ;
//import cn.sharesdk.tencent.qzone.QZone;
//import cn.sharesdk.wechat.friends.Wechat;
//import cn.sharesdk.wechat.moments.WechatMoments;
//
//
//public class ShareUtils implements PlatformActionListener {
//    private BaseActivity mcontext;
//    ShareBean shareBean;
//
//    public ShareUtils(BaseActivity context) {
//        mcontext = context;
//    }
//
//    public void showShare(ShareBean shareBean) {
//        this.shareBean = shareBean;
//        switch (shareBean.getShare_plat()) {
//
//            case ShareBean.SHARE_QZON: {
//                //title、titleUrl、site、siteUrl，QQ
//                Platform platform = ShareSDK.getPlatform(QZone.NAME);
//                QZone.ShareParams sp = new Platform.ShareParams();
//                sp.setText(shareBean.getText());
//                sp.setTitle(shareBean.getTitle());
//                sp.setTitleUrl(shareBean.getUrl());
//                sp.setImagePath(shareBean.getImgUrl());
//                sp.setSite("景转型");
//                sp.setSiteUrl("http://mob.com");
//
//                platform.setPlatformActionListener(this); // 设置分享事件回调
//                // 执行分享
//                platform.share(sp);
//            }
//            break;
//            case ShareBean.SHARE_PYQ: {
//                Platform wechatMoments = ShareSDK.getPlatform(WechatMoments.NAME);
//                if (!wechatMoments.isClientValid()) {
//                    // Log.e("msg", "onClick: 请先安装微信");
//                    Toast.makeText(mcontext, "请先安装微信", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (!PermissionUtil.grantStorageGroup(mcontext)) {
//                    Toast.makeText(mcontext, "请检查sd卡读取权限", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                WechatMoments.ShareParams sp = new WechatMoments.ShareParams();
//                sp.setShareType(shareBean.getShare_type());
//
//
//                sp.setTitle(shareBean.getTitle());  //分享标题
//                sp.setText(shareBean.getText());   //分享文本
//
//                sp.setImageUrl(shareBean.getImgUrl());//网络图片rul
//                sp.setUrl(shareBean.getUrl());   //网友点进链接后，可以看到分享的详情
//
//
//                wechatMoments.setPlatformActionListener(this); // 设置分享事件回调
//                // 执行分享
//                wechatMoments.share(sp);
//            }
//            break;
//            case ShareBean.SHARE_QQ: {
//                //     MobclickAgent.onEventValue(mcontext, UmentConfig.share_qq_id, map, UmentConfig.umengid);
//
//                if (shareBean != null) {
//                    Platform qq = ShareSDK.getPlatform(QQ.NAME);
//                    QQ.ShareParams sp = new QQ.ShareParams();
//                    //设置标题
//                    if (!StringUtil.isEmpty(shareBean.getTitle())) {
//                        sp.setTitle(shareBean.getTitle());
//                    }
//                    //设置文本
//                    if (StringUtil.isEmpty(shareBean.getText())) {
//                        sp.setText("  ");//网络图片rul
//                    } else {
//                        sp.setText(shareBean.getText());//网络图片rul
//                    }
//                    //设置连接
//                    if (!StringUtil.isEmpty(shareBean.getUrl())) {
//                        sp.setTitleUrl(shareBean.getUrl());//网络图片rul
//                    }
//                    //设置图片连接
//                    if (!StringUtil.isEmpty(shareBean.getImgUrl())) {
//                        sp.setImageUrl(shareBean.getImgUrl());//网络图片rul
//                    }
//                    sp.setShareType(shareBean.getShare_type());//非常重要：一定要设置分享属性
//                    qq.setPlatformActionListener(this);
//                    // 执行分享
//                    qq.share(sp);
//                }
//
//            }
//            break;
//            case ShareBean.SHARE_WX: {
//                Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
//                if (!wechat.isClientValid()) {
//                    Toast.makeText(mcontext, "请先安装微信", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (!PermissionUtil.grantStorageGroup(mcontext)) {
//                    Toast.makeText(mcontext, "请检查sd卡读取权限", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                Wechat.ShareParams sp = new Wechat.ShareParams();
//
//                Log.e("msg", "onClick: wx");
//
//                sp.setShareType(shareBean.getShare_type());//非常重要：一定要设置分享属性
//                sp.setTitle(shareBean.getTitle());  //分享标题
//                sp.setText(shareBean.getText());   //分享文本
//                sp.setImageUrl(shareBean.getImgUrl());//网络图片rul
//                sp.setUrl(shareBean.getUrl());   //网友点进链接后，可以看到分享的详情
//                wechat.setPlatformActionListener(this); // 设置分享事件回调
//                // 执行分享
//                wechat.share(sp);
//
//            }
//            break;
//            case ShareBean.SHARE_WB: {
//                //MobclickAgent.onEventValue(mcontext, UmentConfig.share_wb_id, map, UmentConfig.umengid);
//                SinaWeibo.ShareParams sp = new SinaWeibo.ShareParams();
//                if (!StringUtil.isEmpty(shareBean.getText())) {
//                    Log.e("msg", "onClick: 2");
//                    sp.setText(shareBean.getText() + shareBean.getUrl());//网络图片rul
//                    Log.e("msg", "onClick: ===" + shareBean.getText() + shareBean.getUrl());
//                }
//                if (!StringUtil.isEmpty(shareBean.getImgUrl())) {
//                    sp.setImageUrl(shareBean.getImgUrl());
//                }
//                //3、非常重要：获取平台对象
//                Platform sinaWeibo = ShareSDK.getPlatform(SinaWeibo.NAME);
//                sinaWeibo.setPlatformActionListener(this); // 设置分享事件回调
//                // 执行分享
//                sinaWeibo.share(sp);
//            }
//            break;
//
//        }
//
//    }
//
//
//    @Override
//    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
//        Log.e("msg", "分享结束");
//        //new AntToast(mcontext).ShowSuccess("分享成功");
//
//        LogUtil.e("onComplete 分享成果");
//
//        //Log.e("msg", "onComplete: " + platform.toString());
//        // MyDialogSuccess dialogSuccess = new MyDialogSuccess();
//        // dialogSuccess.showLoadingDialog(mcontext, "分享成功", 1000);
//
//    }
//
//    @Override
//    public void onError(Platform platform, int i, Throwable throwable) {
//        //  Log.e("msg", "onError: " + platform.toString());
//        //  MyDialogFail dialogSuccess = new MyDialogFail();
//        //  dialogSuccess.showLoadingDialog(mcontext, "分享失败", 1000);
//        // new AntToast(mcontext).ShowFail("分享失败");
//        LogUtil.e("onError+分享失败");
//    }
//
//    @Override
//    public void onCancel(Platform platform, int i) {
//        //  MyDialogFail dialogSuccess = new MyDialogFail();
//        // dialogSuccess.showLoadingDialog(mcontext, "已取消分享", 1000);
//        LogUtil.e("onCancel  取消岑翔");
//    }
//
//}
