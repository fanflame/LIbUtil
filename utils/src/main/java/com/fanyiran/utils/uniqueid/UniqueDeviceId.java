package com.fanyiran.utils.uniqueid;

import android.annotation.SuppressLint;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

/*
* 非手机设备： 如果只带有Wifi的设备或者音乐播放器没有通话的硬件功能的话就没有这个DEVICE_ID
* bug：在少数的一些手机设备上，该实现有漏洞，会返回垃圾，如:zeros或者asterisks的产品
* */
public class UniqueDeviceId extends UniqueIdAbstract {
    @Override
    public String getUniqueId(Context context) {
        if (!checkPermission(context, android.Manifest.permission.READ_PHONE_STATE)) {
            return null;
        }
        //实际测试
        // 华为mate 30 android 10系统不需要READ_PRIVILEGED_PHONE_STATE权限也可以拿到deviceid不为null
        // 华为nova 3e android 9可以拿到deviceid不为null
        // 华为honor 8x max android 9可以拿到deviceid不为null
        // 锤子OS 103 android 7.1.1可以拿到deviceid不为null

        //mi 9 android 10拿到deviceid为null

//        if (!checkReadPrivilegedPhoneStatePermission(context)) {
//            return null;
//        }
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String deviceId = tm.getDeviceId();
        if (TextUtils.isEmpty(deviceId)) {
            return null;
        }
        return calcMD5(deviceId + super.getUniqueId(context));
    }

    @SuppressLint("DefaultLocale")
    @Override
    public String getTag() {
        return String.format("%d_device_id_", getPriority());
    }

    @Override
    public int getPriority() {
        return PRIORITY_DEVICE;
    }
}
