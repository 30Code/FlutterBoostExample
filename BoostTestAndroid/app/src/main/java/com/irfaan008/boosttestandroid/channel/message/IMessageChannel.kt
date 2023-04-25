package com.irfaan008.boosttestandroid.channel.message

import io.flutter.plugin.common.BasicMessageChannel

/**
 * @date 2022/8/22
 * @author mxb
 * @desc   消息通道
 * @desired
 */
interface IMessageChannel<T> : BasicMessageChannel.MessageHandler<T> {
    /**
     * 获取消息通道名称
     */
    val channelName: String

    /**
     * 初始化消息处理器
     */
    fun initMessageHandler()

    /**
     * native 向 Flutter 发送消息
     * @param message
     * @param callback
     */
    fun send(message: T?, callback: BasicMessageChannel.Reply<T>?)
}