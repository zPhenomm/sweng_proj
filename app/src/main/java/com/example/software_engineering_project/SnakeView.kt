package com.example.software_engineering_project
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.util.Log

class SnakeView : View {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    // The snake is a list of coordinate tuples representing the body parts
    var snakebody = arrayListOf<Pair<Int, Int>>()
    val gridX = 16
    val gridY = 16
    var apple = Pair(1, 2)
    var started = false


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawRGB(0, 0, 0)

        if(started){
            val paint = Paint().apply{
                style = Paint.Style.FILL
                color = Color.GREEN
            }

            for(i in 1 until snakebody.size){
                canvas?.drawRect(snakebody[i].first * (width / gridX).toFloat(),
                    snakebody[i].second * (height / gridY).toFloat(),
                    snakebody[i].first * (width / gridX) + (width / gridX).toFloat(),
                    snakebody[i].second * (height / gridY) + (height / gridY).toFloat(), paint)
            }

            paint.color = Color.rgb(0, 128, 0)

            canvas?.drawRect(snakebody[0].first * (width / gridX).toFloat(),
                snakebody[0].second * (height / gridY).toFloat(),
                snakebody[0].first * (width / gridX) + (width / gridX).toFloat(),
                snakebody[0].second * (height / gridY) + (height / gridY).toFloat(), paint)

            paint.color = Color.RED

            canvas?.drawRect(apple.first * (width / gridX).toFloat(),
                apple.second * (width / gridY).toFloat(),
                apple.first * (width / gridX) + (width / gridX).toFloat(),
                apple.second * (width / gridY) + (width / gridY).toFloat(), paint)
        }
    }
}