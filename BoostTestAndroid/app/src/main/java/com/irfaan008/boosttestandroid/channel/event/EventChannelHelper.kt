package com.irfaan008.boosttestandroid.channel.event

import android.util.Log
import com.idlefish.flutterboost.FlutterBoost
import io.flutter.plugin.common.EventChannel

/**
 * @date 2022/8/22
 * @author mxb
 * @desc 事件通道，继续广播，通常用于native 端 向 flutter端 上报事件，如：手机电量变化，网络连接变化，陀螺仪，传感器等;
 * @desired
 */
object EventChannelHelper {
    private const val TAG = "EventChannelHelper"

    private const val FLUTTER_EVENT_CHANNEL = "flutter.event.channel"

    private val channel: EventChannel by lazy {
        EventChannel(FlutterBoost.instance().engine.dartExecutor, FLUTTER_EVENT_CHANNEL)
    }

    var eventSink: EventChannel.EventSink? = null

    /**
     * EventChannel 初始化
     */
    fun initEventChannel() {
        channel.setStreamHandler(object : EventChannel.StreamHandler {
            override fun onListen(arguments: Any?, events: EventChannel.EventSink?) {
                Log.i(TAG, "EventChannel 建立链接")
                Log.i(TAG, "onListen: arguments=$arguments")
                eventSink = events
            }

            override fun onCancel(arguments: Any?) {
                Log.i(TAG, "onCancel: native 取消EventChannel")
                eventSink = null
            }
        })
    }

}