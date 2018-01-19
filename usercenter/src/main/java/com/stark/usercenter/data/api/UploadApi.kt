package com.stark.usercenter.data.api

import com.stark.base.data.BaseResp
import retrofit2.http.POST
import rx.Observable

/**
 * Created by zhao on 2018-01-17.
 * Copyright (c) 2018 ejia7.com . All rights reserved.
 */
/*
    上传相关 接口
 */
interface UploadApi {

    /*
        获取七牛云上传凭证
     */
    @POST("common/getUploadToken")
    fun getUploadToken(): Observable<BaseResp<String>>
}