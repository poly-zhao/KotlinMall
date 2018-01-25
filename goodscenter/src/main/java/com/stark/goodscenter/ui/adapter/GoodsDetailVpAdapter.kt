package com.stark.goodscenter.ui.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.stark.goodscenter.ui.fragment.GoodsDetailTabOneFragment
import com.stark.goodscenter.ui.fragment.GoodsDetailTabTwoFragment

/**
 * Created by zhao on 2018-01-24.
 * Copyright (c) 2018 ejia7.com . All rights reserved.
 */
class GoodsDetailVpAdapter(fm: FragmentManager, context: Context) : FragmentStatePagerAdapter(fm) {
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return if (position == 0) GoodsDetailTabOneFragment() else GoodsDetailTabTwoFragment()
    }

    override fun getPageTitle(position: Int): CharSequence {
        return if (position == 0) "商品" else "详情"
    }
}