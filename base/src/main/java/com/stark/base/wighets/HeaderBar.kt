package com.stark.base.wighets

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.stark.base.R
import kotlinx.android.synthetic.main.layout_header_bar.view.*

/**
 * Created by zhao on 2018-01-12.
 * Copyright (c) 2018 ejia7.com . All rights reserved.
 */
class HeaderBar @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var isShowBack = true
    private var titleText: String? = null
    private var rightText: String? = null

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.HeaderBar)
        isShowBack = typedArray.getBoolean(R.styleable.HeaderBar_isShowBack, true)
        titleText = typedArray.getString(R.styleable.HeaderBar_titleText)
        rightText = typedArray.getString(R.styleable.HeaderBar_rightText)

        initView()
        typedArray.recycle()
    }

    private fun initView() {
        //将填充的view, add到第三个参数中
        View.inflate(context, R.layout.layout_header_bar, this)

        mLeftIv.visibility = if (isShowBack) View.VISIBLE else View.GONE

        //如果titleText==null, 返回""
        mTitleTv.text = titleText ?: ""
        //如果rightText!=null, 执行let
        rightText?.let {
            mRightTv.text = it
            mRightTv.visibility = View.VISIBLE
        }

        mLeftIv.setOnClickListener {
            if (context is Activity) {
                (context as Activity).finish()
            }
        }
    }

    /*
        获取左侧视图
     */
    fun getLeftView(): ImageView = mLeftIv

    /*
        获取右侧视图
     */
    fun getRightView(): TextView {
        return mRightTv
    }

    /*
        获取右侧文字
     */
    fun getRightText(): String {
        return mRightTv.text.toString()
    }
}
