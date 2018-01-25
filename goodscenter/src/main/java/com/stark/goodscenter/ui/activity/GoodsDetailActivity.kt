package com.stark.goodscenter.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import com.stark.base.ui.activity.BaseActivity
import com.stark.goodscenter.R
import com.stark.goodscenter.ui.adapter.GoodsDetailVpAdapter
import kotlinx.android.synthetic.main.activity_goods_detail.*

class GoodsDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods_detail)
        initView()
    }

    private fun initView() {
        mGoodsDetailTab.tabMode = TabLayout.MODE_FIXED
        val adapter = GoodsDetailVpAdapter(supportFragmentManager, this)
        mGoodsDetailVp.adapter = adapter
        mGoodsDetailTab.setupWithViewPager(mGoodsDetailVp)
    }
}
