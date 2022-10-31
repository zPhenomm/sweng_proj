package com.example.software_engineering_project
import android.view.View
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log


class SnakeView : View {

    constructor(context: Context) : super(context){
    }
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs){
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
    }

    fun redraw(){
        Log.i("TAG", "call")
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val s = width
        val t = height
        canvas?.drawRGB(255, 0, 0)
        Log.i("TAG", "ondraw")
        Log.i("TAG", s.toString())
        Log.i("TAG", t.toString())
    }

}