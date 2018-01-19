package com.stark.usercenter.mvp.presenter

import com.stark.base.extend.execute
import com.stark.base.mvp.presenter.BasePresenter
import com.stark.base.rx.BaseSubscriber
import com.stark.usercenter.data.protocal.UserInfo
import com.stark.usercenter.mvp.view.LoginView
import com.stark.usercenter.service.impl.UserServiceImpl
import javax.inject.Inject

/**
 * Created by zhao on 2018-01-16.
 * Copyright (c) 2018 ejia7.com . All rights reserved.
 */
class LoginPresenter @Inject constructor(): BasePresenter<LoginView>() {

    @Inject
    lateinit var userService: UserServiceImpl

    fun login(tel: String, pwd: String, pushID: String) {
        mView.showLoading()
        userService.login(tel, pwd, pushID).execute(object : BaseSubscriber<UserInfo>(mView) {
            override fun onNext(t: UserInfo?) {
                mView.onLogin(t!!)
            }
        }, lifecycleProvider)
    }
}