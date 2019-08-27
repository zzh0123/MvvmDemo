package com.zhenhua.mvvmdemo;

import android.app.Application;
import android.content.Context;

import com.zhenhua.mvvmdemo.model.HttpService;

import rx.Scheduler;
import rx.schedulers.Schedulers;

public class MyApplication extends Application {

    private HttpService httpService;
    private Scheduler defaultSubscribeScheduler;

    public static MyApplication get(Context context) {
        return (MyApplication) context.getApplicationContext();
    }

    public HttpService getHttpService() {
        if (httpService == null) {
            httpService = HttpService.Factory.create();
        }
        return httpService;
    }

    //For setting mocks during testing
    public void setHttpService(HttpService httpService) {
        this.httpService = httpService;
    }

    public Scheduler defaultSubscribeScheduler() {
        if (defaultSubscribeScheduler == null) {
            defaultSubscribeScheduler = Schedulers.io();
        }
        return defaultSubscribeScheduler;
    }

    //User to change scheduler from tests
    public void setDefaultSubscribeScheduler(Scheduler scheduler) {
        this.defaultSubscribeScheduler = scheduler;
    }
}
