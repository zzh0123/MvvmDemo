package com.zhenhua.mvvmdemo;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhenhua.mvvmdemo.databinding.FragmentSecondBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {

    private TextView tv_second;

    private View rootView;
    //自动生成ActivityMainBinding类，命名规则是布局文件使用驼峰规则来命名
    private FragmentSecondBinding binding;

    private static Coupon coupon;
    private static int i = 1;
    public SecondFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_second,
                container, false);
        coupon = new Coupon();
        coupon.count.set(i);
        binding.setCoupon(coupon);
//        rootView = inflater.inflate(R.layout.fragment_second, container, false);
//        tv_second = (TextView) rootView.findViewById(R.id.tv_second);
        initView();
        return binding.getRoot();
    }

    private void initView(){
        binding.tvSecondClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                Log.i("--fg2-i---", "---i---" + i);
                coupon.count.set(i);
            }
        });
    }

    public static void setCount(int count){
        i = count;
        coupon.count.set(i);
    }

}
