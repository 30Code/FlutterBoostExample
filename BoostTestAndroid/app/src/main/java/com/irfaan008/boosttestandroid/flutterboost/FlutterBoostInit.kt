package com.irfaan008.boosttestandroid.flutterboost

import android.app.Application
import android.content.Intent
import android.util.Log
import com.idlefish.flutterboost.FlutterBoost
import com.idlefish.flutterboost.FlutterBoostDelegate
import com.idlefish.flutterboost.FlutterBoostRouteOptions
import com.idlefish.flutterboost.containers.FlutterBoostActivity
import com.irfaan008.boosttestandroid.LoginActivity
import com.irfaan008.boosttestandroid.channel.BasicChannelHelper
import com.irfaan008.boosttestandroid.channel.MethodChannelHelper
import com.irfaan008.boosttestandroid.channel.event.EventChannelHelper
import io.flutter.embedding.android.FlutterActivityLaunchConfigs
import io.flutter.embedding.engine.FlutterEngine


/**
 * @date 2022/8/22
 * @author mxb
 * @desc Flutter Boost 初始化
 * @desired
 */
class FlutterBoostInit {
    companion object {
        private const val TAG = "FlutterBoostInit"
        fun init(application: Application) {
            FlutterBoost.instance().setup(application, object : FlutterBoostDelegate {
                override fun pushNativeRoute(options: FlutterBoostRouteOptions) {
                    //这里根据options.pageName来判断你想跳转哪个页面，这里简单给一个
                    Log.i(TAG, "pushNativeRoute: options=${options.pageName()}")
                    // val intent = Intent(FlutterBoost.instance().currentActivity(),
                    //     YourTargetAcitvity::class.java
                    // )
                    // FlutterBoost.instance().currentActivity()
                    //     .startActivityForResult(intent, options.requestCode())
                    val route = options.pageName()
                    FlutterBoost.instance().currentActivity()?.let {
                        when (route) {
                            "login" -> it.startActivity(Intent(it, LoginActivity::class.java))
                        }
                    }
                }

                override fun pushFlutterRoute(options: FlutterBoostRouteOptions) {
                    Log.i(TAG, "pushFlutterRoute: options=$options")
                    val intent: Intent =
                        FlutterBoostActivity.CachedEngineIntentBuilder(FlutterBoostActivity::class.java)
                            .backgroundMode(FlutterActivityLaunchConfigs.BackgroundMode.transparent)
                            .destroyEngineWithActivity(false)
                            .uniqueId(options.uniqueId())
                            .url(options.pageName())
                            .urlParams(options.arguments())
                            .build(FlutterBoost.instance().currentActivity())
                    FlutterBoost.instance().currentActivity().startActivity(intent)
                }
            }) { engine: FlutterEngine? ->
                // 在这里可以初始化 Channel，因为这个时候 FlutterEngine 对象才创建完成
                MethodChannelHelper.initMethodHandler()
                BasicChannelHelper.initMethodHandler()
                EventChannelHelper.initEventChannel()
            }
        }
    }
}