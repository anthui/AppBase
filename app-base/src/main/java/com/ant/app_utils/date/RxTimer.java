package com.ant.app_utils.date;

import androidx.annotation.NonNull;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2019/8/5.
 * describe：  通过RXJava方式 实现定时器,这里只实现 一种，还有多种根据需求实现
 * <p>
 * 每个定时器可以 可重复使用
 * 使用时，监听方法最好 是同一个
 *
 *
 */

public class RxTimer {

    private Disposable mDisposable;

    /**
     * milliseconds毫秒后执行指定动作
     * 只执行 一次
     *
     * @param milliSeconds
     * @param rxAction
     */
    public void timer(long milliSeconds, final RxAction rxAction) {

        //执行前，先判断 是否已经被订阅
        cancel();
        Observable.timer(milliSeconds, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable disposable) {
                        mDisposable = disposable;
                    }

                    @Override
                    public void onNext(@NonNull Long number) {
                        if (rxAction != null) {
                            rxAction.action(number);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        //取消订阅
                        cancel();
                    }

                    @Override
                    public void onComplete() {
                        //取消订阅
                        cancel();
                    }
                });
    }

    /**
     * 每隔milliseconds毫秒后执行指定动作
     * 多次执行
     *
     * @param milliSeconds
     * @param rxAction
     */
    public void interval(long milliSeconds, final RxAction rxAction) {
        //执行前，先判断 是否已经被订阅
        cancel();
        Observable.interval(milliSeconds, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable disposable) {
                        mDisposable = disposable;
                    }

                    //number 执行的次数
                    @Override
                    public void onNext(@NonNull Long number) {
                        if (rxAction != null) {
                            rxAction.action(number);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        cancel();

                    }

                    @Override
                    public void onComplete() {
                        cancel();

                    }
                });
    }

    /**
     * 取消订阅
     */
    public void cancel() {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    public interface RxAction {
        /**
         * 让调用者指定指定动作
         *
         * @param number 执行的次数
         */
        void action(long number);
    }

}

