package com.stark.goodscenter.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eightbitlab.rxbus.Bus
import com.stark.base.ui.fragment.BaseMvpFragment
import com.stark.base.utils.YuanFenConverter
import com.stark.base.wighets.BannerImageLoader
import com.stark.goodscenter.R
import com.stark.goodscenter.common.GoodsConstant
import com.stark.goodscenter.data.protocal.Goods
import com.stark.goodscenter.event.GoodsDetailImageEvent
import com.stark.goodscenter.injecttion.DaggerGoodsComponent
import com.stark.goodscenter.mvp.preseter.GoodsDetailPresenter
import com.stark.goodscenter.mvp.view.GoodsDetailView
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_goods_detail_tab_one.*

/**
 * Created by zhao on 2018-01-24.
 * Copyright (c) 2018 ejia7.com . All rights reserved.
 */
class GoodsDetailTabOneFragment : BaseMvpFragment<GoodsDetailPresenter>(), GoodsDetailView {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater?.inflate(R.layout.fragment_goods_detail_tab_one, container!!, false)
//        var tv = TextView(context)
//        tv.setTextSize(30f)
//        tv.setText("GoodsDetailTabOneFragment")
//        return tv
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        loadData()
    }

    private fun loadData() {
        mPresenter.getGoodsDetail(activity.intent.getIntExtra(GoodsConstant.KEY_GOODS_ID, -1))
    }

    fun initView() {
        mGoodsDetailBanner.setImageLoader(BannerImageLoader())
        mGoodsDetailBanner.setBannerAnimation(Transformer.Accordion)
        mGoodsDetailBanner.setDelayTime(2000)
        //设置指示器位置（当banner模式中有指示器时）
        mGoodsDetailBanner.setIndicatorGravity(BannerConfig.RIGHT)
    }

    override fun onGetGoodsDetail(goods: Goods) {
        mGoodsDetailBanner.setImages(goods.goodsBanner.split(","))
        mGoodsDetailBanner.start()

        mGoodsDescTv.text = goods.goodsDesc
        mGoodsPriceTv.text = YuanFenConverter.changeF2YWithUnit(goods.goodsDefaultPrice)

        Bus.send(GoodsDetailImageEvent(goods.goodsDetailOne, goods.goodsDetailTwo))
    }

    override fun injectComponent() {
        DaggerGoodsComponent.builder().activityComponent(activityComponent).build().inject(this)
        mPresenter.mView = this
    }


}