package com.fanyiran.utils.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public abstract class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflateView(inflater);
//        ButterKnifenife.bind(this, view);
        initView(view);
        return view;
    }

    public abstract int getLayoutId();

    protected abstract void initView(View view);

    protected View inflateView(LayoutInflater inflater) {
        return inflater.inflate(getLayoutId(), null);
    }
}
