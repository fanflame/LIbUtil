package com.fanyiran.utils.recycleadapter;

import android.view.View;

/**
 * Created by fanqiang on 2019/4/16.
 */
interface RvListener<T extends ItemData> {
    void onClick(View view, T data, int position);

    void onLongClick(View view, T data, int position);
}
