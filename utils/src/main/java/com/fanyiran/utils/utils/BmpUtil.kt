package com.fanyiran.utils.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory

object BmpUtil {
    public fun getScaled(bitmap: Bitmap, width: Int): Bitmap {
        val aspectRatio = bitmap.width * 1.0f / bitmap.height
        return Bitmap.createScaledBitmap(bitmap, width, (width / aspectRatio).toInt(), false)
    }

    fun getBitmap(path: String, maxWidth: Int): Bitmap {
        return getBitmap(path,true,maxWidth)
    }

    fun getBitmap(path: String, premltipliedAlpha:Boolean,maxWidth: Int): Bitmap {
        var options = BitmapFactory.Options()
        options.inPreferredConfig = Bitmap.Config.ARGB_8888
        options.inJustDecodeBounds = true
        options.inPremultiplied = premltipliedAlpha
        BitmapFactory.decodeFile(path, options)
        var fakeBitmapWidth = options.outWidth
        var sampleSize = 1
        while (fakeBitmapWidth > maxWidth) {
            sampleSize += 1
            fakeBitmapWidth /= sampleSize
        }
        options.inSampleSize = sampleSize
        options.inJustDecodeBounds = false
        return BitmapFactory.decodeFile(path, options)
    }
}