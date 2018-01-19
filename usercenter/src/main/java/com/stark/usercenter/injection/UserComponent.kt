package com.stark.usercenter.injection

import com.stark.base.injection.component.ActivityComponent
import com.stark.base.injection.scope.PerComponentScope
import com.stark.usercenter.activity.*
import dagger.Component

/**
 * Created by zhao on 2018-01-12.
 * Copyright (c) 2018 ejia7.com . All rights reserved.

 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class))
interface UserComponent {
    fun inject(activity: RegisterActivity)
    fun inject(activity: LoginActivity)
    fun inject(activity: ForgetPwdActivity)
    fun inject(activity: ResetPwdActivity)
    fun inject(activity: UserInfoActivity)
}