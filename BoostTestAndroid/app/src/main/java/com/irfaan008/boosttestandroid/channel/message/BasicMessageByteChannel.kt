package com.irfaan008.boosttestandroid.channel.message

import com.idlefish.flutterboost.FlutterBoost
import io.flutter.plugin.common.BasicMessageChannel
import io.flutter.plugin.common.BinaryCodec
import java.nio.ByteBuffer

/**
 * @date 2022/8/22
 * @author mxb
 * @desc    字符串消息通道
 * @desired
 */
class BasicMessageByteChannel : IMessageChannel<ByteBuffer> {
    companion object {
        private const val FLUTTER_BASIC_MESSAGE_BYTE = "flutter.channel.basic_message_byte"
    }

    override val channelName = FLUTTER_BASIC_MESSAGE_BYTE

    /**
     * 传递 字节流 类型消息的通道
     */
    private val channel: BasicMessageChannel<ByteBuffer> by lazy {
        BasicMessageChannel(
            FlutterBoost.instance().engine.dartExecutor,
            channelName,
            BinaryCodec.INSTANCE
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
    override fun onMessage(message: ByteBuffer?, reply: BasicMessageChannel.Reply<ByteBuffer>) {

    }

    override fun send(message: ByteBuffer?, callback: BasicMessageChannel.Reply<ByteBuffer>?) {
    }

}