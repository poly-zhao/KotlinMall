package com.stark.usercenter.service.impl

import com.stark.base.extend.convert
import com.stark.base.extend.convertToBoolean
import com.stark.usercenter.data.protocal.UserInfo
import com.stark.usercenter.data.respository.UserRespository
import com.stark.usercenter.service.UserService
import rx.Observable
import javax.inject.Inject

/**
 * Created by zhao on 2018-01-11.
 * Copyright (c) 2018 ejia7.com . All rights reserved.
 */
class UserServiceImpl @Inject constructor() : UserService {
    override fun editUser(userIcon: String, userName: String, userGender: String, userSign: String):
            Observable<UserInfo> {
        return UserRespository().editUserInfo(userIcon, userName, userGender, userSign).convert()
    }

    override fun resetPwd(mobile: String, pwd: String): Observable<Boolean> {
        return UserRespository().resetPwd(mobile, pwd).convertToBoolean()
    }

    override fun forgetPwd(tel: String, verifyCode: String): Observable<Boolean> {
        return UserRespository().forgetPwd(tel, verifyCode).convertToBoolean()
    }

    override fun register(mobile: String, verifyCode: String, pwd: String): Observable<Boolean> {
        return UserRespository().register(mobile, verifyCode, pwd).convertToBoolean()
    }

    override fun login(tel: String, pwd: String, pushID: String): Observable<UserInfo> {
        return UserRespository().login(tel, pwd, pushID).convert()
    }
}




