package com.stark.base.injection.module

import android.app.Activity
import com.stark.base.injection.scope.ActivityScope
import dagger.Module
import dagger.Provides

/**
 * Created by zhao on 2018-01-16.
 * Copyright (c) 2018 ejia7.com . All rights reserved.
 */
@Module
class ActivityModule(val activity: Activity) {

    @ActivityScope
    @Provides
    fun provideActivity(): Activity {
        return activity
    }
}