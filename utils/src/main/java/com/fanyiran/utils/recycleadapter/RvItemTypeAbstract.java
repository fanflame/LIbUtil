package com.fanyiran.utils.recycleadapter;

public abstract class RvItemTypeAbstract<T extends RvItemData> implements RvItemType<T> {
    private int cacheTypeId = -1;

    @Override
    public boolean openLongClick() {
        return false;
    }

    @Override
    public boolean isCurrentType(T data, int position) {
        return data.getItemType() == getType();
    }

    @Override
    public int[] getOnLongClickViews() {
        return new int[0];
    }

    @Override
    public int getType() {
        if (cacheTypeId == -1) {
            cacheTypeId = TypeIdGenerator.getInstance().getTypeIdByGeneric(this);
        }
        return cacheTypeId;
    }
}
