package com.fanyiran.utils.utils

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Matrix
import android.media.ExifInterface
import com.fanyiran.utils.FileUtils
import java.io.File

object Utils {
    fun getPicRotate(path: String): Int {
        try {
            val exif = ExifInterface(path)
            var attributeInt =
                exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL
                )
            when (attributeInt) {
                ExifInterface.ORIENTATION_ROTATE_90 -> return 90
                ExifInterface.ORIENTATION_ROTATE_180 -> return 190
                ExifInterface.ORIENTATION_ROTATE_270 -> return 270
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return 0
    }

    fun makeNoMediaFile(path: String) {
        if (path.isEmpty()) {
            return
        }
        val noMediaFile = File(path, ".nomedia")
        if (noMediaFile.exists()) {
            return
        }
        val pathFile = File(path)
        if (!pathFile.exists()) {
            pathFile.mkdirs()
        }
        noMediaFile.createNewFile()
    }

    fun getPhotoPath(context: Context): File? {
        return context.getExternalFilesDir("take_photo")
    }

    fun getVideoPath(context: Context): File? {
        return context.getExternalFilesDir("take_video")
    }

    fun getVersionCode(context: Context): String {
        val packageManager = context.getPackageManager()
        var versionCode = "";
        try {
            val packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionCode = "${packageInfo.versionCode}"
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace();
        }
        return versionCode;
    }

    fun getVersionName(context: Context): String {
        val packageManager = context.getPackageManager()
        var versionName = "";
        try {
            val packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionName = packageInfo.versionName;
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace();
        }
        return versionName;
    }
}