package com.fanyiran.utils.recycleadapter.actiivty;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fanyiran.utils.base.mvp.IBaseActivityView;
import com.fanyiran.utils.base.mvp.base.IPresenter;
import com.fanyiran.utils.recycleadapter.CreateRvHelper;
import com.fanyiran.utils.recycleadapter.ICreateRv;


/**
 * Created by fanqiang on 2019/4/17.
 */
public abstract class RvBaseActivity<P extends IPresenter> extends IBaseActivityView<P> implements ICreateRv {
    private CreateRvHelper createRvHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        createRvHelper = new CreateRvHelper.Builder(this).build();
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
    }

    @Override
    public RecyclerView.ItemDecoration getItemDecoration() {
        return new DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL);
    }
}
