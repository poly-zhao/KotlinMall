package com.stark.usercenter.data.protocal

/**
 * Created by zhao on 2018-01-16.
 * Copyright (c) 2018 ejia7.com . All rights reserved.
 */
/*
    重设密码请求体
 */
data class ResetPwdReq(val mobile:String, val pwd:String)