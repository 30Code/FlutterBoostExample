import 'package:flutter/services.dart';

class BasicMessageChannelHelper {
  static const _CHANNEL_STRING = "flutter.channel.basic_message_string";

  final BasicMessageChannel channel =
      const BasicMessageChannel(_CHANNEL_STRING, StringCodec());

  BasicMessageChannelHelper._internal();

  static final BasicMessageChannelHelper _instance =
      BasicMessageChannelHelper._internal();

  factory BasicMessageChannelHelper.instance() => _instance;
}
