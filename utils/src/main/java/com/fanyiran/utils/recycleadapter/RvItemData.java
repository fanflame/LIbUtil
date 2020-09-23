package com.fanyiran.utils.recycleadapter;

public class RvItemData {
    public int getItemType() {
        return TypeIdGenerator.getInstance().getTypeId(this.getClass());
    }
}
