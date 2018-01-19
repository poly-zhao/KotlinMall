package com.stark.usercenter.service.impl

import com.stark.base.extend.convert
import com.stark.usercenter.data.respository.UploadRespository
import com.stark.usercenter.service.UploadService
import rx.Observable
import javax.inject.Inject

/**
 * Created by zhao on 2018-01-17.
 * Copyright (c) 2018 ejia7.com . All rights reserved.
 */
class UploadServiceImpl @Inject constructor():UploadService {
    override fun getUploadToken(): Observable<String> {
        return UploadRespository().getUploadToken().convert()
    }
}