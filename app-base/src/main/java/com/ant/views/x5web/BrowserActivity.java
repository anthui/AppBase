//package com.ant.views.x5web;
//
//import android.os.Bundle;
//import android.view.KeyEvent;
//import android.widget.FrameLayout;
//
//import com.ant.base.BaseActivity;
//import com.ant.antproject.R;
//
//import butterknife.BindView;
//
///**
// * copyright：haoxin
// * author：anthui
// * Version：1.0
// * creation date：2018/5/9
// * describe： 浏览器
// */
//public class BrowserActivity extends BaseActivity {
//
//    private static final String mHomeUrl = "http://app.html5.qq.com/navi/index";
//
//    @BindView(R.id.framelayout)
//    FrameLayout framelayout;
//
//    private X5WebView mWebView;
//
//
//    @Override
//    protected void initComponents(Bundle savedInstanceState) {
//        initWebView();
//    }
//
//    @Override
//    protected void initData() {
//
//    }
//
//    @Override
//    protected int getMainContentViewId() {
//        return R.layout.activity_browser;
//    }
//
//    /**
//     * 返回键拦截
//     */
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            if (mWebView != null && mWebView.canGoBack()) {
//                mWebView.goBack();
//                return true;
//            } else
//                return super.onKeyDown(keyCode, event);
//        }
//        return super.onKeyDown(keyCode, event);
//    }
//
//    private void initWebView() {
//
//        mWebView = new X5WebView(this, null);
//        framelayout.addView(mWebView, new FrameLayout.LayoutParams(
//                FrameLayout.LayoutParams.MATCH_PARENT,
//                FrameLayout.LayoutParams.MATCH_PARENT));
//        // initProgressBar();
//        mWebView.loadUrl(mHomeUrl);
//    }
//}
