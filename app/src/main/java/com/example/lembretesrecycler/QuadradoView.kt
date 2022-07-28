package com.example.lembretesrecycler

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup

class QuadradoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    style: Int = 0): View(context, attrs, style) {

    var corDoQuadrado: Int

    init {
        val styledAttrs = context.obtainStyledAttributes(attrs, R.styleable.QuadradoView)
        corDoQuadrado = styledAttrs.getColor(R.styleable.QuadradoView_corDoQuadrado, Color.BLACK)
        styledAttrs.recycle()
    }
    private var tamanho: Int = 0
    private lateinit var paint: Paint
    private val rect: RectF = RectF()

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.style = Paint.Style.FILL
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        tamanho = when(layoutParams.width){
            ViewGroup.LayoutParams.WRAP_CONTENT ->{
                (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 48F, resources.displayMetrics)).toInt()
            }

            ViewGroup.LayoutParams.MATCH_PARENT ->{
                Math.min(View.MeasureSpec.getSize(widthMeasureSpec), View.MeasureSpec.getSize(heightMeasureSpec))
            }

            else -> layoutParams.width
        }
        setMeasuredDimension(tamanho, tamanho)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        paint.color = corDoQuadrado
        paint.strokeWidth = 5F

        canvas?.drawPaint(paint)
    }
}