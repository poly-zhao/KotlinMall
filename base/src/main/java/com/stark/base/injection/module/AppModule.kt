package com.stark.base.injection.module

import android.content.Context
import com.stark.base.common.BaseApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by zhao on 2018-01-16.
 * Copyright (c) 2018 ejia7.com . All rights reserved.
 */
@Module
class AppModule(val context: BaseApplication) {
    @Provides
    @Singleton
    fun context():Context{
        return context
    }

}