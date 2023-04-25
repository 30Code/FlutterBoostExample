package com.mechanist.boosttestandroid

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.mechanist.boosttestandroid.flutterboost.FlutterBoostInit
import com.mechanist.boosttestandroid.utils.FContext
import com.mei.myhost.utils.ActivityUtils


class App: Application() {

    companion object {
        lateinit var application: Application
    }

    override fun onCreate() {
        super.onCreate()
        FlutterBoostInit.init(this)
        FContext.set(this)
        application = this
        registerActivityLifecycleCallbacks(object :ActivityLifecycleCallbacks{
            override fun onActivityCreated(p0: Activity, p1: Bundle?) {
                ActivityUtils.push(p0)
            }

            override fun onActivityStarted(p0: Activity) {
            }

            override fun onActivityResumed(p0: Activity) {
            }

            override fun onActivityPaused(p0: Activity) {
            }

            override fun onActivityStopped(p0: Activity) {
            }

            override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
            }

            override fun onActivityDestroyed(p0: Activity) {
                ActivityUtils.pop()
            }
        })
    }
}