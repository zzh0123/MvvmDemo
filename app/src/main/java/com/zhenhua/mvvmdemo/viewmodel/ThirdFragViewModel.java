package com.zhenhua.mvvmdemo.viewmodel;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.databinding.ObservableInt;

import com.zhenhua.mvvmdemo.MyApplication;
import com.zhenhua.mvvmdemo.model.HttpService;
import com.zhenhua.mvvmdemo.model.User;


import java.util.List;

import rx.android.schedulers.AndroidSchedulers;

import rx.Subscriber;
import rx.Subscription;

/**
 * Author: zzhh
 * Date: 2019/8/26 14:07
 * Description: ${DESCRIPTION}
 */
public class ThirdFragViewModel implements ViewModel{

    private Context context;
    private DataListener dataListener;
    public ObservableInt textViewVisibility;
    public ObservableInt recyclerViewVisibility;

    private Subscription subscription;
    private List<User> userList;

    public ThirdFragViewModel(Context context, DataListener dataListener){
        this.context = context;
        this.dataListener = dataListener;
        textViewVisibility = new ObservableInt(View.GONE);
        this.recyclerViewVisibility = new ObservableInt(View.INVISIBLE);
    }

    public interface DataListener {
        void onUserListChanged(List<User> userList);
    }

    @Override
    public void destroy() {
        if (subscription != null && !subscription.isUnsubscribed()) subscription.unsubscribe();
        subscription = null;
        context = null;
        dataListener = null;
    }

    public void getUserList(){
        textViewVisibility.set(View.VISIBLE);
        if (subscription != null&& !subscription.isUnsubscribed()) subscription.unsubscribe();
        MyApplication application = MyApplication.get(context);
        HttpService httpService = application.getHttpService();
        subscription = httpService.getUserList()
                .observeOn(AndroidSchedulers .mainThread())
                .subscribeOn(application.defaultSubscribeScheduler())
                .subscribe(new Subscriber<List<User>>() {
                    @Override
                    public void onCompleted() {
                        if (dataListener != null) dataListener.onUserListChanged(userList);
                        if (!userList.isEmpty()) {
                            recyclerViewVisibility.set(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onError(Throwable error) {
                        Log.e("--getUserList--", "Error loading repos ", error);
                    }

                    @Override
                    public void onNext(List<User> userList) {
                        Log.i("--getUserList--", "Repos loaded " + userList);
                        ThirdFragViewModel.this.userList = userList;
                    }
                });
    }
}
