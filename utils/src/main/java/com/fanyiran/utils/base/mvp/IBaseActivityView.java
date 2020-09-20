package com.fanyiran.utils.base.mvp;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.fanyiran.utils.ToastUtils;
import com.fanyiran.utils.base.BaseActivity;
import com.fanyiran.utils.base.mvp.base.IPresenter;
import com.fanyiran.utils.base.mvp.base.IView;

public abstract class IBaseActivityView<P extends IPresenter> extends BaseActivity implements IView {
    protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = (P) getIPresenter();
        if (presenter == null) {
            throw new IllegalStateException("chatPresenter is null");
        }
    }

    @Override
    public void toast(String content) {
        ToastUtils.showText(content);
    }


    @Override
    protected void onDestroy() {
        presenter.onDetachView();
        super.onDestroy();
    }
}
