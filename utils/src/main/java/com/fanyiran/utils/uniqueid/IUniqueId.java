package com.fanyiran.utils.uniqueid;

import android.content.Context;

import androidx.annotation.IntDef;

public interface IUniqueId {
    int PRIORITY_UNDEFINE = 0;
    int PRIORITY_APP = 1;
    int PRIORITY_DEVICE = 2;

    @IntDef({PRIORITY_UNDEFINE,PRIORITY_DEVICE,PRIORITY_APP})
    @interface Priority{}

    String getUniqueId(Context context);

    String getTag();

    @Priority int getPriority();
}
