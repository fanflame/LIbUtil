package com.fanyiran.utils.uniqueid;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;

public class UniqueSerialNumber extends UniqueIdAbstract {
    @Override
    public String getUniqueId(Context context) {
        String serial = null;
//        if (context.getApplicationInfo().targetSdkVersion >= Build.VERSION_CODES.O_MR1) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (!checkPermission(context, Manifest.permission.READ_PHONE_STATE)) {
                return null;
            }
            try {
                serial = Build.getSerial();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }else{
            try {
                serial = Build.SERIAL;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        if (TextUtils.isEmpty(serial) || "Unknown".equalsIgnoreCase(serial)) {
            return null;
        }
        return calcMD5(serial + super.getUniqueId(context));
    }

    @SuppressLint("DefaultLocale")
    @Override
    public String getTag() {
        return String.format("%d_serialnum_", getPriority());
    }

    @Override
    public int getPriority() {
        return PRIORITY_DEVICE;
    }
}
