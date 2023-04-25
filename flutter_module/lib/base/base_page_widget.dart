import 'package:flutter/material.dart';

/// 页面基础类
abstract class BasePageWidget extends StatefulWidget {

  const BasePageWidget({Key key}) : super(key: key);

  @override
  State<BasePageWidget> createState() => getState();

  State<BasePageWidget> getState();
}

abstract class BasePageState<T extends BasePageWidget> extends State<T> {
  String pageTitle;

  @override
  Widget build(BuildContext context) {
    if (pageTitle == null || pageTitle.isEmpty) {
      return Material(
        child: buildContent(context),
      );
    }
    return Scaffold(
      appBar: AppBar(title: Text(pageTitle)),
      body: buildContent(context),
    );
  }

  Widget buildContent(BuildContext context);
}
