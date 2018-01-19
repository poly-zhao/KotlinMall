package com.stark.usercenter.mvp.presenter

import com.stark.base.extend.execute
import com.stark.base.mvp.presenter.BasePresenter
import com.stark.usercenter.mvp.view.RegisterView
import com.stark.usercenter.service.impl.UserServiceImpl
import javax.inject.Inject

/**
 * Created by zhao on 2018-01-11.
 * Copyright (c) 2018 ejia7.com . All rights reserved.
 */
class RegisterPresenter @Inject constructor() : BasePresenter<RegisterView>() {

    @Inject
    lateinit var userService: UserServiceImpl

    fun register(tel: String, verifyCode: String, pwd: String) {
        mView.showLoading()
        userService.register(tel, verifyCode, pwd).execute(onNext, mView, lifecycleProvider)
    }

    var onNext = fun(t: Boolean) {
        if (t) {
            mView.onRegisterResult("注册成功")
        } else {
            mView.onRegisterResult("注册失败 ")
        }
    }
}

