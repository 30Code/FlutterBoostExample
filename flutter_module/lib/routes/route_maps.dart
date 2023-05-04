import 'package:flutter/material.dart';
import 'package:flutter_boost/flutter_boost.dart';
import 'package:flutter_module/model/tree.dart';
import 'package:flutter_module/pages/home/home_page.dart';
import 'package:flutter_module/pages/home/notice_dialog.dart';
import 'package:flutter_module/pages/message/message_list_page.dart';
import 'package:flutter_module/pages/user/open_account_page.dart';
import 'package:flutter_module/routes/page_num.dart';
import 'package:flutter_module/ui/page/article/article_list_page.dart';
import 'package:flutter_module/ui/page/coin/coin_record_list_page.dart';
import 'package:flutter_module/ui/page/login/login_page.dart';
import 'package:flutter_module/ui/page/login/register_page.dart';
import 'package:flutter_module/ui/page/tab/project_page.dart';

import '../ui/page/tab/structure_page.dart';

/// 路由表
class RouteMap {
  static Map<String, FlutterBoostRouteFactory> routerMap = {
    PageNum.homePage: (settings, uniqueId) {
      return PageRouteBuilder<dynamic>(
          settings: settings, pageBuilder: (_, __, ___) => const HomePage());
    },

    PageNum.coinRecordListPage: (settings, uniqueId) {
      return PageRouteBuilder<dynamic>(
          settings: settings, pageBuilder: (_, __, ___) => CoinRecordListPage());
    },
    PageNum.projectPage: (settings, uniqueId) {
      return PageRouteBuilder<dynamic>(
          settings: settings, pageBuilder: (_, __, ___) => ProjectPage());
    },
    PageNum.structurePage: (settings, uniqueId) {
      return PageRouteBuilder<dynamic>(
          settings: settings, pageBuilder: (_, __, ___) => StructurePage());
    },
    PageNum.loginPage: (settings, uniqueId) {
      return PageRouteBuilder<dynamic>(
          settings: settings, pageBuilder: (_, __, ___) => LoginPage());
    },
    PageNum.registerPage: (settings, uniqueId) {
      return PageRouteBuilder<dynamic>(
          settings: settings, pageBuilder: (_, __, ___) => RegisterPage());
    },
    PageNum.articleCategoryTabPage: (settings, uniqueId) {
      var list = settings.arguments as List;
      Tree tree = list[0] as Tree;
      int index = list[1];
      return PageRouteBuilder<dynamic>(
          settings: settings, pageBuilder: (_, __, ___) => ArticleCategoryTabPage(tree, index));
    },

    PageNum.messageListPage: (settings, uniqueId) {
      return PageRouteBuilder(
        settings: settings,
        pageBuilder: (_, __, ___) => MessageListPage(
          arguments: settings.arguments,
        ),
      );
    },
    PageNum.noticeDialog: (settings, uniqueId) {
      return PageRouteBuilder(
        settings: settings,

        ///透明弹窗页面这个需要是false
        opaque: false,

        ///背景蒙版颜色
        barrierColor: Colors.black54,
        pageBuilder: (_, __, ___) => const NoticeDialog(),
      );
    },
    PageNum.openAccountPage: (settings, uniqueId) {
      return PageRouteBuilder(
        settings: settings,
        pageBuilder: (_, __, ___) => const OpenAccountPage(),
      );
    }
  };
}
