package com.stark.goodscenter.ui.fragment

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kennyc.view.MultiStateView
import com.stark.base.extend.startLoading
import com.stark.base.ui.BaseRecyclerViewAdapter
import com.stark.base.ui.fragment.BaseMvpFragment
import com.stark.goodscenter.R
import com.stark.goodscenter.common.GoodsConstant
import com.stark.goodscenter.data.protocal.Category
import com.stark.goodscenter.injecttion.DaggerCategoryComponent
import com.stark.goodscenter.mvp.preseter.CategoryPresenter
import com.stark.goodscenter.mvp.view.CategoryView
import com.stark.goodscenter.ui.activity.GoodsListActivity
import com.stark.goodscenter.ui.adapter.SecondCategoryAdapter
import com.stark.goodscenter.ui.adapter.TopCategoryAdapter
import kotlinx.android.synthetic.main.fragment_category.*
import org.jetbrains.anko.support.v4.startActivity

/**
 * Created by zhao on 2018-01-23.
 * Copyright (c) 2018 ejia7.com . All rights reserved.
 */
class CategoryFragment : BaseMvpFragment<CategoryPresenter>(), CategoryView {

    lateinit var topCategoryAdapter: TopCategoryAdapter
    lateinit var secondeAdapter: SecondCategoryAdapter

    override fun injectComponent() {
        DaggerCategoryComponent.builder().activityComponent(activityComponent).build().inject(this)
        mPresenter.mView = this
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater?.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        initView()
        loadData()
    }


    private fun initView() {
        mTopCategoryRv.layoutManager = LinearLayoutManager(context)
        topCategoryAdapter = TopCategoryAdapter(context)
        mTopCategoryRv.adapter = topCategoryAdapter
        topCategoryAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<Category> {
            override fun onItemClick(item: Category, position: Int) {
                for (category in topCategoryAdapter.dataList) {
                    category.isSelected = category.id == item.id
                }
                loadData(item.id)
                topCategoryAdapter.notifyDataSetChanged()
            }
        })

        mSecondCategoryRv.layoutManager = GridLayoutManager(context, 3)
        secondeAdapter = SecondCategoryAdapter(context)
        mSecondCategoryRv.adapter = secondeAdapter
        secondeAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<Category> {
            override fun onItemClick(item: Category, position: Int) {
                startActivity<GoodsListActivity>(GoodsConstant.KEY_CATEGORY_ID to item.id)
            }
        })
    }

    private fun loadData(parentId: Int = 0) {
        if (parentId != 0) {
            mMultiStateView.startLoading()
        }
        mPresenter.getCategory(parentId)
    }

    override fun onGetCategoryResult(result: MutableList<Category>?) {
        if (result != null && result.size > 0) {
            if (result[0].parentId == 0) {
                result[0].isSelected = true
                topCategoryAdapter.setData(result)
                mPresenter.getCategory(result[0].id)
            } else {
                secondeAdapter.setData(result)

                mMultiStateView.viewState = if (result.size != 0) {
                    MultiStateView.VIEW_STATE_CONTENT
                } else MultiStateView.VIEW_STATE_EMPTY
                mTopCategoryIv.visibility = View.VISIBLE
                mCategoryTitleTv.visibility = View.VISIBLE
                mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
            }
        } else {
            mTopCategoryIv.visibility = View.INVISIBLE
            mCategoryTitleTv.visibility = View.INVISIBLE
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }

    }

}