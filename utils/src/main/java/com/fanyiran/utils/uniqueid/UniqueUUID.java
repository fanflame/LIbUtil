package com.fanyiran.utils.uniqueid;

import android.annotation.SuppressLint;
import android.content.Context;

import java.util.UUID;

public class UniqueUUID extends UniqueIdAbstract {
    @Override
    public String getUniqueId(Context context) {
        return calcMD5(UUID.randomUUID().toString() + super.getUniqueId(context));
    }

    @SuppressLint("DefaultLocale")
    @Override
    public String getTag() {
        return String.format("%d_uuid_",getPriority());
    }

    @Override
    public int getPriority() {
        return PRIORITY_APP;
    }
}
