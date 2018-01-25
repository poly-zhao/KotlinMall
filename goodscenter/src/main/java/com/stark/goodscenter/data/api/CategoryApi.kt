package com.stark.goodscenter.data.api

import com.stark.base.data.BaseResp
import com.stark.goodscenter.data.protocal.Category
import com.stark.goodscenter.data.protocal.GetCategoryReq
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/*
    商品分类接口
 */
interface CategoryApi {
    /*
        获取商品分类列表
     */
    @POST("category/getCategory")
    fun getCategory(@Body req:GetCategoryReq): Observable<BaseResp<MutableList<Category>?>>
}