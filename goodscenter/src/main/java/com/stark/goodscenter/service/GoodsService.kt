package com.stark.goodscenter.service

import com.stark.goodscenter.data.protocal.Goods
import rx.Observable

/**
 * Created by zhao on 2018-01-23.
 * Copyright (c) 2018 ejia7.com . All rights reserved.
 */
interface GoodsService {

    fun getGoodsList(categoryId: Int, pageNo: Int): Observable<MutableList<Goods>?>
    fun getGoodsListByKeyword(keyword: String, pageNo: Int): Observable<MutableList<Goods>?>

    fun getGoodsDetail(goodsId: Int): Observable<Goods>
}