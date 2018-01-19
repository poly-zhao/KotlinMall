package com.stark.kotlinmall.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.stark.base.ui.fragment.BaseFragment
import com.stark.base.wighets.BannerImageLoader
import com.stark.kotlinmall.*
import com.stark.kotlinmall.ui.adapter.HomeDiscountAdapter
import com.stark.kotlinmall.ui.adapter.TopicAdapter
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_home.*
import me.crosswall.lib.coverflow.CoverFlow

/**
 * Created by zhao on 2018-01-18.
 * Copyright (c) 2018 ejia7.com . All rights reserved.
 */
class HomeFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initBanner()
        initNews()
        initDiscount()
        initTopic()
    }

    private fun initTopic() {
        mTopicContainer.setOverlapEnabled(true)
        mTopicContainer.clipChildren = false
        mTopicPager.adapter = TopicAdapter(context, listOf(HOME_TOPIC_ONE, HOME_TOPIC_TWO, HOME_TOPIC_THREE, HOME_TOPIC_FOUR, HOME_TOPIC_FIVE))
        mTopicPager.currentItem = 1
        mTopicPager.offscreenPageLimit = 5
        mTopicContainer.clipChildren = false
        CoverFlow.Builder().with(mTopicPager).scale(0.3f).pagerMargin(-30.0f).spaceSize(0.0f).build()
        mTopicContainer.clipChildren = false
        mTopicPager.post {
            mTopicPager.invalidate()
        }
    }

    override fun onStop() {
        super.onStop()
        println("HomeFragment")
    }

    private fun initDiscount() {
        val list = mutableListOf<String>(HOME_DISCOUNT_ONE, HOME_DISCOUNT_TWO, HOME_DISCOUNT_THREE, HOME_DISCOUNT_FOUR, HOME_DISCOUNT_FIVE)
        val manager = LinearLayoutManager(context)
        manager.orientation = LinearLayoutManager.HORIZONTAL
        mHomeDiscountRv.layoutManager = manager
        val adapter = HomeDiscountAdapter(context)
        mHomeDiscountRv.adapter = adapter
        adapter.setData(list)
    }

    fun initBanner() {
        mHomeBanner.isAutoPlay(true)
        mHomeBanner.setImageLoader(BannerImageLoader())
        mHomeBanner.setImages(listOf(HOME_BANNER_ONE, HOME_BANNER_TWO, HOME_BANNER_THREE, HOME_BANNER_FOUR))
        mHomeBanner.setBannerAnimation(Transformer.Default)
        mHomeBanner.setIndicatorGravity(Gravity.CENTER)
        mHomeBanner.start()
    }

    fun initNews() {
        mNewFlipperView.setData(arrayOf("夏日炎炎，第一波福利还有30秒到达战场", "新用户立领1000元优惠券"))
    }
}