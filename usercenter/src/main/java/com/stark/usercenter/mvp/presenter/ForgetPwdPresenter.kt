package com.stark.usercenter.mvp.presenter

import com.stark.base.extend.execute
import com.stark.base.mvp.presenter.BasePresenter
import com.stark.usercenter.mvp.view.ForgetPwdView
import com.stark.usercenter.service.impl.UserServiceImpl
import javax.inject.Inject

/**
 * Created by zhao on 2018-01-16.
 * Copyright (c) 2018 ejia7.com . All rights reserved.
 */
class ForgetPwdPresenter @Inject constructor():BasePresenter<ForgetPwdView>() {
    @Inject
    lateinit var userService:UserServiceImpl

    fun forgetPwd(tel:String, verifyCode:String){
        userService.forgetPwd(tel,verifyCode).execute({t:Boolean->
                mView.onForgetPwd(t)
        },mView,lifecycleProvider)
    }
}