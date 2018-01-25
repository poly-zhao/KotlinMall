package com.stark.goodscenter.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.stark.base.extend.loadUrl
import com.stark.base.ui.fragment.BaseMvpFragment
import com.stark.goodscenter.R.layout
import com.stark.goodscenter.event.GoodsDetailImageEvent
import com.stark.goodscenter.mvp.preseter.GoodsDetailPresenter
import kotlinx.android.synthetic.main.fragment_goods_detail_tab_two.*
import rx.functions.Action1

/**
 * Created by zhao on 2018-01-24.
 * Copyright (c) 2018 ejia7.com . All rights reserved.
 */
class GoodsDetailTabTwoFragment : BaseMvpFragment<GoodsDetailPresenter>() {
    override fun injectComponent() {


    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater?.inflate(layout.fragment_goods_detail_tab_two, container, false)
//        val tv = TextView(context)
//        tv.setTextSize(30f)
//        tv.setText("GoodsDetailTabTwoFragment")
//        return tv
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserve()
    }

    /*
    初始化监听，商品详情获取成功后，加载当前页面
 */
    private fun initObserve() {
        Bus.observe<GoodsDetailImageEvent>()
                .subscribe(object : Action1<GoodsDetailImageEvent> {
                    override fun call(t: GoodsDetailImageEvent) {
                        mGoodsDetailOneIv.loadUrl(t.imgOne)
                        mGoodsDetailTwoIv.loadUrl(t.imgTwo)
                    }
                })
                .registerInBus(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }
}