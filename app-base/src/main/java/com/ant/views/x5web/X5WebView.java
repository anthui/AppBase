//package com.ant.views.x5web;
//
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.graphics.Canvas;
//import android.graphics.Paint;
//import android.os.Build;
//import android.util.AttributeSet;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.FrameLayout;
//
//import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient;
//import com.tencent.smtt.export.external.interfaces.JsResult;
//import com.tencent.smtt.sdk.CookieSyncManager;
//import com.tencent.smtt.sdk.QbSdk;
//import com.tencent.smtt.sdk.WebChromeClient;
//import com.tencent.smtt.sdk.WebSettings;
//import com.tencent.smtt.sdk.WebSettings.LayoutAlgorithm;
//import com.tencent.smtt.sdk.WebView;
//import com.tencent.smtt.sdk.WebViewClient;
//import com.ant.antproject.R;
//
///**
// * copyright：haoxin
// * author：anthui
// * Version：1.0
// * creation date：2018/5/9
// * describe：  内置 x5内核的webView
// * <p>
// * <p>
// * public void onCreate() {
// * super.onCreate();
// * QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
// *
// * @Override public void onCoreInitFinished() {
// * Log.e("msg", "web初始化结束");
// * }
// * @Override public void onViewInitFinished(boolean b) {
// * Log.e("msg", "是否加载到 xf" + b);
// * }
// * };
// * //初始化x5
// * QbSdk.initX5Environment(getApplicationContext(), cb);
// * }
// */
//public class X5WebView extends WebView {
//
//    private WebViewClient client = new WebViewClient() {
//        /**
//         * 防止加载网页时调起系统浏览器
//         */
//        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            view.loadUrl(url);
//            return true;
//        }
//    };
//
//
//    WebChromeClient webChromeClient = new WebChromeClient() {
//
//        @Override
//        public boolean onJsConfirm(WebView arg0, String arg1, String arg2,
//                                   JsResult arg3) {
//            return super.onJsConfirm(arg0, arg1, arg2, arg3);
//        }
//
//        View myVideoView;
//        View myNormalView;
//        IX5WebChromeClient.CustomViewCallback callback;
//
//        /**
//         * 全屏播放配置
//         */
//        @Override
//        public void onShowCustomView(View view,
//                                     IX5WebChromeClient.CustomViewCallback customViewCallback) {
//            FrameLayout normalView = (FrameLayout) findViewById(R.id.web_filechooser);
//            ViewGroup viewGroup = (ViewGroup) normalView.getParent();
//            viewGroup.removeView(normalView);
//            viewGroup.addView(view);
//            myVideoView = view;
//            myNormalView = normalView;
//            callback = customViewCallback;
//        }
//
//        @Override
//        public void onHideCustomView() {
//            if (callback != null) {
//                callback.onCustomViewHidden();
//                callback = null;
//            }
//            if (myVideoView != null) {
//                ViewGroup viewGroup = (ViewGroup) myVideoView.getParent();
//                viewGroup.removeView(myVideoView);
//                viewGroup.addView(myNormalView);
//            }
//        }
//
//        @Override
//        public boolean onJsAlert(WebView arg0, String arg1, String arg2,
//                                 JsResult arg3) {
//            /**
//             * 这里写入你自定义的window alert
//             */
//            return super.onJsAlert(null, arg1, arg2, arg3);
//        }
//    };
//
//
//    public X5WebView(Context arg0) {
//        this(arg0, null);
//        setBackgroundColor(85621);
//    }
//
//    @SuppressLint("SetJavaScriptEnabled")
//    public X5WebView(Context arg0, AttributeSet arg1) {
//        super(arg0, arg1);
//        this.setWebViewClient(client);
//        this.setWebChromeClient(webChromeClient);
//        // WebStorage webStorage = WebStorage.getInstance();
//        initWebViewSettings(arg0);
//        this.getView().setClickable(true);
//    }
//
//    /**
//     * 初始化 settings
//     */
//    private void initWebViewSettings(Context mContext) {
//
//        WebSettings webSetting = this.getSettings();
//        webSetting.setJavaScriptEnabled(true);
//        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
//        webSetting.setAllowFileAccess(true);
//        webSetting.setLayoutAlgorithm(LayoutAlgorithm.NARROW_COLUMNS);
//        webSetting.setSupportZoom(true);
//        webSetting.setBuiltInZoomControls(true);
//        webSetting.setUseWideViewPort(true);
//        webSetting.setSupportMultipleWindows(true);
//        webSetting.setLoadWithOverviewMode(true);
//        webSetting.setAppCacheEnabled(true);
//        // webSetting.setDatabaseEnabled(true);
//        webSetting.setDomStorageEnabled(true);
//        webSetting.setGeolocationEnabled(true);
//        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
//        // webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
//        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
//        // webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
//        webSetting.setCacheMode(WebSettings.LOAD_NO_CACHE);
//
//        //关闭直接向上向下 按钮
//        setVerticalScrollBarEnabled(false);
//        setHorizontalScrollBarEnabled(false);
//
//        //关机滚动条
//        getX5WebViewExtension().setScrollBarFadingEnabled(false);
//
//        CookieSyncManager.createInstance(mContext);
//        CookieSyncManager.getInstance().sync();
//        // this.getSettingsExtension().setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);//extension
//        // settings 的设计
//    }
//
//    @Override
//    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
//        boolean ret = super.drawChild(canvas, child, drawingTime);
//        canvas.save();
//
//        Paint paint = new Paint();
//        paint.setColor(0x7fff0000);
//        paint.setTextSize(24.f);
//        paint.setAntiAlias(true);
//        if (getX5WebViewExtension() != null) {
//            canvas.drawText(this.getContext().getPackageName() + "-pid:"
//                    + android.os.Process.myPid(), 10, 50, paint);
//            canvas.drawText(
//                    "X5  Core:" + QbSdk.getTbsVersion(this.getContext()), 10,
//                    100, paint);
//        } else {
//            canvas.drawText(this.getContext().getPackageName() + "-pid:"
//                    + android.os.Process.myPid(), 10, 50, paint);
//            canvas.drawText("Sys Core", 10, 100, paint);
//        }
//        canvas.drawText(Build.MANUFACTURER, 10, 150, paint);
//        canvas.drawText(Build.MODEL, 10, 200, paint);
//        canvas.restore();
//        return ret;
//    }
//
//
//}
