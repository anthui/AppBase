package com.ant.app_base.lifecycle;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date：2020/3/9
 * describe： 生命周期
 */
@Keep
public abstract class BaseLifecycle implements LifecycleObserver {
    LifecycleOwner owner;

    /**
     * 绑定生命周期
     *
     * @param owner 谷歌提供的生命周期接口，Fragment及FragmetnActivity已实现此接口
     */
    public void addObserver(@NonNull LifecycleOwner owner) {
        this.owner = owner;
        owner.getLifecycle().addObserver(this);
    }

    /**
     * 解绑生命周期
     *
     * @param owner
     */
    public void removeObserver(@NonNull LifecycleOwner owner) {
        owner.getLifecycle().removeObserver(this);
    }

    /**
     * 在LifecycleOwner的onCreate之后触发
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
    }

    /**
     * 在LifecycleOwner的onStart之后触发
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
    }

    /**
     * 在LifecycleOwner的onResume之后触发
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
    }

    /**
     * 在LifecycleOwner的onPause之前触发
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
    }

    /**
     * 在LifecycleOwner的onStop之前触发
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
    }

    /**
     * 在LifecycleOwner的onDestroy之前触发
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        if (owner != null) {
            removeObserver(owner);
        }
    }

    /**
     * 任意生命周期
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    public void onAny(LifecycleOwner owner, Lifecycle.Event event) {
    }
}
