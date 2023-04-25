import 'package:flutter/material.dart';
import 'package:flutter_module/base/base_page_widget.dart';

/// 消息列表页
class MessageListPage extends BasePageWidget {
  final arguments;

  const MessageListPage({Key key, this.arguments}) : super(key: key);

  @override
  State<BasePageWidget> getState() => _MessageListPage(arguments: arguments);
}

class _MessageListPage extends BasePageState {
  final arguments;

  _MessageListPage({this.arguments});

  @override
  String pageTitle = "标题";

  @override
  void initState() {
    super.initState();
    setState(() {
      pageTitle = arguments["name"];
    });
  }

  @override
  Widget buildContent(BuildContext context) {
    return const Center(
      child: Text("消息列表页面"),
    );
  }
}
