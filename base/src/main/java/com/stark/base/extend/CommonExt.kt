package com.stark.base.extend

import android.widget.ImageView
import com.stark.base.data.BaseResp
import com.stark.base.mvp.view.BaseView
import com.stark.base.rx.BaseSubscriber
import com.stark.base.utils.GlideUtils
import com.trello.rxlifecycle.LifecycleProvider
import rx.Observable
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Func1
import rx.schedulers.Schedulers

/**
 * Created by zhao on 2018-01-12.
 * Copyright (c) 2018 ejia7.com . All rights reserved.
 */

fun <T> Observable<T>.execute(subscriber: Subscriber<T>, lifecycleProvider: LifecycleProvider<*>) {
    this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .compose(lifecycleProvider.bindToLifecycle())
            .subscribe(subscriber)
}

fun <T> Observable<T>.execute(action: (T) -> Unit, baseView: BaseView, lifecycleProvider: LifecycleProvider<*>) {
    this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .compose(lifecycleProvider.bindToLifecycle())
            .subscribe(object : BaseSubscriber<T>(baseView) {
                override fun onNext(t: T) {
                    action(t)
                }
            })
}

fun <T> Observable<BaseResp<T>>.convertToBoolean(): Observable<Boolean> {
    return this.flatMap(object : Func1<BaseResp<T>, Observable<Boolean>> {
        override fun call(t: BaseResp<T>): Observable<Boolean> {
            if (t.status == 0) {
                return Observable.just(true)
            }
            return Observable.error(com.stark.base.rx.BaseRxException(t.status, t.message))
        }
    })
}

fun <T> Observable<BaseResp<T>>.convert(): Observable<T> {
    return this.flatMap(object : Func1<BaseResp<T>, Observable<out T>> {
        override fun call(t: BaseResp<T>): Observable<out T> {
            if (t.status == com.stark.base.common.ResultCode.FAIL) {
                return Observable.error(com.stark.base.rx.BaseRxException(t.status, t.message))
            }
            return Observable.just(t.data)
        }

    })
}

/*
    ImageView加载网络图片
 */
fun ImageView.loadUrl(url: String) {
    GlideUtils.loadUrlImage(context, url, this)
}
