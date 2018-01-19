package com.stark.usercenter.service

import com.stark.usercenter.data.protocal.UserInfo
import rx.Observable

/**
 * Created by zhao on 2018-01-11.
 * Copyright (c) 2018 ejia7.com . All rights reserved.
 */
interface UserService {
    fun register(mobile: String, verifyCode: String, pwd: String): Observable<Boolean>

    fun login(tel:String, pwd:String,pushID: String):Observable<UserInfo>

    fun forgetPwd(tel:String, verifyCode:String):Observable<Boolean>

    fun resetPwd(mobile: String, pwd: String): Observable<Boolean>
    //编辑用户资料
    fun editUser(userIcon:String,userName:String,userGender:String,userSign:String):Observable<UserInfo>
}