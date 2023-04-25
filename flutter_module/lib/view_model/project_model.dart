
import 'package:flutter_module/model/article.dart';
import 'package:flutter_module/model/tree.dart';
import 'package:flutter_module/provider/view_state_list_model.dart';
import 'package:flutter_module/provider/view_state_refresh_list_model.dart';
import 'package:flutter_module/service/wan_android_repository.dart';
import 'package:flutter_module/view_model/favourite_model.dart';


class ProjectCategoryModel extends ViewStateListModel<Tree> {
  @override
  Future<List<Tree>> loadData() async {
    return await WanAndroidRepository.fetchProjectCategories();
  }
}

class ProjectListModel extends ViewStateRefreshListModel<Article> {
  @override
  Future<List<Article>> loadData({int pageNum}) async {
    return await WanAndroidRepository.fetchArticles(pageNum, cid: 294);
  }
  @override
  onCompleted(List data) {
    GlobalFavouriteStateModel.refresh(data);
  }
}
