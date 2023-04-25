package com.irfaan008.boosttestandroid.channel

import android.content.ContextWrapper
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Build
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.idlefish.flutterboost.FlutterBoost
import com.irfaan008.boosttestandroid.App
import com.irfaan008.boosttestandroid.LoginActivity
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel

/**
 * @date 2022/8/22
 * @author mxb
 * @desc   方法桥接
 * @desired
 */
object MethodChannelHelper : MethodChannel.MethodCallHandler, NativeInvokeFlutter {
    private const val TAG = "CommonChannelBridge"
    private const val FLUTTER_METHOD_COMMON_METHOD = "flutter.channel.common.method"

    /**
     * 通用方法桥接对象
     */
    private val commonMethodChannel: MethodChannel by lazy {
        MethodChannel(FlutterBoost.instance().engine.dartExecutor, FLUTTER_METHOD_COMMON_METHOD)
    }

    /**
     * 初始化接口回调，flutter 调用 native 的方法的时候，会回调 MethodCallHandler的 onMethodCall方法
     */
    fun initMethodHandler() {
        commonMethodChannel.setMethodCallHandler(this::onMethodCall)
    }

    /**
     * flutter 调用 native 的方法
     * @param call
     * @param result
     */
    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        val methodName = call.method
        val arguments = call.arguments
        Log.i(TAG, "onMethodCall: methodName=$methodName,arguments=$arguments")
        when (methodName) {
            "getBatteryLevel" -> result.success("当前电量：${getBatteryLevel()}")
            "startNativePage" -> startNativePage(call, result)
        }
    }

    /**
     * native 调用 flutter 方法
     * @param methodName    方法名
     * @param arguments     方法参数
     */
    override fun invokeFlutterMethod(methodName: String, arguments: Any?) {
        commonMethodChannel.invokeMethod(methodName, arguments)
    }

    /**
     * 获取电量信息
     * @return
     */
    private fun getBatteryLevel(): Int {
        val context = App.application
        val batteryLevel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val batteryManager =
                context.getSystemService(AppCompatActivity.BATTERY_SERVICE) as BatteryManager
            batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
        } else {
            val intent = ContextWrapper(context).registerReceiver(
                null,
                IntentFilter(Intent.ACTION_BATTERY_CHANGED)
            )
            intent!!.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) * 100 /
                    intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
        }
        return batteryLevel
    }

    /**
     * 启动原生页面
     * @param call
     * @param result
     */
    private fun startNativePage(call: MethodCall, result: MethodChannel.Result) {
        val route = (call.arguments as? Map<*, *>)?.get("route")
        FlutterBoost.instance().currentActivity()?.let {
            when (route) {
                "login" -> it.startActivity(Intent(it, LoginActivity::class.java))
            }
        }
    }
}