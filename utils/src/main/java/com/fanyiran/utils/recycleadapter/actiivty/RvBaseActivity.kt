package com.fanyiran.utils.recycleadapter.actiivty

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fanyiran.utils.base.BaseActivity
import com.fanyiran.utils.recycleadapter.CreateRvHelper
import com.fanyiran.utils.recycleadapter.ICreateRv

open abstract class RvBaseActivity : BaseActivity(), ICreateRv {
    private var createRvHelper: CreateRvHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
    }

    @CallSuper
    override fun onSetContentViewEnd() {
        createRvHelper = CreateRvHelper.Builder(this).build()
    }

    override fun getLayoutManager(): RecyclerView.LayoutManager? {
        return LinearLayoutManager(
                this,
                RecyclerView.VERTICAL,
                false
        )
    }

    override fun getItemDecoration(): RecyclerView.ItemDecoration? {
        return DividerItemDecoration(
                this,
                LinearLayoutManager.HORIZONTAL
        )
    }
}