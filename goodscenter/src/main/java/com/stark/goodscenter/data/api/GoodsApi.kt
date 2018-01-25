package com.stark.goodscenter.data.api

import com.stark.base.data.BaseResp
import com.stark.goodscenter.data.protocal.GetGoodsDetailReq
import com.stark.goodscenter.data.protocal.GetGoodsListByKeywordReq
import com.stark.goodscenter.data.protocal.GetGoodsListReq
import com.stark.goodscenter.data.protocal.Goods
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/**
 * Created by zhao on 2018-01-23.
 * Copyright (c) 2018 ejia7.com . All rights reserved.
 */
interface GoodsApi {
    @POST("goods/getGoodsList")
    fun getGoodsList(@Body req:GetGoodsListReq):Observable<BaseResp<MutableList<Goods>?>>

    /*
      获取商品列表
   */
    @POST("goods/getGoodsListByKeyword")
    fun getGoodsListByKeyword(@Body req: GetGoodsListByKeywordReq): Observable<BaseResp<MutableList<Goods>?>>

    /*
    获取商品详情
 */
    @POST("goods/getGoodsDetail")
    fun getGoodsDetail(@Body req: GetGoodsDetailReq): Observable<BaseResp<Goods>>

}