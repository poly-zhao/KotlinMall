package com.stark.base.ui.activity

import android.content.Context
import android.os.Bundle
import com.stark.base.common.BaseApplication
import com.stark.base.injection.component.ActivityComponent
import com.stark.base.injection.component.DaggerActivityComponent
import com.stark.base.injection.module.ActivityModule
import com.stark.base.injection.module.LifecycleProviderModule
import com.stark.base.mvp.presenter.BasePresenter
import com.stark.base.mvp.view.BaseView
import com.stark.base.wighets.ProgressLoading
import org.jetbrains.anko.toast
import javax.inject.Inject

/**
 * Created by zhao on 2018-01-11.
 * Copyright (c) 2018 ejia7.com . All rights reserved.
 */
abstract class BaseMvpActivity<T : BasePresenter<*>> : BaseActivity(), BaseView {
    @Inject
    lateinit var mPresenter: T

    lateinit var activityComponent: ActivityComponent

    lateinit var mProgressLoading: ProgressLoading

    @Inject
    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivityInject()
        injectComponent()
        mProgressLoading = ProgressLoading(this)
    }

    override fun showLoading() = mProgressLoading.showLoading()

    override fun hideLoading() = mProgressLoading.hideLoading()

    override fun onError(msg: String) = toast(msg)

    /*
    Dagger注册
    */
    protected abstract fun injectComponent()

    /*
       初始Activity Component
    */
    private fun initActivityInject() {
        activityComponent = DaggerActivityComponent.builder()
                .appComponent((application as BaseApplication).appComponent)
                .activityModule(ActivityModule(this))
                .lifecycleProviderModule(LifecycleProviderModule(this))
                .build()
    }


}