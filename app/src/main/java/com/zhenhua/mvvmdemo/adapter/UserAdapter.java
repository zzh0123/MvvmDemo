package com.zhenhua.mvvmdemo.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.zhenhua.mvvmdemo.R;
import com.zhenhua.mvvmdemo.databinding.ItemUserBinding;
import com.zhenhua.mvvmdemo.model.User;
import com.zhenhua.mvvmdemo.viewmodel.ItemUserViewModel;

import java.util.Collections;
import java.util.List;

/**
 * Author: zzhh
 * Date: 2019/8/26 11:17
 * Description: ${DESCRIPTION}
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {


    private List<User> userList;

    public UserAdapter() {
        this.userList = Collections.emptyList();
    }
    static TextView view;
    public UserAdapter(TextView view) {
        this.userList = Collections.emptyList();
        this.view = view;
    }
    public UserAdapter(List<User> userList) {
        this.userList = userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemUserBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_user,
                parent,
                false);
        return new UserViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.bindUser(userList.get(position));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        final ItemUserBinding binding;

        public UserViewHolder(ItemUserBinding binding) {
            super(binding.cardView);
            this.binding = binding;
        }

        void bindUser(User user) {
            if (binding.getItemUserViewModel() == null) {
                binding.setItemUserViewModel(new ItemUserViewModel(itemView.getContext(), user, view));
            } else {
                binding.getItemUserViewModel().setUser(user);
            }
        }
    }
}
