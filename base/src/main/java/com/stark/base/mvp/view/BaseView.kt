package com.stark.base.mvp.view

/**
 * Created by zhao on 2018-01-11.
 * Copyright (c) 2018 ejia7.com . All rights reserved.
 */
interface BaseView {
    fun showLoading()
    fun hideLoading()
    fun onError(msg: String)
}