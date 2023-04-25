import 'package:flutter/material.dart';
import 'package:flutter_boost/flutter_boost.dart';
import 'package:flutter_module/base/base_page_widget.dart';
import 'package:flutter_module/channel/basic_message_channel.dart';
import 'package:flutter_module/channel/event_channel.dart';
import 'package:flutter_module/channel/method_channel.dart';
import 'package:flutter_module/routes/page_num.dart';

/// 首页
class HomePage extends BasePageWidget {
  const HomePage({Key key}) : super(key: key);

  @override
  State<BasePageWidget> getState() => HomePageState();
}

class HomePageState extends BasePageState {
  @override
  String pageTitle = "首页";

  // 显示结果
  String result = "结果";

  @override
  Widget buildContent(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.only(left: 10, right: 10),
      child: ListView(
        children: [
          Padding(
            padding: const EdgeInsets.all(10),
            child: Text(
              result,
              style: const TextStyle(
                fontSize: 20,
                color: Colors.red,
              ),
            ),
          ),
          ElevatedButton(
            onPressed: () async {
              String battery = await MethodChannelHelper().getBatteryLevel();
              print('battery=$battery');
              setState(() {
                result = battery;
              });
            },
            child: const Text("MethodChannel，调用native方法，获取电量"),
          ),
          ElevatedButton(
            onPressed: () async {
              // MethodChannelHelper().startNativePage('login', null);
              EventChannelHelper.instance().listenNetwork();
            },
            child: const Text("EventChannel，监听网络变化"),
          ),
          ElevatedButton(
            onPressed: () async {
              String reply = await BasicMessageChannelHelper.instance()
                  .channel
                  .send("Hello,I am Flutter");
              setState(() {
                result = "$reply";
              });
            },
            child: const Text("BasicMessageChannel，发送消息"),
          ),
          ElevatedButton(
            onPressed: () {
              // name	          页面在路由表中的名字
              // withContainer	是否需伴随原生容器弹出
              // arguments	    携带到下一页面的参数
              // opaque	        页面是否透明(下面会再次提到)
              BoostNavigator.instance.push(
                PageNum.messageListPage,
                withContainer: false,
                arguments: {"name": "消息列表"},
                opaque: false,
              );

              /// 或者
              /// Navigator.of(context).pushNamed('simplePage', arguments: {'data': _controller.text});
            },
            child: const Text("不开启新容器，跳转页面"),
          ),
          ElevatedButton(
            onPressed: () {
              BoostNavigator.instance.push(
                PageNum.messageListPage,
                withContainer: true,
                arguments: {"name": "消息列表"},
                opaque: false,
              );
            },
            child: const Text("开启新容器的方式进行页面跳转"),
          ),
          ElevatedButton(
            onPressed: () async {
              var res =
                  await BoostNavigator.instance.push(PageNum.noticeDialog);
              setState(() {
                result = "弹框点击按钮：$res";
              });
            },
            child: const Text("不开启新容器的flutter内部弹窗(推荐)"),
          ),
          ElevatedButton(
            onPressed: () async {
              var res = await BoostNavigator.instance.push(
                PageNum.noticeDialog,

                /// 开启新容器的方法，打开弹框
                withContainer: true,

                /// 透明
                opaque: false,
              );
              setState(() {
                result = "弹框点击按钮：$res";
              });
            },
            child: const Text("开启新容器的flutter内部弹窗"),
          ),
          ElevatedButton(
            onPressed: () async {
              // MethodChannelHelper().startNativePage('login', null);
              BoostNavigator.instance.push("login");
            },
            child: const Text("启动原生登录页面"),
          ),
          ElevatedButton(
            onPressed: ()  {
              BoostNavigator.instance.push(
                PageNum.coinRecordListPage,
                withContainer: false,
                arguments: {"name": "积分页面"},
                opaque: false,
              );
            },
            child: const Text("跳转积分页面"),
          ),
          ElevatedButton(
            onPressed: ()  {
              BoostNavigator.instance.push(
                PageNum.structurePage,
                withContainer: false,
                arguments: {"name": "体系页面"},
                opaque: false,
              );
            },
            child: const Text("跳转体系页面"),
          ),
          ElevatedButton(
            onPressed: ()  {
              BoostNavigator.instance.push(
                PageNum.projectPage,
                withContainer: false,
                arguments: {"name": "公众号页面"},
                opaque: false,
              );
            },
            child: const Text("跳转公众号页面"),
          ),
        ],
      ),
    );
  }
}
