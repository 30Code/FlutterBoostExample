import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_boost/flutter_boost.dart';
import 'package:flutter_module/config/provider_manager.dart';
import 'package:flutter_module/config/storage_manager.dart';
import 'package:flutter_module/custom_flutter_binding.dart';
import 'package:flutter_module/routes/route_maps.dart';
import 'package:flutter_module/view_model/locale_model.dart';
import 'package:flutter_module/view_model/theme_model.dart';
import 'package:oktoast/oktoast.dart';
import 'package:provider/provider.dart';
import 'package:pull_to_refresh/pull_to_refresh.dart';

import 'channel/method_channel.dart';

void main() async {
  Provider.debugCheckInvalidValueType = null;
  
  CustomFlutterBinding();
  MethodChannelHelper().initChannel();
  await StorageManager.init();
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({Key key}) : super(key: key);

  @override
  State<StatefulWidget> createState() {
    return _MyAppState();
  }
}

class _MyAppState extends State<MyApp> {
  Route<dynamic> routeFactory(RouteSettings settings, String uniqueId) {
    FlutterBoostRouteFactory func = RouteMap.routerMap[settings.name];
    if (func == null) {
      return null;
    }
    return func(settings, uniqueId);
  }

  Widget appBuilder(Widget widget) {
    return OKToast(
        child: MultiProvider(
            providers: providers,
            child: Consumer2<ThemeModel, LocaleModel>(
                builder: (context, themeModel, localeModel, child) {
                  return RefreshConfiguration(
                    hideFooterWhenNotFull: true, //列表数据不满一页,不触发加载更多
                    child: MaterialApp(
                      // theme: themeModel.themeData(),
                      // darkTheme: themeModel.themeData(platformDarkMode: true),
                      // locale: localeModel.locale,
                      // localizationsDelegates: const [
                      //   S.delegate,
                      //   RefreshLocalizations.delegate, //下拉刷新
                      //   GlobalCupertinoLocalizations.delegate,
                      //   GlobalMaterialLocalizations.delegate,
                      //   GlobalWidgetsLocalizations.delegate
                      // ],
                      // supportedLocales: S.delegate.supportedLocales,
                      // onGenerateRoute: MyRouter.generateRoute,
                      // initialRoute: RouteName.splash,

                      home: widget,
                      debugShowCheckedModeBanner: true,

                      ///必须加上builder参数，否则showDialog等会出问题
                      builder: (_, __) {
                        return widget;
                      },
                    ),
                  );
                })));
    // return MaterialApp(
    //   home: widget,
    //   debugShowCheckedModeBanner: true,
    //
    //   ///必须加上builder参数，否则showDialog等会出问题
    //   builder: (_, __) {
    //     return widget;
    //   },
    // );
  }

  @override
  Widget build(BuildContext context) {
    return FlutterBoostApp(
      routeFactory,
      appBuilder: appBuilder,
    );
  }
}
