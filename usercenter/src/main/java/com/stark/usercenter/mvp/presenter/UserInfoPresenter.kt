package com.stark.usercenter.mvp.presenter

import com.stark.base.extend.execute
import com.stark.base.mvp.presenter.BasePresenter
import com.stark.base.rx.BaseSubscriber
import com.stark.usercenter.data.protocal.UserInfo
import com.stark.usercenter.mvp.view.UserInfoView
import com.stark.usercenter.service.impl.UploadServiceImpl
import com.stark.usercenter.service.impl.UserServiceImpl
import javax.inject.Inject

/**
 * Created by zhao on 2018-01-17.
 * Copyright (c) 2018 ejia7.com . All rights reserved.
 */
class UserInfoPresenter @Inject constructor() : BasePresenter<UserInfoView>() {

    @Inject
    lateinit var uploadService: UploadServiceImpl

    @Inject
    lateinit var userService: UserServiceImpl

    fun getUploadToken() {
        if (!checkNetWork()) {
            mView.onError("网络不可用")
            return
        }

        mView.showLoading()
        uploadService.getUploadToken().execute(object : BaseSubscriber<String>(mView) {
            override fun onNext(t: String) {
                mView.onGetUploadToken(t)
            }
        }, lifecycleProvider)
    }

    fun editUserInfo(userIcon: String, userName: String, userGender: String, userSign: String) {
        if (!checkNetWork()) {
            mView.onError("网络不可用")
            return
        }
        mView.showLoading()

        userService.editUser(userIcon, userName, userGender, userSign).
                execute({ userInfo: UserInfo -> mView.onEditUserInfo(userInfo) },mView, lifecycleProvider)

    }
}