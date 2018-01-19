package com.stark.usercenter.data.respository

import com.stark.base.data.BaseResp
import com.stark.base.data.RetrofitFactory
import com.stark.usercenter.data.api.UploadApi
import rx.Observable
import javax.inject.Inject

/**
 * Created by zhao on 2018-01-17.
 * Copyright (c) 2018 ejia7.com . All rights reserved.
 */
class UploadRespository @Inject constructor() {

    fun getUploadToken(): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.create(UploadApi::class.java).getUploadToken()
    }
}