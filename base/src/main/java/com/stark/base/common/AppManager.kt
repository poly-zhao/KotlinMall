package com.stark.base.common

import android.app.Activity
import java.util.*

/**
 * Created by zhao on 2018-01-16.
 * Copyright (c) 2018 ejia7.com . All rights reserved.
 */
class AppManager private constructor(){

    private val activityStack:Stack<Activity> = Stack()

    companion object {
        val  instance by lazy { AppManager() }
    }


    fun addActivity(activity: Activity){
        activityStack.add(activity)
    }

    fun finishActivity(activity: Activity){
        activity.finish()
        activityStack.remove(activity)
    }

    /**
     * 获取栈顶
     */
    fun currentActivity():Activity{
        return activityStack.lastElement()
    }

    /**
     * 清空任务栈
     */
    fun finishAllActivity(){
        for(activity in activityStack){
            activity.finish()
        }
        activityStack.clear()
    }

    fun finishApp(){
        finishAllActivity()
        System.exit(0)
    }

}