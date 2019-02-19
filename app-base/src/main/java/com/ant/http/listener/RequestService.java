package com.ant.http.listener;//package com.ant.http.listener;
//
//import com.ant.base.http.HttpConfig;
//import com.ant.http.Bean.HttpResult;
//import com.tuzhumao.hjzx.home.bean.RecommentBean;
//
//import io.reactivex.Observable;
//import retrofit2.http.GET;
//import retrofit2.http.Query;
//
//
//public interface RequestService {
//    String BASE_URL = HttpConfig.BASE_URL;
//
//    //获取数据
//    @GET(HttpConfig.NEW_VIRSON)
//    Observable<String> getNewVison();
//
//
//    //根据经纬度 获取marker
//    @GET(HttpConfig.ADDRESS_INIT)
//    Observable<String> getAddressList(@Query("user_id") String user_id, @Query("state") String state);
//
//    //推荐
//    @GET(HttpConfig.RECHOMMENT_INIT)
//    Observable<HttpResult<RecommentBean>> getRecomment(@Query("pageIndex") int pageIndex, @Query("pageSize") int pageSize, @Query("user_id") String user_id);
//
//    //发现
//    @GET(HttpConfig.FIND_INIT)
//    Observable<String> getFindData();
//
//    //发现详情
//    @GET(HttpConfig.FIND_DETAIL_INIT)
//    Observable<String> getFindDetailData(@Query("article_id") String article_id);
//
//    @GET(HttpConfig.GOODS_INIT)
//    Observable<String> getGoodsId(@Query("goods_id") String goodsid);
//
//    @GET(HttpConfig.GOODS_LIST_INIT)
//    Observable<String> getGoosListData(@Query("class_id") String class_id);
//
//    //购物车初始化
//    @GET(HttpConfig.CAR_INIT)
//    Observable<String> getCarData(@Query("user_id") String user_id);
//
//    //购物车初始化
//    @GET(HttpConfig.SEARCH_INIT)
//    Observable<String> getSearChData(@Query("user_id") String user_id);
//
//    //购物车初始化
//    @GET(HttpConfig.GOODS_SEARCH)
//    Observable<String> goodsSearch(@Query("searchStr") String searchStr, @Query("user_id") String user_id);
//
//    //购物车初始化
//    @GET(HttpConfig.TYPE_DATA_INIT)
//    Observable<String> typeDataInit();
//
//
//}
