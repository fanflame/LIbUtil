package com.fanyiran.utils.recycleadapter;

import android.util.SparseArray;

/**
 * Created by fanqiang on 2019/4/16.
 */
public class ItemManager {
    private static final ItemManager ourInstance = new ItemManager();
    private SparseArray<RvItemType> itemTypeSparseArray;

    public static ItemManager getInstance() {
        return ourInstance;
    }

    private ItemManager() {
        itemTypeSparseArray = new SparseArray<>();
    }

    public void addItems(RvItemType itemType) {
        if (itemType == null) {
            return;
        }
        itemTypeSparseArray.put(itemType.getType(), itemType);
    }

    public int getLayout(int viewType) {
        return itemTypeSparseArray.get(viewType).getLayout();
    }

    public RvItemType getItemType(int viewType) {
        return itemTypeSparseArray.get(viewType);
    }

    public <T extends RvItemData> int getType(T t, int position) {
        int size = itemTypeSparseArray.size();
        RvItemType itemType;
        for (int i = 0; i < size; i++) {
            itemType = itemTypeSparseArray.valueAt(i);
            if (itemType.isCurrentType(t, position)) {// NOTE: 比较骚的是这个判断需要具体实现完成
                return itemType.getType();
            }
        }
        throw new IllegalArgumentException("unknow msgType");
    }
}
