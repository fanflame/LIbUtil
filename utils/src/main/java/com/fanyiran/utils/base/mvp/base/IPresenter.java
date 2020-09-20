package com.fanyiran.utils.base.mvp.base;


import com.fanyiran.utils.LogUtil;

public abstract class IPresenter<V extends IView, M extends IModel> {
    private static final String TAG = "IPresenter";
    protected V iView;
    private M iModel;

    public IPresenter(V iView) {
        M iModel = generateIModel();
        if (iModel == null) {
            LogUtil.v(TAG, "iMode is null");
        }
        init(iView, iModel);
    }

    protected void init(V iView, M iModel) {
        this.iView = iView;
        this.iModel = iModel;
    }

    public V getIView() {
        return iView;
    }

    public void onDetachView() {
        iView = null;
    }

    public M getiModel() {
        return iModel;
    }

    public abstract M generateIModel();
}
