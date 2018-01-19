package com.stark.base.wighets

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.view.Gravity
import android.widget.ImageView
import com.stark.base.R
import org.jetbrains.anko.find

/**
 * Created by zhao on 2018-01-12.
 * Copyright (c) 2018 ejia7.com . All rights reserved.
 */
class ProgressLoading  constructor(context: Context, theme: Int=android.R.style.Theme_Dialog) : Dialog(context, theme) {

//    companion object {
        private var animDrawable: AnimationDrawable? = null

//        fun create(context: Context): ProgressLoading {
//            mDialog = ProgressLoading(context, android.R.style.Theme_Dialog)
//            mDialog.setContentView(R.layout.progress_dialog)
//
//            mDialog.setCancelable(true)
//            val attrs = mDialog.window.attributes
//            attrs.gravity = Gravity.CENTER
//            attrs.alpha = 0.2f
//            mDialog.window.attributes = attrs
//
//            animDrawable = mDialog.find<ImageView>(R.id.iv_loading).background as AnimationDrawable
//
//            return mDialog
////        }
//    }

    init {
//        mDialog = ProgressLoading(context, android.R.style.Theme_Dialog)
        setContentView(R.layout.progress_dialog)

        setCancelable(true)
        val attrs = window.attributes
        attrs.gravity = Gravity.CENTER
        attrs.alpha = 0.2f
        window.attributes = attrs

        animDrawable = find<ImageView>(R.id.iv_loading).background as AnimationDrawable
    }



    fun showLoading() {
        show()
        animDrawable?.start()
    }

    fun hideLoading() {
        super.dismiss()
        animDrawable?.stop()
    }
}