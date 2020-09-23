package com.fanyiran.utils.base.mvp;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.fanyiran.utils.ToastUtils;
import com.fanyiran.utils.base.mvp.base.IView;
import com.fanyiran.utils.recycleadapter.actiivty.RvBaseFragment;

public abstract class IRvMvpBaseFragmentView<P> extends RvBaseFragment implements IView {
    protected P presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = (P) getIPresenter();
    }

    @Override
    public void toast(String content) {
        ToastUtils.showText(content);
    }
}
