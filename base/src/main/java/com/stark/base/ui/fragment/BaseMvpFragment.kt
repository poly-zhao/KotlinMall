package com.stark.base.ui.fragment

import com.stark.base.mvp.presenter.BasePresenter
import com.stark.base.mvp.view.BaseView

/**
 * Created by zhao on 2018-01-11.
 * Copyright (c) 2018 ejia7.com . All rights reserved.
 */
open class BaseMvpFragment <T:BasePresenter<*>>: BaseFragment(), BaseView {

    lateinit var mPresenter:T

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onError(msg: String) {
    }
}