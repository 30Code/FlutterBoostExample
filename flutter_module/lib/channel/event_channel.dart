import 'package:flutter/services.dart';

class EventChannelHelper {
  static const _FLUTTER_EVENT_CHANNEL = "flutter.event.channel";

  final EventChannel _channel = const EventChannel(_FLUTTER_EVENT_CHANNEL);

  // 定义一个私有构造函数
  EventChannelHelper._internal();

  static final EventChannelHelper _instance = EventChannelHelper._internal();

  factory EventChannelHelper.instance() => _instance;

  /// 监听网络变化
  String listenNetwork() {
    _channel.receiveBroadcastStream().listen(
      (event) {
        print('event=$event');
      },
      onError: (error) {
        print('监听网络状态，发生异常');
      },
      onDone: () {
        print('监听网络状态，完成');
      },
      cancelOnError: true,
    );
    return null;
  }
}
