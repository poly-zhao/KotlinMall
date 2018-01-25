package com.stark.goodscenter.mvp.preseter

import com.stark.base.extend.execute
import com.stark.base.mvp.presenter.BasePresenter
import com.stark.base.rx.BaseSubscriber
import com.stark.goodscenter.data.protocal.Goods
import com.stark.goodscenter.mvp.view.GoodsDetailView
import com.stark.goodscenter.service.impl.GoodsServiceImpl
import javax.inject.Inject

/**
 * Created by zhao on 2018-01-24.
 * Copyright (c) 2018 ejia7.com . All rights reserved.
 */
class GoodsDetailPresenter @Inject constructor(): BasePresenter<GoodsDetailView>() {
    @Inject
    lateinit var goodsService: GoodsServiceImpl

    fun getGoodsDetail(goodsId: Int) {
        goodsService.getGoodsDetail(goodsId).execute(object : BaseSubscriber<Goods>(mView) {
            override fun onNext(t: Goods?) {
                mView.onGetGoodsDetail(t!!)
            }
        }, lifecycleProvider)
    }
}