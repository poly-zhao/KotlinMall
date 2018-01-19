package com.stark.provider.common

import com.stark.base.common.BaseConstant
import com.stark.base.utils.AppPrefsUtils

/**
 * Created by zhao on 2018-01-19.
 * Copyright (c) 2018 ejia7.com . All rights reserved.
 */
fun isLogin():Boolean{
    return !AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN).isEmpty()
}