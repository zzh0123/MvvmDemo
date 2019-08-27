package com.zhenhua.mvvmdemo.viewmodel;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableInt;

import com.zhenhua.mvvmdemo.model.User;

/**
 * Author: zzhh
 * Date: 2019/8/26 9:59
 * Description: ${DESCRIPTION}
 */
public class ItemUserViewModel extends BaseObservable {
    private User user;
    private Context context;
    TextView textView;
    public ItemUserViewModel(Context context, User user){
        this.context = context;
        this.user = user;
    }

    public ItemUserViewModel(Context context, User user, TextView textView){
        this.context = context;
        this.user = user;
        this.textView = textView;
    }

    public String getUserId(){
        return user.userId;
    }

    public String getUserName(){
        return user.userName;
    }
    public String getPhoneNum(){
        return user.phoneNum;
    }

    public String getSex(){
        return user.sex;
    }

    public void onItemClick(View view){
        if (textView.getVisibility() == View.INVISIBLE ){
            textView.setVisibility(View.VISIBLE);
        } else {
            textView.setVisibility(View.INVISIBLE);
        }
//        (textViewVisibility.get() == View.INVISIBLE ) ? textViewVisibility.set(View.VISIBLE) : textViewVisibility.set(View.INVISIBLE);
//        (textViewVisibility.get() == View.INVISIBLE ) ? true : false;

        Toast.makeText(context, user.userName,Toast.LENGTH_SHORT).show();
    }

    public void setUser(User user) {
        this.user = user;
        notifyChange();
    }

}
