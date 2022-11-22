/**
 * Custom View to display the Snake game
 *
 * @author Max Hannawald
 */
package com.example.software_engineering_project
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View


/**
 * Class for the custom View to display Snake.
 * Draws all elements.
 *
 * @constructor Create new Snake View
 */
class SnakeView : View {

    // helper constructors as this is extending the View object
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    /** The snake is a list of coordinate tuples representing the body parts */
    var snakebody = arrayListOf<Pair<Int, Int>>()
    /** Game view will be separated in 16 blocks vertically */
    private val gridX = 16
    /** Game view will be separated in 16 blocks horizontally */
    private val gridY = 16
    /** The apple is a coordinate tuple */
    var apple = Pair(1, 2)
    /** Flag to indicate if game is running */
    var started = false

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawRGB(0, 0, 0)

        if(started){
            val paint = Paint().apply{
                style = Paint.Style.FILL
                color = Color.GREEN
            }
            // draw the snake
            for(i in 1 until snakebody.size){
                canvas?.drawRect(snakebody[i].first * (width / gridX).toFloat(),
                    snakebody[i].second * (height / gridY).toFloat(),
                    snakebody[i].first * (width / gridX) + (width / gridX).toFloat(),
                    snakebody[i].second * (height / gridY) + (height / gridY).toFloat(), paint)
            }
            // draw the snake head
            paint.color = Color.rgb(0, 128, 0)
            canvas?.drawRect(snakebody[0].first * (width / gridX).toFloat(),
                snakebody[0].second * (height / gridY).toFloat(),
                snakebody[0].first * (width / gridX) + (width / gridX).toFloat(),
                snakebody[0].second * (height / gridY) + (height / gridY).toFloat(), paint)

            // draw the apple
            paint.color = Color.RED
            canvas?.drawRect(apple.first * (width / gridX).toFloat(),
                apple.second * (width / gridY).toFloat(),
                apple.first * (width / gridX) + (width / gridX).toFloat(),
                apple.second * (width / gridY) + (width / gridY).toFloat(), paint)
        }
    }
}
