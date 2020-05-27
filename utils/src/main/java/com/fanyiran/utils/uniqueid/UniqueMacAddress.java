package com.fanyiran.utils.uniqueid;

import android.annotation.SuppressLint;
import android.content.Context;

import com.fanyiran.utils.LogUtil;

import java.net.NetworkInterface;
import java.util.Enumeration;

public class UniqueMacAddress extends UniqueIdAbstract {
    private static final String TAG = "UniqueMacAddress";
    @Override
    public String getUniqueId(Context context) {
        byte[] wifiMac = getWifiMac();
        if (wifiMac == null) {
            return null;
        }
        return calcMD5(byteArrayToHex(wifiMac) + super.getUniqueId(context));
    }

    /*
    * 实际测试
    * 华为mate 30 android 10 即使关闭wifi可以拿到mac
    * 小米9 android 10 即使关闭wifi可以拿到mac
    * 华为nova 3e android 9 即使关闭wifi可以拿到mac
    * 华为honor 8x max 即使关闭wifi可以拿到mac
    *
    * 锤子OS103 android 7.1.1关闭wifi拿不到mac
    * */
    private byte[] getWifiMac() {
        try {
            Enumeration<NetworkInterface> enumeration = NetworkInterface.getNetworkInterfaces();
            if (enumeration == null) {
                return null;
            }
            while (enumeration.hasMoreElements()) {
                NetworkInterface netInterface = enumeration.nextElement();
                if (netInterface.getName().equals("wlan0")) {
                    return netInterface.getHardwareAddress();
                }
            }
        } catch (Exception e) {
            LogUtil.v(TAG, e.getMessage());
        }
        return null;
    }

    @SuppressLint("DefaultLocale")
    @Override
    public String getTag() {
        return String.format("%d_mac_",getPriority());
    }

    @Override
    public int getPriority() {
        return PRIORITY_DEVICE;
    }
}
