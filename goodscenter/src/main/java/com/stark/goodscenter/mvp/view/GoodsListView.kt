package com.stark.goodscenter.mvp.view

import com.stark.base.mvp.view.BaseView
import com.stark.goodscenter.data.protocal.Goods

/**
 * Created by zhao on 2018-01-23.
 * Copyright (c) 2018 ejia7.com . All rights reserved.
 */
interface GoodsListView :BaseView {
    fun onGoodsListResult(result:MutableList<Goods>?)
}