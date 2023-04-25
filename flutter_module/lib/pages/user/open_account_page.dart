import 'package:flutter/material.dart';
import 'package:flutter_boost/flutter_boost.dart';
import 'package:flutter_module/base/base_page_widget.dart';

import '../../routes/page_num.dart';

/// 消息列表页
class OpenAccountPage extends BasePageWidget {
  final arguments;

  const OpenAccountPage({Key key, this.arguments}) : super(key: key);

  @override
  State<BasePageWidget> getState() =>
      _OpenAccountPagePage(arguments: arguments);
}

class _OpenAccountPagePage extends BasePageState {
  final arguments;

  _OpenAccountPagePage({this.arguments});

  @override
  String pageTitle = "登录页面";

  @override
  Widget buildContent(BuildContext context) {
    return Center(
      child: Column(
        children: [
          const SizedBox(height: 30),
          const Text(
            "登录失败，返回第一页",
            style: TextStyle(fontSize: 20),
          ),
          const SizedBox(height: 30),
          ElevatedButton(
            onPressed: () {
              BoostNavigator.instance.popUntil(route: PageNum.homePage);
            },
            child: const Text("返回Flutter首页"),
          ),
        ],
      ),
    );
  }
}
