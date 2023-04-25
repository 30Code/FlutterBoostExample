package com.irfaan008.boosttestandroid.channel.message

import com.idlefish.flutterboost.FlutterBoost
import io.flutter.plugin.common.BasicMessageChannel
import io.flutter.plugin.common.JSONMessageCodec

/**
 * @date 2022/8/22
 * @author mxb
 * @desc    字符串消息通道
 * @desired
 */
class BasicMessageJsonChannel : IMessageChannel<Any> {
    companion object {
        private const val FLUTTER_BASIC_MESSAGE_JSON = "flutter.channel.basic_message_json"
    }

    override val channelName = FLUTTER_BASIC_MESSAGE_JSON

    /**
     * 传递 Json 类型消息的通道
     */
    private val channel: BasicMessageChannel<Any> by lazy {
        BasicMessageChannel(
            FlutterBoost.instance().engine.dartExecutor,
            channelName,
            JSONMessageCodec.INSTANCE
        )
    }

    /**
     * 初始化消息处理器
     */
    override fun initMessageHandler() {
        channel.setMessageHandler(this)
    }

    /**
     * native 接收到 flutter 消息
     * @param message
     * @param reply
     */
    override fun onMessage(message: Any?, reply: BasicMessageChannel.Reply<Any>) {
    }

    override fun send(message: Any?, callback: BasicMessageChannel.Reply<Any>?) {
    }

}