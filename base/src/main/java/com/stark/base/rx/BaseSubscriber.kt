package com.stark.base.rx

import com.stark.base.mvp.view.BaseView
import rx.Subscriber

/**
 * Created by zhao on 2018-01-12.
 * Copyright (c) 2018 ejia7.com . All rights reserved.
 */
abstract class BaseSubscriber<T>(private var baseView:BaseView):Subscriber<T>() {

    override fun onCompleted() {
        baseView.hideLoading()
    }

    override fun onError(e: Throwable?) {
        println(e)
        baseView.hideLoading()
        if (e is BaseRxException){
            baseView.onError(e.msg)
        }
    }
}