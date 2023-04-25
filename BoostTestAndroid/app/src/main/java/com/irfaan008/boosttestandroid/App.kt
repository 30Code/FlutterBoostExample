package com.irfaan008.boosttestandroid

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Bundle
import com.idlefish.flutterboost.FlutterBoost
import com.idlefish.flutterboost.FlutterBoostDelegate
import com.idlefish.flutterboost.FlutterBoostRouteOptions
import com.idlefish.flutterboost.containers.FlutterBoostActivity
import com.irfaan008.boosttestandroid.flutterboost.FlutterBoostInit
import com.irfaan008.boosttestandroid.utils.FContext
import com.mei.myhost.utils.ActivityUtils
import io.flutter.embedding.android.FlutterActivityLaunchConfigs


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