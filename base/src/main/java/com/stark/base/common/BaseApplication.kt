package com.stark.base.common

import android.app.Application
import android.content.Context
import com.stark.base.injection.component.AppComponent
import com.stark.base.injection.component.DaggerAppComponent
import com.stark.base.injection.module.AppModule

/**
 * Created by zhao on 2018-01-16.
 * Copyright (c) 2018 ejia7.com . All rights reserved.
 */
class BaseApplication : Application() {
    lateinit var appComponent: AppComponent

    companion object {

        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()

        Thread.setDefaultUncaughtExceptionHandler(object : Thread.UncaughtExceptionHandler {
            override fun uncaughtException(t: Thread?, e: Throwable?) {
                e?.printStackTrace()
            }
        })
    }


}