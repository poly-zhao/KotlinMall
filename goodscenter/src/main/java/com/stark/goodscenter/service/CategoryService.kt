package com.stark.goodscenter.service

import com.stark.goodscenter.data.protocal.Category
import rx.Observable

/**
 * Created by zhao on 2018-01-23.
 * Copyright (c) 2018 ejia7.com . All rights reserved.
 */
interface CategoryService {
    fun getCategory(parentId:Int): Observable<MutableList<Category>?>
}