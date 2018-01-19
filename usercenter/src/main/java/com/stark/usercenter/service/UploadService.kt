package com.stark.usercenter.service

import rx.Observable

/**
 * Created by zhao on 2018-01-17.
 * Copyright (c) 2018 ejia7.com . All rights reserved.
 */
interface UploadService {

    fun getUploadToken(): Observable<String>
}