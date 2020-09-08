package com.fanyiran.utils.view.listdialog;


import com.fanyiran.utils.recycleadapter.RvBaseAdapter;

import java.util.List;

public class ListDialogAdapter extends RvBaseAdapter {
    public ListDialogAdapter(List baseDataList) {
        super(baseDataList);
        addItemType(new ListDialogItem());
    }
}
