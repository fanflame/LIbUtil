package com.fanyiran.utils.uniqueid;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class UniqueIdManager {
    private static final String TAG = "UniqueIdManager";
    private List<IUniqueId> uniqueIds;
    private String uniqueId;
    private UniqueCache uniqueCache;

    private UniqueIdManager() {
        uniqueIds = new ArrayList<>();
//        uniqueIds.add(uniqueCache = new UniqueCache());
        uniqueIds.add(new UniqueMacAddress());
        uniqueIds.add(new UniqueDeviceId());
        uniqueIds.add(new UniqueEMEI());
        uniqueIds.add(new UniqueSerialNumber());
        uniqueIds.add(new UniqueAndroidId());
        uniqueIds.add(new UniqueUUID());
    }

    static class UniqueIdManagerHolder {
        static UniqueIdManager uniqueIdManager = new UniqueIdManager();
    }

    static public UniqueIdManager getInstance() {
        return UniqueIdManagerHolder.uniqueIdManager;
    }

    private String seekUniqueId(Context context) {
        String uniqueId;
        for (IUniqueId iUniqueId : uniqueIds) {
            uniqueId = iUniqueId.getUniqueId(context);
            if (!TextUtils.isEmpty(uniqueId)) {
//                String temp;
//                if (uniqueCache != iUniqueId) {
//                    temp = String.format("%s%s", iUniqueId.getTag(), uniqueId);
//                    uniqueCache.saveUnique(context, temp,iUniqueId.getPriority());
//                }else{
//                    temp = uniqueId;
//                }
//                MDLog.v(TAG,String.format("get uniqueid:%s",temp));
//                return temp;
            }
        }
        return String.format("no uniqueid create and android info:%s %s", Build.MANUFACTURER, Build.MODEL);
    }

    public String getUniqueId(Context context) {
        if (uniqueId == null) {
            uniqueId = seekUniqueId(context);
        }
        return uniqueId;
    }
}
