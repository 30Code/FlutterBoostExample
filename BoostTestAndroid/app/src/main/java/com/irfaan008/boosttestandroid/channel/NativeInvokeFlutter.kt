package com.irfaan008.boosttestandroid.channel

/**
 * @date 2022/8/22
 * @author mxb
 * @desc   native 调用 flutter 的方法接口
 * @desired
 */
interface NativeInvokeFlutter {
    /**
     * native 调用 flutter 方法
     * @param methodName    flutter 的方法名
     * @param arguments     传递的参数
     */
    fun invokeFlutterMethod(methodName: String, arguments: Any?)
}