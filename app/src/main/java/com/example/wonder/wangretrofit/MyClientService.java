package com.example.wonder.wangretrofit;



import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Retrofit的接口
 * @HTTP(method = ) http可代表 get  post 只需要指定就可以了
 */

public interface MyClientService {
    //比如：获取商品列表
    @GET("product/getProducts")
    Call<GoodsListBean> getGoodsList(@Query("pscid") int pscid);

}
