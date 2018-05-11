package com.example.wonder.wangretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       list= findViewById(R.id.list);
        //使用Retrofit去请求数据
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HttpConfig.base_url)//地址
                .addConverterFactory(GsonConverterFactory.create(new Gson()))//提那家转换工厂
                .build();
        //指定服务的接口
        MyClientService service = retrofit.create(MyClientService.class);//指定服务,通过反射创建子类
        //调用服务里面的方法
        Call<GoodsListBean> call = service.getGoodsList(1);
        //执行
        call.enqueue(new Callback<GoodsListBean>() {
            @Override
            public void onResponse(Call<GoodsListBean> date, Response<GoodsListBean> response) {
                GoodsListBean body = response.body();
                List<GoodsListBean.DataBean> data = body.getData();
               // Log.d(TAG, "失败----");
                MyAdapter myAdapter = new MyAdapter(MainActivity.this,data );
                list.setAdapter(myAdapter);
            }

            @Override
            public void onFailure(Call<GoodsListBean> call, Throwable t) {
                Log.d(TAG, "失败----");
            }
        });
    }

}
