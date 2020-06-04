package com.fanyiran.utils.base.mvp.base;

public interface IView<P extends IPresenter> {
    P getIPresenter();

    void toast(String content);
}
