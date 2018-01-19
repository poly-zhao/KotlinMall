package com.stark.base.injection.component

import android.app.Activity
import android.content.Context
import com.stark.base.injection.module.ActivityModule
import com.stark.base.injection.module.LifecycleProviderModule
import com.stark.base.injection.scope.ActivityScope
import com.trello.rxlifecycle.LifecycleProvider
import dagger.Component

/**
 * Created by zhao on 2018-01-12.
 * Copyright (c) 2018 ejia7.com . All rights reserved.
 */

@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(
        LifecycleProviderModule::class, ActivityModule::class))
interface ActivityComponent {
    fun activity(): Activity
    fun context(): Context
    fun lifecycleProvider(): LifecycleProvider<*>
}