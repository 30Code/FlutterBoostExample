import 'package:flutter/services.dart';

class MethodChannelHelper {
  static const String flutterMethodChannel = "flutter.channel.common.method";

  final MethodChannel _channel = const MethodChannel(flutterMethodChannel);

  MethodChannelHelper._internal();

  static final MethodChannelHelper _instance =
      MethodChannelHelper._internal();

  factory MethodChannelHelper() => _instance;

  /// 获取手机电量
  Future<String> getBatteryLevel() async {
    String battery = await _channel.invokeMethod("getBatteryLevel");
    return battery;
  }

  void startNativePage(String route, Map<String, dynamic> params) {
    params ??= {};
    params['route'] = route;
    _channel.invokeMethod("startNativePage", params);
  }

  // 原生调用Flutter
  void initChannel() {
    // nativeCallFlutter 注册渠道
    _channel.setMethodCallHandler((call) async {
      await handleNativeMethodCall(call);
    });
  }

  /// 处理原生方法调用
  Future<void> handleNativeMethodCall(MethodCall call) async {
    switch(call.method) {
      case "methodName": //方法名
        final arguments = call.arguments; //参数
        break;
    }
  }
}
