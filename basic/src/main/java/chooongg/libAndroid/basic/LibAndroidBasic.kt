package chooongg.libAndroid.basic

import android.app.Activity
import chooongg.libAndroid.basic.manager.ActivityLifecycleManager
import chooongg.libAndroid.basic.manager.ApplicationManager

val APPLICATION get() = ApplicationManager.application!!

/**
 * 获取Activity栈
 */
val ACTIVITY_STACK: List<Activity> get() = ActivityLifecycleManager.activityTask

/**
 * 获取Activity栈中的栈顶
 */
val ACTIVITY_STACK_TOP
    get() = if (ActivityLifecycleManager.activityTask.isEmpty()) null
    else ActivityLifecycleManager.activityTask.last

