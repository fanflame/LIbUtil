package com.fanyiran.utils.base.mvp;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.fanyiran.utils.ToastUtils;
import com.fanyiran.utils.base.BaseFragment;
import com.fanyiran.utils.base.mvp.base.IPresenter;
import com.fanyiran.utils.base.mvp.base.IView;

public abstract class IBaseFragmentView<P extends IPresenter> extends BaseFragment implements IView {
    protected P presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        presenter = (P) getIPresenter();
        super.onCreate(savedInstanceState);
    }

    @Override
    public void toast(String content) {
        ToastUtils.showText(content);
    }

    @Override
    public void onDestroy() {
        presenter.onDetachView();
        super.onDestroy();
    }
}
