package com.stark.usercenter.data.api

import com.stark.base.data.BaseResp
import com.stark.usercenter.data.protocal.*
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/**
 * Created by zhao on 2018-01-11.
 * Copyright (c) 2018 ejia7.com . All rights reserved.
 */
interface UserApi{

    /*
        用户注册
     */
    @POST("userCenter/register")
    fun register(@Body req: RegisterReq):Observable<BaseResp<String>>

    /**
     * 登录
     */
    @POST("userCenter/login")
    fun login(@Body req:LoginReq):Observable<BaseResp<UserInfo>>

    /*
        忘记密码
     */
    @POST("userCenter/forgetPwd")
    fun forgetPwd(@Body req:ForgetPwdReq):Observable<BaseResp<String>>

    /*
        重置密码
     */
    @POST("userCenter/resetPwd")
    fun resetPwd(@Body req: ResetPwdReq):Observable<BaseResp<String>>

    /*
        编辑用户资料
     */
    @POST("userCenter/editUser")
    fun editUser(@Body req:EditUserReq):Observable<BaseResp<UserInfo>>
}