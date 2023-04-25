package com.irfaan008.boosttestandroid.channel

import com.irfaan008.boosttestandroid.channel.message.BasicMessageByteChannel
import com.irfaan008.boosttestandroid.channel.message.BasicMessageJsonChannel
import com.irfaan008.boosttestandroid.channel.message.BasicMessageStandChannel
import com.irfaan008.boosttestandroid.channel.message.BasicMessageStringChannel
import io.flutter.plugin.common.BasicMessageChannel
import org.json.JSONObject
import java.nio.ByteBuffer

/**
 * @date 2022/8/22
 * @author mxb
 * @desc   消息通道：持续通信，收到消息后可以回复此次消息，如: Native将遍历到的文件信息陆续传递到Dart,
 *         在比如: Flutter将从服务端陆陆续获取到信息交给Native加工，Native处理完返回等;
 * @desired
 */
object BasicChannelHelper {
    private const val TAG = "CommonChannelBridge"

    /**
     * 传递 String 类型消息的通道
     */
    private val stringChannel: BasicMessageStringChannel by lazy {
        BasicMessageStringChannel()
    }

    /**
     * 传递 Json 类型消息的通道
     */
    private val jsonChannel: BasicMessageJsonChannel by lazy {
        BasicMessageJsonChannel()
    }

    /**
     * 传递 任意对象 类型消息的通道
     */
    private val standChannel: BasicMessageStandChannel by lazy {
        BasicMessageStandChannel()
    }

    /**
     * 传递 字节流 类型消息的通道
     */
    private val byteChannel: BasicMessageByteChannel by lazy {
        BasicMessageByteChannel()
    }

    /**
     * 初始化接口回调，flutter 调用 native 的方法的时候，会回调 MethodCallHandler的 onMethodCall方法
     */
    fun initMethodHandler() {
        stringChannel.initMessageHandler()
        jsonChannel.initMessageHandler()
        standChannel.initMessageHandler()
        byteChannel.initMessageHandler()
    }

    fun sendString(message: String?, callback: BasicMessageChannel.Reply<String>?) {
        stringChannel.send(message, callback)
    }

    fun send(message: Any?, callback: BasicMessageChannel.Reply<Any>?) {
        if (message is JSONObject) {
            jsonChannel.send(message, callback)
            return
        }
        standChannel.send(message, callback)
    }

    fun sendByte(message: ByteBuffer?, callback: BasicMessageChannel.Reply<ByteBuffer>?) {
        byteChannel.send(message, callback)
    }
}