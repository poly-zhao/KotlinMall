package com.stark.goodscenter.mvp.preseter

import com.stark.base.extend.execute
import com.stark.base.mvp.presenter.BasePresenter
import com.stark.base.rx.BaseSubscriber
import com.stark.goodscenter.data.protocal.Goods
import com.stark.goodscenter.mvp.view.GoodsListView
import com.stark.goodscenter.service.impl.GoodsServiceImpl
import javax.inject.Inject

/**
 * Created by zhao on 2018-01-23.
 * Copyright (c) 2018 ejia7.com . All rights reserved.
 */
class GoodsListPresenter @Inject constructor() : BasePresenter<GoodsListView>() {
    @Inject
    lateinit var goodsListService: GoodsServiceImpl

    fun getGoodsList(categoryId: Int, pageNo: Int) {
        goodsListService.getGoodsList(categoryId, pageNo).execute(object : BaseSubscriber<MutableList<Goods>?>(mView) {
            override fun onNext(t: MutableList<Goods>?) {
                mView.onGoodsListResult(t)
            }
        }, lifecycleProvider)
    }

    fun getGoodsListByKeyword(keyword: String, pageNo: Int) {
        goodsListService.getGoodsListByKeyword(keyword, pageNo).execute(object : BaseSubscriber<MutableList<Goods>?>(mView) {
            override fun onNext(t: MutableList<Goods>?) {
                mView.onGoodsListResult(t)
            }
        }, lifecycleProvider)
    }
}