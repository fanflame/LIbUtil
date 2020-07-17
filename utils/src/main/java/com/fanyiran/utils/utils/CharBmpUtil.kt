package com.fanyiran.utils.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

object CharBmpUtil {
    //灰度值15个等级，根据颜色的深度给对应的中文字
    val content by lazy { arrayOf("餮", "淼", "圆", "困", "品", "回", "田", "凸", "口", "王", "天", "干", "工", "十", "一") }
    public fun charToBmp(bmp: Bitmap): Bitmap? {
        val width = 400
        var scaledBmp = BmpUtil.getScaled(bmp, width)

        val height = scaledBmp.height
        var color = 0
        var red = 0
        var green = 0
        var blue = 0
        var gray = 0
        val fontSize = 6
        var result = Bitmap.createBitmap(width * fontSize, height * fontSize, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(result)
        canvas.drawColor(Color.WHITE)
        val paint = Paint()
        paint.textSize = fontSize.toFloat()
        paint.strokeWidth = 1F
        paint.color = Color.BLACK
        for (j in 0 until height) {
            for (i in 0 until width) {
                color = scaledBmp.getPixel(i, j)
                red = (color and 0x00ff0000) shr 16
                green = color and 0x0000ff00 shr 8
                blue = color and 0x000000ff
                gray = (red * 0.4 + green * 0.3 + blue * 0.3).toInt() / 18  //这个值是关键，为啥18？  256/18= 14.2222(15个灰度等级)
                canvas.drawText(content[gray], (i * fontSize).toFloat(), (j * fontSize).toFloat(), paint)
            }
        }
        scaledBmp.recycle()
        return result
    }
}