import 'package:flutter/material.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter_module/model/article.dart';
import 'package:flutter_module/model/tree.dart';
import 'package:flutter_module/provider/provider_widget.dart';
import 'package:flutter_module/provider/view_state_widget.dart';
import 'package:flutter_module/ui/helper/refresh_helper.dart';
import 'package:flutter_module/ui/widget/article_skeleton.dart';
import 'package:flutter_module/ui/widget/skeleton.dart';
import 'package:flutter_module/view_model/structure_model.dart';
import 'package:pull_to_refresh/pull_to_refresh.dart';

/// 文章列表页面
class ArticleListPage extends StatefulWidget {
  /// 目录id
  final int cid;

  ArticleListPage(this.cid);

  @override
  _ArticleListPageState createState() => _ArticleListPageState();
}

class _ArticleListPageState extends State<ArticleListPage>
    with AutomaticKeepAliveClientMixin {
  @override
  bool get wantKeepAlive => true;

  @override
  Widget build(BuildContext context) {
    super.build(context);
    return ProviderWidget<StructureListModel>(
      model: StructureListModel(widget.cid),
      onModelReady: (model) => model.initData(),
      builder: (context, model, child) {
        if (model.isBusy) {
          return SkeletonList(
            builder: (context, index) => ArticleSkeletonItem(),
          );
        } else if (model.isError && model.list.isEmpty) {
          return ViewStateErrorWidget(
              error: model.viewStateError, onPressed: model.initData);
        } else if (model.isEmpty) {
          return ViewStateEmptyWidget(onPressed: model.initData);
        }
        return SmartRefresher(
            controller: model.refreshController,
            header: WaterDropHeader(),
            footer: RefresherFooter(),
            onRefresh: model.refresh,
            onLoading: model.loadMore,
            enablePullUp: true,
            child: ListView.builder(
                itemCount: model.list.length,
                itemBuilder: (context, index) {
                  Article item = model.list[index];
                  return Container();
                }));
      },
    );
  }
}

/// 体系--> 选择相关知识点的详情页
class ArticleCategoryTabPage extends StatelessWidget {
  final Tree tree;
  final int index;

  ArticleCategoryTabPage(this.tree, this.index);

  @override
  Widget build(BuildContext context) {
    return DefaultTabController(
      length: tree.children.length,
      initialIndex: index,
      child: Scaffold(
          appBar: AppBar(
            centerTitle: true,
            title: Text(tree.name),
            bottom: TabBar(
                isScrollable: true,
                tabs: List.generate(
                    tree.children.length,
                    (index) => Tab(
                          text: tree.children[index].name,
                        ))),
          ),
          body: TabBarView(
            children: List.generate(tree.children.length,
                (index) => ArticleListPage(tree.children[index].id)),
          )),
    );
  }
}
