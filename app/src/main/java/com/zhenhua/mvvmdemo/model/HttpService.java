package com.zhenhua.mvvmdemo.model;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;
/**
 * Author: zzhh
 * Date: 2019/8/26 14:23
 * Description: ${DESCRIPTION}
 */
public interface HttpService {

    @GET("getUsers")
    Observable<List<User>> getUserList();
    class Factory {
        public static HttpService create() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.3.75:8080")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(HttpService.class);
        }
    }

}
