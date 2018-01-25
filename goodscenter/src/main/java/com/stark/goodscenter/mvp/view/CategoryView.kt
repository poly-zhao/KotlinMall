package com.stark.goodscenter.mvp.view

import com.stark.base.mvp.view.BaseView
import com.stark.goodscenter.data.protocal.Category

/*
    商品分类 视图回调
 */
interface CategoryView: BaseView {

    //获取商品分类列表
    fun onGetCategoryResult(result:MutableList<Category>?)
}