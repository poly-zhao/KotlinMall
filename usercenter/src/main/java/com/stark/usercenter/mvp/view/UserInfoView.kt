package com.stark.usercenter.mvp.view

import com.stark.base.mvp.view.BaseView
import com.stark.usercenter.data.protocal.UserInfo

/**
 * Created by zhao on 2018-01-17.
 * Copyright (c) 2018 ejia7.com . All rights reserved.
 */
interface UserInfoView:BaseView {

    fun onGetUploadToken(token:String)

    fun onEditUserInfo(result: UserInfo)
}