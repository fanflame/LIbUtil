package com.fanyiran.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by fanqiang on 2018/12/7.
 */
public class SharedPreferUtil {
    private static SharedPreferUtil instance;
    private final String DEFALUT = "default";
    private Context appContext;

    public static SharedPreferUtil getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPreferUtil(context);
        }
        return instance;
    }

    private SharedPreferUtil(Context context) {
        if (context == null)
            return;
        appContext = context.getApplicationContext();
    }

    public void saveObject(String key, Object value) {
        saveObject(DEFALUT, key, value);
    }

    /**
     * //TODO
     * @param key
     * @param value
     * @param isEnc 是否加密
     */
    public void saveObject(String key, Object value,boolean isEnc) {
        saveObject(DEFALUT, key, value);
    }

    public void saveObject(String shareName, String key, Object value) {
        SharedPreferences sharedPreferences = appContext.getSharedPreferences(shareName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Class valueClass = value.getClass();
        if (valueClass.equals(Integer.class)) {
            editor.putInt(key, (int) value);
        } else if (valueClass.equals(String.class)) {
            editor.putString(key, (String) value);
        } else if (valueClass.equals(Boolean.class)) {
            editor.putBoolean(key, (Boolean) value);
        } else if (valueClass.equals(Long.class)) {
            editor.putLong(key, (Long) value);
        } else if (valueClass.equals(Float.class)) {
            editor.putFloat(key, (Float) value);
        }
        editor.apply();
    }

    public Object getObject(String key, Object value) {
        return getObject(DEFALUT, key, value);
    }

    public Object getObject(String shareName, String key, Object defaultValue) {
        SharedPreferences sharedPreferences = appContext.getSharedPreferences(shareName, Context.MODE_PRIVATE);
        Class valueClass = defaultValue.getClass();
        Object object = null;
        if (valueClass.equals(Integer.class)) {
            object = sharedPreferences.getInt(key, (Integer) defaultValue);
        } else if (valueClass.equals(String.class)) {
            object = sharedPreferences.getString(key, (String) defaultValue);
        } else if (valueClass.equals(Boolean.class)) {
            object = sharedPreferences.getBoolean(key, (Boolean) defaultValue);
        } else if (valueClass.equals(Long.class)) {
            object = sharedPreferences.getLong(key, (Long) defaultValue);
        } else if (valueClass.equals(Float.class)) {
            object = sharedPreferences.getFloat(key, (Float) defaultValue);
        }
        return object;
    }
}
