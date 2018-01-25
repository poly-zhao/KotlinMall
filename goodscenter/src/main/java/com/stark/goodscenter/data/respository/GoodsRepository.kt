package com.stark.goodscenter.data.respository

import com.stark.base.data.BaseResp
import com.stark.base.data.RetrofitFactory
import com.stark.goodscenter.data.api.GoodsApi
import com.stark.goodscenter.data.protocal.GetGoodsDetailReq
import com.stark.goodscenter.data.protocal.GetGoodsListByKeywordReq
import com.stark.goodscenter.data.protocal.GetGoodsListReq
import com.stark.goodscenter.data.protocal.Goods
import rx.Observable

/**
 * Created by zhao on 2018-01-23.
 * Copyright (c) 2018 ejia7.com . All rights reserved.
 */
class GoodsRepository {

    fun getGoodsList(categoryId: Int, pageNo: Int): Observable<BaseResp<MutableList<Goods>?>> {
        return RetrofitFactory.instance.create(GoodsApi::class.java).getGoodsList(GetGoodsListReq(categoryId, pageNo))
    }

    fun getGoodsListByKeyword(Keyword: String, pageNo: Int): Observable<BaseResp<MutableList<Goods>?>> {
        return RetrofitFactory.instance.create(GoodsApi::class.java).getGoodsListByKeyword(GetGoodsListByKeywordReq(Keyword, pageNo))
    }

    fun getGoodsDetail(goodsId: Int): Observable<BaseResp<Goods>> {
        return RetrofitFactory.instance.create(GoodsApi::class.java).getGoodsDetail(GetGoodsDetailReq(goodsId))
    }
}