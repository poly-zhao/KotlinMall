package com.stark.goodscenter.data.respository

import com.stark.base.data.BaseResp
import com.stark.base.data.RetrofitFactory
import com.stark.goodscenter.data.api.CategoryApi
import com.stark.goodscenter.data.protocal.Category
import com.stark.goodscenter.data.protocal.GetCategoryReq
import rx.Observable

/**
 * Created by zhao on 2018-01-23.
 * Copyright (c) 2018 ejia7.com . All rights reserved.
 */
class CategoryRepository {
    fun getCategory(parentId:Int):Observable<BaseResp<MutableList<Category>?>>{
        return RetrofitFactory.instance.create(CategoryApi::class.java).getCategory(GetCategoryReq(parentId))
    }
}