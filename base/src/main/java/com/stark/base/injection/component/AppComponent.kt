package com.stark.base.injection.component

import android.content.Context
import com.stark.base.injection.module.AppModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by zhao on 2018-01-16.
 * Copyright (c) 2018 ejia7.com . All rights reserved.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun context(): Context
}