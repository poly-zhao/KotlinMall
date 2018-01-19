package com.stark.kotlinmall.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.stark.base.common.BaseApplication.Companion.context
import com.stark.base.ui.BaseRecyclerViewAdapter
import com.stark.base.utils.GlideUtils
import com.stark.kotlinmall.R
import kotlinx.android.synthetic.main.layout_home_discount_item.view.*

/**
 * Created by zhao on 2018-01-18.
 * Copyright (c) 2018 ejia7.com . All rights reserved.
 */
class HomeDiscountAdapter(context:Context): BaseRecyclerViewAdapter<String, HomeDiscountAdapter.ViewHolder>(context) {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_home_discount_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        GlideUtils.loadUrlImage(context, dataList[position],holder.itemView.mGoodsIconIv)
        holder.itemView.mDiscountAfterTv.text = "￥123.00"
        holder.itemView.mDiscountBeforeTv.text = "$1000.00"
    }


    class ViewHolder(view:View) : RecyclerView.ViewHolder(view) {
        init {

        }
    }
}