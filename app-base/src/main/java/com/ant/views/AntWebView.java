package com.ant.views;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ant.utils.LogUtil;


/**
 * copyright：haoxin
 * author：anthui
 * Version：1.0
 * creation date：2018/1/10
 * describe：
 */
public class AntWebView extends WebView {
    private WebSettings mWebSettings;

    public AntWebView(Context context) {
        super(context);
        initWebView();
    }

    public AntWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initWebView();
    }

    private void initWebView() {
        mWebSettings = getSettings();
        mWebSettings.setSupportZoom(false);// 可以缩放
        mWebSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);// javascript可以自动打开窗体
        mWebSettings.setJavaScriptEnabled(true);// 允许javascript
        mWebSettings.setDomStorageEnabled(true);//开启DOM storage API功能
        mWebSettings.setAllowFileAccess(true);
        mWebSettings.setLoadWithOverviewMode(true);//自适应屏幕
        mWebSettings.setDatabaseEnabled(true);//开启database storage API功能
        mWebSettings.setGeolocationEnabled(true);
        mWebSettings.setLoadsImagesAutomatically(true);
        // mWebSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        setWebViewClient(new WebViewClient() {

                             @Override
                             public boolean shouldOverrideUrlLoading(WebView view, String url) {
                                 LogUtil.e("shouldOverrideUrlLoading" + url);
                                 view.loadUrl(url);
                                 return true;
                             }

//                             public void onReceivedSslError(WebView view,
//                                                            SslErrorHandler handler, SslError error) {
//                                 handler.proceed();
//                             }

                             @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                             @Override
                             public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                                 view.loadUrl(request.getUrl().toString());
                                 return true;

                             }
                         }


        );


    }
}
