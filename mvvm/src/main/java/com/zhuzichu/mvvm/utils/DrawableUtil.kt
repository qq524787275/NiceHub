package com.zhuzichu.mvvm.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.view.View
import android.widget.ImageView

//节省每次创建时产生的开销，但要注意多线程操作synchronized
private val sCanvas = Canvas()

fun createBitmapFromView(view: View): Bitmap? {
    return createBitmapFromView(view, 1f)
}

fun createBitmapFromView(view: View, scale: Float): Bitmap? {
    if (view is ImageView) {
        val drawable = view.drawable
        if (drawable != null && drawable is BitmapDrawable) {
            return drawable.bitmap
        }
    }
    view.clearFocus()
    val bitmap = createBitmapSafely((view.width * scale).toInt(),
            (view.height * scale).toInt(), Bitmap.Config.ARGB_8888, 1)
    if (bitmap != null) {
        synchronized(sCanvas) {
            val canvas = sCanvas
            canvas.setBitmap(bitmap)
            canvas.save()
            canvas.drawColor(Color.WHITE) // 防止 View 上面有些区域空白导致最终 Bitmap 上有些区域变黑
            canvas.scale(scale, scale)
            view.draw(canvas)
            canvas.restore()
            canvas.setBitmap(null)
        }
    }
    return bitmap
}

fun createBitmapSafely(width: Int, height: Int, config: Bitmap.Config, retryCount: Int): Bitmap? {
    try {
        return Bitmap.createBitmap(width, height, config)
    } catch (e: OutOfMemoryError) {
        e.printStackTrace()
        if (retryCount > 0) {
            System.gc()
            return createBitmapSafely(width, height, config, retryCount - 1)
        }
        return null
    }

}