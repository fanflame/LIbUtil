package com.fanyiran.utils.uniqueid;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;

/*
* 厂商定制系统的Bug：不同的设备可能会产生相同的ANDROID_ID：9774d56d682e549c。
* 厂商定制系统的Bug：有些设备返回的值为null。
* 手机恢复出厂设置以后该值会发生变化
* */
public class UniqueAndroidId extends UniqueIdAbstract {
    @Override
    public String getUniqueId(Context context) {
        String androidId = getAndroidId(context);
        if (TextUtils.isEmpty(androidId)) {
            return null;
        }
        return calcMD5(androidId + super.getUniqueId(context));
    }

    private static String getAndroidId(Context context) {
        try {
            String androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
            if ("9774d56d682e549c".equals(androidId)) {
                return null;
            }
            return androidId;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    @SuppressLint("DefaultLocale")
    @Override
    public String getTag() {
        return String.format("%d_androidid_", getPriority());
    }

    @Override
    public int getPriority() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return PRIORITY_APP;
        }
        return PRIORITY_DEVICE;
    }
}
