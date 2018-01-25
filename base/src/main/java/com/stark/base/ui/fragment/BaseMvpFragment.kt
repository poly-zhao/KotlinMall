package com.stark.base.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.stark.base.common.BaseApplication
import com.stark.base.injection.component.ActivityComponent
import com.stark.base.injection.component.DaggerActivityComponent
import com.stark.base.injection.module.ActivityModule
import com.stark.base.injection.module.LifecycleProviderModule
import com.stark.base.mvp.presenter.BasePresenter
import com.stark.base.mvp.view.BaseView
import com.stark.base.wighets.ProgressLoading
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject

/**
 * Created by zhao on 2018-01-11.
 * Copyright (c) 2018 ejia7.com . All rights reserved.
 */
abstract class BaseMvpFragment<T : BasePresenter<*>> : BaseFragment(), BaseView {
    @Inject
    lateinit var mPresenter: T

    lateinit var activityComponent: ActivityComponent

    private lateinit var mLoadingDialog: ProgressLoading

    override fun showLoading() {
        mLoadingDialog.showLoading()
    }

    override fun hideLoading() {
        mLoadingDialog.hideLoading()
    }

    override fun onError(msg: String) {
        toast(msg)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initActivityInjection()
        injectComponent()
        mLoadingDialog = ProgressLoading(context)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    abstract fun injectComponent()

    private fun initActivityInjection() {
        activityComponent = DaggerActivityComponent.builder().activityModule(ActivityModule(activity))
                .appComponent((activity.application as BaseApplication).appComponent)
                .lifecycleProviderModule(LifecycleProviderModule(this))
                .build()
    }
}