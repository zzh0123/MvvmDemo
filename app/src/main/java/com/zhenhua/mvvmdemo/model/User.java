package com.zhenhua.mvvmdemo.model;


import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class User implements Parcelable {

    public String userId;
    public String userName;
    public String phoneNum;
    public String sex;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userId);
        dest.writeString(this.userName);
        dest.writeString(this.phoneNum);
        dest.writeString(this.sex);
    }

    protected User(Parcel in) {
        this.userId = in.readString();
        this.userName = in.readString();
        this.phoneNum = in.readString();
        this.sex = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
