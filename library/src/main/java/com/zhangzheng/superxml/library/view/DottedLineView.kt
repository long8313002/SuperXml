package com.zhangzheng.superxml.library.view

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import com.zhangzheng.superxml.library.R

internal class DottedLineView : View {

    private var dashWidth = 0
    private var dashHeight = 0
    private var dashGap = 0

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        val typedArray: TypedArray =
            context!!.obtainStyledAttributes(attrs, R.styleable.decorate_view)
        dashWidth = typedArray.getDimension(
            R.styleable.decorate_view_layout_dash_width, 0f
        ).toInt()

        dashHeight = typedArray.getDimension(
            R.styleable.decorate_view_layout_dash_height, 0f
        ).toInt()

        dashGap = typedArray.getDimension(
            R.styleable.decorate_view_layout_dash_gap, 0f
        ).toInt()

        typedArray.recycle()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )


    override fun onDraw(canvas: Canvas) {

        background.setBounds(0, 0, dashWidth, dashHeight)
        background.draw(canvas)

        if (width > height) {
            var translateX = 0
            while (translateX <= width) {
                canvas.translate((dashWidth + dashGap).toFloat(), 0f)
                background.draw(canvas)
                translateX += dashWidth + dashGap
            }
        }else{
            var translateY = 0
            while (translateY <= height) {
                canvas.translate(0f, (dashHeight + dashGap).toFloat())
                background.draw(canvas)
                translateY += dashHeight + dashGap
            }
        }


    }
}