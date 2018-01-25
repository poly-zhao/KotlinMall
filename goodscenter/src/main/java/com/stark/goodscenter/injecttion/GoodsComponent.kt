package com.stark.goodscenter.injecttion

import com.stark.base.injection.component.ActivityComponent
import com.stark.base.injection.scope.PerComponentScope
import com.stark.goodscenter.ui.activity.GoodsListActivity
import com.stark.goodscenter.ui.fragment.GoodsDetailTabOneFragment
import dagger.Component

/**
 * Created by zhao on 2018-01-24.
 * Copyright (c) 2018 ejia7.com . All rights reserved.
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class))
interface GoodsComponent {
    fun inject(activity: GoodsListActivity)
    fun inject(fragment: GoodsDetailTabOneFragment)
}