package com.fanyiran.utils.view.listdialog;

import android.widget.TextView;

import com.fanyiran.utils.R;
import com.fanyiran.utils.recycleadapter.ItemData;
import com.fanyiran.utils.recycleadapter.ItemTypeAbstract;
import com.fanyiran.utils.recycleadapter.RvViewHolder;
import com.fanyiran.utils.recycleadapter.TypeIdGenerator;

public class ListDialogItem extends ItemTypeAbstract {

    @Override
    public boolean openClick() {
        return true;
    }

    @Override
    public int getType() {
        return TypeIdGenerator.INSTANCE.getTypeId(Class.class.getName());
    }

    @Override
    public int getLayout() {
        return R.layout.dialog_list;
    }

    @Override
    public void fillContent(RvViewHolder rvViewHolder, int position, ItemData data) {
        ListItemData itemData = (ListItemData) data;
        ((TextView) rvViewHolder.getView(R.id.tvContent)).setText(itemData.getContent());
    }

    @Override
    public int[] getOnClickViews() {
        return new int[]{R.id.root};
    }
}
