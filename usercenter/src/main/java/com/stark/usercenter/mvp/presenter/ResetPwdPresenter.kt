package com.stark.usercenter.mvp.presenter

import com.stark.base.extend.execute
import com.stark.base.mvp.presenter.BasePresenter
import com.stark.usercenter.mvp.view.ResetPwdView
import com.stark.usercenter.service.impl.UserServiceImpl
import javax.inject.Inject

/**
 * Created by zhao on 2018-01-11.
 * Copyright (c) 2018 ejia7.com . All rights reserved.
 */
class ResetPwdPresenter @Inject constructor() : BasePresenter<ResetPwdView>() {

    @Inject
    lateinit var userService: UserServiceImpl

    fun resetPwd(tel: String,pwd: String) {
        mView.showLoading()
        userService.resetPwd(tel, pwd).execute(onNext, mView, lifecycleProvider)
    }

    var onNext = fun(t: Boolean) {
        if (t) {
            mView.onResetPwd(t)
        }
    }
}

