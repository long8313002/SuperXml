package com.zhangzheng.superxml.library.ext

import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.widget.ImageView


fun View.setRadius(radius: Float) {
    val backgroundColor = getBackgroundColor(this)
    val background = getBackground(this)
    if (backgroundColor != null) {
        this.background = getRoundRectBgByColorValue( backgroundColor, radius)
    }else if(background!=null){
        this.background = BitmapDrawable(toRoundCorner(background,radius))
    }
}

fun View.setSrcRadius(radius: Float){

    if(this is ImageView){
        val src = drawable
        if(src is BitmapDrawable){
            setImageBitmap(toRoundCorner(src.bitmap,radius))
        }
    }
}

private fun getRoundRectBgByColorValue( color: Int, radius: Float) :Drawable{
    return GradientDrawable().also {
        it.shape = GradientDrawable.RECTANGLE
        it.cornerRadius = radius
        it.setColor(color)
    }
}


private fun getBackgroundColor(view: View): Int? {
    var bgColor: Int? = null
    if (view.background is ColorDrawable) {
        val colorDrawable = view.background as ColorDrawable
        bgColor = colorDrawable.color
    }
    return bgColor
}

private fun getBackground(view: View): Bitmap? {
    val background = view.background
    return if (background is BitmapDrawable) {
        background.bitmap
    } else {
        null
    }
}

private fun toRoundCorner(bitmap: Bitmap, pixels: Float): Bitmap? {
    val output =
        Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(output)
    val paint = Paint()
    val rect = Rect(0, 0, bitmap.width, bitmap.height)
    val rectF = RectF(rect)
    paint.isAntiAlias = true
    canvas.drawARGB(0, 0, 0, 0)
    canvas.drawRoundRect(rectF, pixels/2, pixels/2, paint)
    paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    canvas.drawBitmap(bitmap, rect, rect, paint)
    return output
}
