package com.zhenhua.mvvmdemo.view;


import android.database.DatabaseUtils;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhenhua.mvvmdemo.R;
import com.zhenhua.mvvmdemo.adapter.UserAdapter;
import com.zhenhua.mvvmdemo.databinding.FragmentThirdBinding;
import com.zhenhua.mvvmdemo.model.User;
import com.zhenhua.mvvmdemo.viewmodel.ItemUserViewModel;
import com.zhenhua.mvvmdemo.viewmodel.ThirdFragViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThirdFragment extends Fragment implements ThirdFragViewModel.DataListener {


    private FragmentThirdBinding binding;
    private ThirdFragViewModel thirdFragViewModel;
    public ThirdFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_third, container, false);
        thirdFragViewModel = new ThirdFragViewModel(getActivity(), this);
        binding.setThirdFragViewModel(thirdFragViewModel);
        setupRecyclerView(binding.xRecyclerView);
        getUserList();
        return binding.getRoot();
    }

    private void setupRecyclerView(final XRecyclerView recyclerView) {
        UserAdapter adapter = new UserAdapter(binding.tvFg3);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.getDefaultFootView().setLoadingHint("加载中...");
        recyclerView.getDefaultFootView().setNoMoreHint("没有更多数据了");
        recyclerView.getDefaultFootView().setMinimumHeight(100);

        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
//                pageNum++;
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        recyclerView.refreshComplete();
                    }

                }, 0);            //refresh data here
            }

            @Override
            public void onLoadMore() {
                Log.e("aaaaa", "call onLoadMore");
                new Handler().postDelayed(new Runnable() {
                    public void run() {
//                        pageNum++;
//                        getMessageData();
                        recyclerView.refreshComplete();
                    }
                }, 1000);
            }
        });
    }

    private void getUserList(){
        binding.getThirdFragViewModel().getUserList();
    }
    @Override
    public void onUserListChanged(List<User> userList) {
        UserAdapter adapter =
                (UserAdapter) binding.xRecyclerView.getAdapter();
        Log.e("--userList--", "---userList-- "+ userList.size());
        adapter.setUserList(userList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        thirdFragViewModel.destroy();
    }
}
