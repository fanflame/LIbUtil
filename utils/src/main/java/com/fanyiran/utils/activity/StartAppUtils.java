package com.fanyiran.utils.activity;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.fanyiran.utils.ToastUtils;

/**
 * Created by fanqiang on 2019/2/13.
 */
public class StartAppUtils {
    public static void startAppQuick(Context context, Class targetActivity) {
        Intent intent = new Intent(context, targetActivity);
        startAppQuickWithIntent(context,intent);
    }

    public static void startAppQuickWithIntent(Context context, Intent intent) {
        PendingIntent pendingIntent =
                PendingIntent.getActivity(context, 0, intent, 0);
        try {
            pendingIntent.send();
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }
    }

    public static void startAppQuickByPacketName(Context context, String packetName) {
        try{
            Intent intent = context.getPackageManager().getLaunchIntentForPackage(packetName);
            startAppQuickWithIntent(context,intent);
        }catch(Exception e){
            ToastUtils.showText(context, "没有安装:"+packetName);
        }
    }
}
