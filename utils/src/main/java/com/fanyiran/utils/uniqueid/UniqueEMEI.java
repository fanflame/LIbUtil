package com.fanyiran.utils.uniqueid;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

public class UniqueEMEI extends UniqueIdAbstract {
    @Override
    public String getUniqueId(Context context) {
        String imei = getIMEI(context);
        if (TextUtils.isEmpty(imei)) {
            return null;
        }
        return calcMD5(imei+ super.getUniqueId(context));
    }

    private String getIMEI(Context context) {
        if (!checkPermission(context,android.Manifest.permission.READ_PHONE_STATE)) {
            return null;
        }
        //实际测试
        //华为mate 30 android 10系统不需要READ_PRIVILEGED_PHONE_STATE权限也可以拿到imei不为null
        //华为nova 3e android 9可以拿到imei不为null
        //华为honor 8x max android 9可以拿到imei不为null
        //锤子OS103 android 7.1.1可以拿到imei不为null

        //小米9 android 10拿到imei为null
//        if (!checkReadPrivilegedPhoneStatePermission(context)) {
//            return null;
//        }
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (telephonyManager != null) {
            String imei;
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    imei = telephonyManager.getImei();
                } else {
                    imei = telephonyManager.getDeviceId();
                }

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            return imei;
        }
        return null;
    }

    @SuppressLint("DefaultLocale")
    @Override
    public String getTag() {
        return String.format("%d_imei_",getPriority());
    }

    @Override
    public int getPriority() {
        return PRIORITY_DEVICE;
    }
}
