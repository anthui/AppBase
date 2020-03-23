package com.ant.kotlin.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import leakcanary.AppWatcher;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2020/3/17.
 * describe：
 */
public class LifeTestActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new TextView(this));
//        AppWatcher.objectWatcher.watch(this, "View was detached")

        AppWatcher.INSTANCE.getObjectWatcher().watch(this, "hahaha");
//        StateUtil intent = StateUtil.getIntent(this);
//

//
//        for (int i = 0; i < 5; i++) {
//            new User().setName("哈哈" + i);
//        }
//
//
        Observable.interval(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                //AutoDispose的使用就是这句//
//                .as(AutoDispose.<Long>autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        Log.e("接收数据,当前线程" + Thread.currentThread().getName(), String.valueOf(aLong));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
