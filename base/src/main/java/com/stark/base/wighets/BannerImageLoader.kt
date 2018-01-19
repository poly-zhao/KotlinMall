package com.stark.base.wighets

import android.content.Context
import android.widget.ImageView
import com.stark.base.utils.GlideUtils
import com.youth.banner.loader.ImageLoader

/**
 * Created by zhao on 2018-01-18.
 * Copyright (c) 2018 ejia7.com . All rights reserved.
 */
class BannerImageLoader : ImageLoader() {
    override fun displayImage(context: Context, path: Any, imageView: ImageView) {
        GlideUtils.loadImage(context, path.toString(), imageView)
        println(path)
    }
}