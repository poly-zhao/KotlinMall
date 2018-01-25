package com.stark.goodscenter.ui.fragment

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.ScaleAnimation
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.kotlin.goods.event.AddCartEvent
import com.stark.base.ui.fragment.BaseMvpFragment
import com.stark.base.utils.YuanFenConverter
import com.stark.base.wighets.BannerImageLoader
import com.stark.goodscenter.R
import com.stark.goodscenter.common.GoodsConstant
import com.stark.goodscenter.data.protocal.Goods
import com.stark.goodscenter.event.GoodsDetailImageEvent
import com.stark.goodscenter.event.SkuChangedEvent
import com.stark.goodscenter.injecttion.DaggerGoodsComponent
import com.stark.goodscenter.mvp.preseter.GoodsDetailPresenter
import com.stark.goodscenter.mvp.view.GoodsDetailView
import com.stark.goodscenter.wighet.GoodsSkuPopView
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_goods_detail_tab_one.*
import org.jetbrains.anko.support.v4.find

/**
 * Created by zhao on 2018-01-24.
 * Copyright (c) 2018 ejia7.com . All rights reserved.
 */
class GoodsDetailTabOneFragment : BaseMvpFragment<GoodsDetailPresenter>(), GoodsDetailView {
    private lateinit var mSkuPop: GoodsSkuPopView

    val mAnimationStart = ScaleAnimation(1f, 0.95f, 1f, 0.95f, 1f, 0.5f)
    val mAnimationEnd = ScaleAnimation(0.95f, 1f, 0.95f, 1f, 1f, 0.5f)

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
        initSkuPop()
        initObserve()
        initAnim()
    }

    private fun loadData() {
        mPresenter.getGoodsDetail(activity.intent.getIntExtra(GoodsConstant.KEY_GOODS_ID, -1))
    }

    /*
        初始化sku弹层
     */
    private fun initSkuPop() {
        mSkuPop = GoodsSkuPopView(activity)
        mSkuPop.setOnDismissListener {
            var view = find<View>(android.R.id.content)
            view?.animation = mAnimationEnd
            mAnimationStart.start()//            (activity as BaseActivity).contentView.startAnimation(mAnimationEnd)
        }
    }

    fun initView() {
        mGoodsDetailBanner.setImageLoader(BannerImageLoader())
        mGoodsDetailBanner.setBannerAnimation(Transformer.Accordion)
        mGoodsDetailBanner.setDelayTime(2000)
        //设置指示器位置（当banner模式中有指示器时）
        mGoodsDetailBanner.setIndicatorGravity(BannerConfig.RIGHT)

        mSkuView.setOnClickListener {
            mSkuPop.showAtLocation(view, Gravity.BOTTOM and Gravity.CENTER_HORIZONTAL, 0, 0)
            var view = find<View>(android.R.id.content)
            view?.animation = mAnimationStart
            mAnimationStart.start()
        }
    }


    /*
        加载SKU数据
     */
    private fun loadPopData(result: Goods) {
        mSkuPop.setGoodsIcon(result.goodsDefaultIcon)
        mSkuPop.setGoodsCode(result.goodsCode)
        mSkuPop.setGoodsPrice(result.goodsDefaultPrice)

        mSkuPop.setSkuData(result.goodsSku)

    }

    override fun onGetGoodsDetail(goods: Goods) {
        mGoodsDetailBanner.setImages(goods.goodsBanner.split(","))
        mGoodsDetailBanner.start()

        mGoodsDescTv.text = goods.goodsDesc
        mGoodsPriceTv.text = YuanFenConverter.changeF2YWithUnit(goods.goodsDefaultPrice)

        Bus.send(GoodsDetailImageEvent(goods.goodsDetailOne, goods.goodsDetailTwo))
        loadPopData(goods)
    }

    override fun injectComponent() {
        DaggerGoodsComponent.builder().activityComponent(activityComponent).build().inject(this)
        mPresenter.mView = this
    }

    private fun initAnim() {
        mAnimationStart.duration = 500
        mAnimationStart.fillAfter = true
        mAnimationEnd.duration = 500
        mAnimationEnd.fillAfter = true
    }

    private fun initObserve() {
        Bus.observe<SkuChangedEvent>()
                .subscribe { mSkuSelectedTv.text = mSkuPop.getSelectSku() + GoodsConstant.SKU_SEPARATOR + mSkuPop.getSelectCount() + "件" }
                .registerInBus(this)

        Bus.observe<AddCartEvent>()
                .subscribe {
//                    addCart()
                }.registerInBus(this)
    }
}