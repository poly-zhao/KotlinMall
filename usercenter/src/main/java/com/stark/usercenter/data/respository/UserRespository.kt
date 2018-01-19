package com.stark.usercenter.data.respository

import com.stark.base.data.BaseResp
import com.stark.base.data.RetrofitFactory
import com.stark.usercenter.data.api.UserApi
import com.stark.usercenter.data.protocal.*
import rx.Observable

/**
 * Created by zhao on 2018-01-11.
 * Copyright (c) 2018 ejia7.com . All rights reserved.
 */
class UserRespository {
    fun register(mobile: String, verifyCode: String, pwd: String): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.create(UserApi::class.java).register(RegisterReq(mobile, verifyCode, pwd))
    }

    fun login(tel: String, pwd: String, pushID: String): Observable<BaseResp<UserInfo>> {
        return RetrofitFactory.instance.create(UserApi::class.java).login(LoginReq(tel, pwd, pushID))
    }

    fun forgetPwd(tel: String, verifyCode: String): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.create(UserApi::class.java).forgetPwd(ForgetPwdReq(tel, verifyCode))
    }

    fun resetPwd(tel: String, pwd: String): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.create(UserApi::class.java).resetPwd(ResetPwdReq(tel, pwd))
    }

    fun editUserInfo(userIcon: String, userName: String, gender: String, sign: String):Observable<BaseResp<UserInfo>> {
        return RetrofitFactory.instance.create(UserApi::class.java).editUser(EditUserReq(userIcon,
                userName,gender,sign))
    }
}