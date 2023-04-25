import 'package:flutter/material.dart';
import 'package:flutter_boost/flutter_boost.dart';

/// 弹框页面
class NoticeDialog extends StatelessWidget {
  const NoticeDialog({Key key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Align(
      alignment: Alignment.bottomCenter,
      child: Container(
        width: double.infinity,
        height: 250,
        alignment: Alignment.center,
        decoration: const BoxDecoration(
          /// 内容区，背景色
          color: Colors.white,
          borderRadius: BorderRadius.only(
            topLeft: Radius.circular(30),
            topRight: Radius.circular(30),
          ),
        ),
        child: Material(
          color: Colors.transparent,
          child: Column(
            children: [
              const Text(
                "标题",
                style: TextStyle(fontSize: 20, color: Colors.black),
              ),
              const Text("弹窗内容"),
              const Expanded(
                child: Text(""),
                flex: 1,
              ),
              Row(
                children: [
                  Expanded(
                    flex: 1,
                    child: TextButton(
                      onPressed: () {
                        BoostNavigator.instance.pop("Cancel");
                      },
                      /// 弹框取消
                      child: const Text("Cancel"),
                    ),
                  ),
                  const SizedBox(width: 10),
                  Expanded(
                    flex: 1,
                    child: ElevatedButton(
                      onPressed: () {
                        /// 弹框取消
                        BoostNavigator.instance.pop("Ok");
                      },
                      child: const Text("Ok"),
                    ),
                  ),
                ],
              )
            ],
          ),
        ),
      ),
    );
  }
}
