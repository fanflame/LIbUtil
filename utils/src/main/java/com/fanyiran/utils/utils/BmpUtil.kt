package com.fanyiran.utils.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix

object BmpUtil {
    fun getScaled(bitmap: Bitmap, width: Int): Bitmap {
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

    fun rotaingBmp(angle: Int, bitmap: Bitmap): Bitmap {
        var returnBm: Bitmap? = null
        // 根据旋转角度，生成旋转矩阵
        val matrix = Matrix()
        matrix.postRotate(-angle.toFloat())
        try {
            // 将原始图片按照旋转矩阵进行旋转，并得到新的图片
            returnBm = Bitmap.createBitmap(
                    bitmap,
                    0,
                    0,
                    bitmap.getWidth(),
                    bitmap.getHeight(),
                    matrix,
                    true
            )
        } catch (e: OutOfMemoryError) {
        }
        if (returnBm == null) {
            returnBm = bitmap
        }
        if (bitmap !== returnBm) {
            bitmap.recycle()
        }
        return returnBm
    }
}