package com.stark.base.data

/**
 * Created by zhao on 2018-01-11.
 * Copyright (c) 2018 ejia7.com . All rights reserved.
 */
data class BaseResp<T>(var status:Int, var message:String,var data:T)