package com.zhenhua.mvvmdemo;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhenhua.mvvmdemo.databinding.FragmentFirstBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {


    private TextView tv_first;

    private View rootView;
    private Coupon coupon;
    private int i = 1;
    //自动生成ActivityMainBinding类，命名规则是布局文件使用驼峰规则来命名
    private FragmentFirstBinding binding;

    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_first, container, false );
        coupon = new Coupon();
        coupon.count.set(i);
        binding.setCoupon(coupon);
        initView();
        return binding.getRoot();
    }

    private void initView(){
        binding.tvFirstClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                Log.i("-fg1--i---", "---i---" + i);
                coupon.count.set(i);
                SecondFragment.setCount(i);
            }
        });
    }

}
