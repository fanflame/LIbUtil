package com.fanyiran.utils.view.listdialog;

import com.fanyiran.utils.recycleadapter.RvItemData;

public class ListItemData extends RvItemData {
    private String content;

    public ListItemData(String item) {
        this.content = item;
    }

//    @Override
//    public int getItemType() {
//        return TypeIdGenerator.INSTANCE.getTypeId(ListDialogItem.class.getName());
//    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
