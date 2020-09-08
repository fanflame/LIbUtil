package com.fanyiran.utils.uniqueid;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;

import com.fanyiran.utils.LogUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

public class UniqueCache extends UniqueIdAbstract {
    private static final String TAG = "UniqueCache";
    private static final String SP_UNIQUE_ID = "fq_push_uninque_id";
    private static final String SP_UNIQUE_ID_KEY = "fq_push_uninque_id_key";
    private static final String UNIQUE_EXTRA_PATH = ".backup/.fq/.push/.uniqueidcache";

    @Override
    public String getUniqueId(Context context) {
        super.getUniqueId(context);
        return getCacheUniqueId(context);
    }

    private String getCacheUniqueId(Context context) {
        String cachedUniqueId = getCacheUniqueIdFromInner(context);
        if (TextUtils.isEmpty(cachedUniqueId)) {
            cachedUniqueId = getUniqueIdFromSdCard(context);
            if (!TextUtils.isEmpty(cachedUniqueId)) {
                LogUtil.v(TAG, String.format("getUniqueId from sdcard:%s", cachedUniqueId));
                saveUniqueInner(context, cachedUniqueId);//从sdcard读取到的uniqueid保存到内部存储
            }
        } else {
            LogUtil.v(TAG, String.format("getUniqueId from inner:%s", cachedUniqueId));
            saveUniqueSdCard(context, cachedUniqueId);//尝试保存到sdcard如果之前没有权限
        }
        return cachedUniqueId;
    }

    private String getCacheUniqueIdFromInner(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_UNIQUE_ID, Context.MODE_PRIVATE);
        return sharedPreferences.getString(SP_UNIQUE_ID_KEY, "");
    }

    private String getUniqueIdFromSdCard(Context context) {
        if (!checkPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            return "";
        }
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return "";
        }
        File file = new File(Environment.getExternalStorageDirectory(), UNIQUE_EXTRA_PATH);
        if (!file.exists()) {
            return null;
        }
        RandomAccessFile f = null;
        try {
            f = new RandomAccessFile(file, "r");
            byte[] bytes = new byte[(int) f.length()];
            f.readFully(bytes);
            f.close();
            return new String(bytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void saveUnique(Context context, String uniqueId, int priority) {
        if (priority >= IUniqueId.PRIORITY_DEVICE) {
            saveUniqueSdCard(context, uniqueId);
        } else if (priority >= IUniqueId.PRIORITY_APP) {
            saveUniqueInner(context, uniqueId);
        }
    }

    private void saveUniqueInner(Context context, String uniqueId) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_UNIQUE_ID, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(SP_UNIQUE_ID_KEY, uniqueId);
        edit.apply();
    }

    private void saveUniqueSdCard(Context context, String uniqueId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                return;
            }
        }
        File file = new File(Environment.getExternalStorageDirectory(), UNIQUE_EXTRA_PATH);
        if (file.exists()) {
            return;
        } else {
            File parentFile = file.getParentFile();
            //noinspection ResultOfMethodCallIgnored
            parentFile.mkdirs();
            try {
                //noinspection ResultOfMethodCallIgnored
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
            out.write(uniqueId.getBytes());
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            //noinspection ResultOfMethodCallIgnored
            file.delete();
        } catch (IOException e) {
            e.printStackTrace();
            //noinspection ResultOfMethodCallIgnored
            file.delete();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getTag() {
        return "";
    }

    @Override
    public @Priority
    int getPriority() {
        return PRIORITY_UNDEFINE;
    }
}
