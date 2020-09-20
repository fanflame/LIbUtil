package com.fanyiran.utils.view.listdialog;

import android.widget.TextView;

import com.fanyiran.utils.R;
import com.fanyiran.utils.recycleadapter.RvItemData;
import com.fanyiran.utils.recycleadapter.RvItemTypeAbstract;
import com.fanyiran.utils.recycleadapter.RvViewHolder;

public class ListDialogItem extends RvItemTypeAbstract {

    @Override
    public boolean openClick() {
        return true;
    }

    @Override
    public int getLayout() {
        return R.layout.dialog_list;
    }

    @Override
    public void fillContent(RvViewHolder rvViewHolder, int position, RvItemData data) {
        ListItemData itemData = (ListItemData) data;
        ((TextView) rvViewHolder.getView(R.id.tvContent)).setText(itemData.getContent());
    }

    @Override
    public int[] getOnClickViews() {
        return new int[]{R.id.root};
    }
}
