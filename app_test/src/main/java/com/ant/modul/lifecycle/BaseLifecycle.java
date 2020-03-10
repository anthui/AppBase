package com.ant.modul.lifecycle;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

import com.ant.app_utils.LogUtil;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2020/3/9.
 * describe：
 */
public class BaseLifecycle implements LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {

        LogUtil.e("msg=================== onCreate");

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        LogUtil.e("msg=================== onStart");

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        LogUtil.e("msg=================== onPause");

    }

    /**
     * 在LifecycleOwner的onStop之前触发
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        LogUtil.e("msg=================== onStop");

    }

    /**
     * 在LifecycleOwner的onDestroy之前触发
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        LogUtil.e("msg=================== onDestroy");

    }

    /**
     * 任意生命周期
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    public void onAny(LifecycleOwner owner, Lifecycle.Event event) {
        LogUtil.e("msg=================== onAny" + event.name());
    }
}
