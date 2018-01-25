package com.stark.goodscenter.service.impl

import com.stark.base.extend.convert
import com.stark.goodscenter.data.protocal.Goods
import com.stark.goodscenter.data.respository.GoodsRepository
import com.stark.goodscenter.service.GoodsService
import rx.Observable
import javax.inject.Inject

/**
 * Created by zhao on 2018-01-23.
 * Copyright (c) 2018 ejia7.com . All rights reserved.
 */
class GoodsServiceImpl @Inject constructor() : GoodsService {
    override fun getGoodsDetail(goodsId: Int): Observable<Goods> {
        return GoodsRepository().getGoodsDetail(goodsId).convert()
    }

    override fun getGoodsListByKeyword(keyword: String, pageNo: Int): Observable<MutableList<Goods>?> {
        return GoodsRepository().getGoodsListByKeyword(keyword, pageNo).convert()
    }

    override fun getGoodsList(categoryId: Int, pageNo: Int): Observable<MutableList<Goods>?> {
        return GoodsRepository().getGoodsList(categoryId, pageNo).convert()
    }
}