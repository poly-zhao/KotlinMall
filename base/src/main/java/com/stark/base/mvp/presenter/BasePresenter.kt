package com.stark.base.mvp.presenter

import android.content.Context
import com.stark.base.mvp.view.BaseView
import com.stark.base.utils.NetWorkUtils
import com.trello.rxlifecycle.LifecycleProvider
import javax.inject.Inject

/**
 * Created by zhao on 2018-01-11.
 * Copyright (c) 2018 ejia7.com . All rights reserved.
 */
open class BasePresenter<T : BaseView> {
    lateinit var mView: T

    @Inject
    lateinit var lifecycleProvider:LifecycleProvider<*>

    @Inject
    lateinit var context:Context

    /**
     * 检查网络是否可用
     */
    fun checkNetWork():Boolean{
        if(NetWorkUtils.isNetWorkAvailable(context)){
            return true
        }
        return false
    }
}