package com.stark.goodscenter.ui.activity

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.kennyc.view.MultiStateView
import com.stark.base.extend.startLoading
import com.stark.base.ui.BaseRecyclerViewAdapter
import com.stark.base.ui.activity.BaseMvpActivity
import com.stark.goodscenter.R
import com.stark.goodscenter.common.GoodsConstant
import com.stark.goodscenter.data.protocal.Goods
import com.stark.goodscenter.injecttion.DaggerGoodsComponent
import com.stark.goodscenter.mvp.preseter.GoodsListPresenter
import com.stark.goodscenter.mvp.view.GoodsListView
import com.stark.goodscenter.ui.adapter.GoodsAdapter
import kotlinx.android.synthetic.main.activity_goods_list.*
import org.jetbrains.anko.startActivity


class GoodsListActivity : BaseMvpActivity<GoodsListPresenter>(), GoodsListView {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods_list)
        initView()
        loadData()
    }

    private fun loadData() {
/*
*                 GoodsConstant.KEY_SEARCH_GOODS_TYPE to GoodsConstant.SEARCH_GOODS_TYPE_KEYWORD,
                GoodsConstant.KEY_GOODS_KEYWORD to value*/
        if (intent.getIntExtra(GoodsConstant.KEY_SEARCH_GOODS_TYPE, 0) == 1) {
            mMultiStateView.startLoading()
            mPresenter.getGoodsListByKeyword(intent.getStringExtra(GoodsConstant.KEY_GOODS_KEYWORD), 1)
        } else {
            mMultiStateView.startLoading()
            mPresenter.getGoodsList(intent.getIntExtra(GoodsConstant.KEY_CATEGORY_ID, 1), 1)
        }
    }

    lateinit var adapter: GoodsAdapter

    private fun initView() {
        mGoodsRv.layoutManager = GridLayoutManager(context, 2)
        adapter = GoodsAdapter(context)
        mGoodsRv.adapter = adapter

        adapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<Goods> {
            override fun onItemClick(item: Goods, position: Int) {
                startActivity<GoodsDetailActivity>(GoodsConstant.KEY_GOODS_ID to item.id)
            }
        })
    }

    override fun injectComponent() {
        DaggerGoodsComponent.builder().activityComponent(activityComponent).build().inject(this)
        mPresenter.mView = this
    }

    override fun onGoodsListResult(result: MutableList<Goods>?) {
        if (result != null && result!!.size > 0) {
            adapter.setData(result)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }
}
