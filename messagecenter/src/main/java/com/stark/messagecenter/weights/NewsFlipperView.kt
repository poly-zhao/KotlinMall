package com.stark.messagecenter.weights

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.ViewFlipper
import com.stark.messagecenter.R
import org.jetbrains.anko.find

/**
 * Created by zhao on 2018-01-18.
 * Copyright (c) 2018 ejia7.com . All rights reserved.
 */
class NewsFlipperView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var mFlipperView: ViewFlipper

    init {
        val view = View.inflate(context, R.layout.layout_news_flipper, null)
        mFlipperView = view.find(R.id.mFlipperView)
        mFlipperView.setInAnimation(context, R.anim.news_in)
        mFlipperView.setOutAnimation(context, R.anim.news_out)
        addView(view)
    }

    private fun buildNewsView(text: String): View {
        val tv = TextView(context)
        tv.textSize = 12f
        tv.text = text
        tv.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, -2)
        return tv
    }

    fun setData(data: Array<String>) {
        for (s in data) {
            mFlipperView.addView(buildNewsView(s))
        }
        mFlipperView.startFlipping()
    }
}