package com.stark.goodscenter.mvp.preseter

import com.stark.base.extend.execute
import com.stark.base.mvp.presenter.BasePresenter
import com.stark.base.rx.BaseSubscriber
import com.stark.goodscenter.data.protocal.Category
import com.stark.goodscenter.mvp.view.CategoryView
import com.stark.goodscenter.service.impl.CategoryServiceImpl
import javax.inject.Inject

/**
 * Created by zhao on 2018-01-23.
 * Copyright (c) 2018 ejia7.com . All rights reserved.
 */
class CategoryPresenter @Inject constructor() : BasePresenter<CategoryView>() {

    @Inject
    lateinit var categoryService: CategoryServiceImpl

    fun getCategory(parentId: Int) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        categoryService.getCategory(parentId).execute(object : BaseSubscriber<MutableList<Category>?>(mView) {
            override fun onNext(t: MutableList<Category>?) {
                mView.onGetCategoryResult(t)
            }
        }, lifecycleProvider)
    }
}