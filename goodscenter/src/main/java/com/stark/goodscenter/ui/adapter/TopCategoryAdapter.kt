package com.stark.goodscenter.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.stark.base.common.BaseApplication.Companion.context
import com.stark.base.ui.BaseRecyclerViewAdapter
import com.stark.goodscenter.R
import com.stark.goodscenter.data.protocal.Category
import kotlinx.android.synthetic.main.layout_top_category_item.view.*

/**
 * Created by zhao on 2018-01-23.
 * Copyright (c) 2018 ejia7.com . All rights reserved.
 */
class TopCategoryAdapter(context: Context) : BaseRecyclerViewAdapter<Category, TopCategoryAdapter.ViewHolder>(context) {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_top_category_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.itemView.mTopCategoryNameTv.text = dataList[position].categoryName
        holder.itemView.mTopCategoryNameTv.isSelected = dataList[position].isSelected
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {}
}