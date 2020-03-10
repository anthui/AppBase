package com.ant.app_base.lifecycle;

import androidx.lifecycle.LifecycleOwner;

import io.reactivex.disposables.Disposable;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2020/3/9.
 * describe：  生命周期 管理类
 */
public class DisposableManager extends BaseLifecycle {
    Disposable disposable;

    public void bindLifecycle(LifecycleOwner owner, Disposable disposable) {
        this.disposable = disposable;
        if (owner != null && disposable != null) {
            addObserver(owner);
        }
    }

    @Override
    public void onDestroy() {
        disposable.dispose();
        super.onDestroy();
    }
}
