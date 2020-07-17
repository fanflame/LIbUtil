package com.fanyiran.utils.utils

import android.graphics.Bitmap

object BmpUtil {
    public fun getScaled(bitmap: Bitmap, width: Int): Bitmap {
        val aspectRatio = bitmap.width * 1.0f / bitmap.height
        return Bitmap.createScaledBitmap(bitmap, width, (width / aspectRatio).toInt(), false)
    }
}