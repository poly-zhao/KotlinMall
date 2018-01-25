package com.stark.goodscenter.service.impl

import com.stark.base.extend.convert
import com.stark.goodscenter.data.protocal.Category
import com.stark.goodscenter.data.respository.CategoryRepository
import com.stark.goodscenter.service.CategoryService
import rx.Observable
import javax.inject.Inject

/**
 * Created by zhao on 2018-01-23.
 * Copyright (c) 2018 ejia7.com . All rights reserved.
 */

class CategoryServiceImpl @Inject constructor() :CategoryService {
    override fun getCategory(parentId: Int): Observable<MutableList<Category>?> {
        return CategoryRepository().getCategory(parentId).convert()
    }
}