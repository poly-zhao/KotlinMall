package com.stark.goodscenter.injecttion

import com.stark.base.injection.component.ActivityComponent
import com.stark.base.injection.scope.PerComponentScope
import com.stark.goodscenter.ui.fragment.CategoryFragment
import dagger.Component

/**
 * Created by zhao on 2018-01-23.
 * Copyright (c) 2018 ejia7.com . All rights reserved.
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class))
interface CategoryComponent {
    fun inject(fragment:CategoryFragment)
}