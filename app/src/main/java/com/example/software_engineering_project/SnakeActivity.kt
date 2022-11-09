/**
 * Activity to play the Snake game
 *
 * @author Max Hannawald
 */
package com.example.software_engineering_project

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


/**
 * Snake activity, sets all buttons and creates the view and game logic.
 * Also launches and ends the game.
 *
 * @constructor Create new Snake activity
 */
class SnakeActivity : AppCompatActivity() {
    /** As we can't pause the main thread, the game needs to run on a separate one */
    lateinit var gamethread : Thread

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.snake_activity)

        val view: SnakeView = findViewById(R.id.snakeView)
        val score: TextView = findViewById(R.id.score)
        val go: TextView = findViewById(R.id.go)
        val game = SnakeGame()
        // game on separate thread so UI doesnt freeze when calling sleep
        gamethread = Thread{game.play(score, go, view, findViewById(R.id.btnStart))}

        // create listeners for buttons
        var btn: Button = findViewById(R.id.btnUp)
        btn.setOnClickListener{
            game.setDirection(0)
        }
        btn = findViewById(R.id.btnDown)
        btn.setOnClickListener {
            game.setDirection(2)
        }
        btn = findViewById(R.id.btnLeft)
        btn.setOnClickListener{
            game.setDirection(3)
        }
        btn = findViewById(R.id.btnRight)
        btn.setOnClickListener{
            game.setDirection(1)
        }
        btn = findViewById(R.id.btnStart)
        btn.setOnClickListener{
            if(!game.started){
                // if thread was terminated before, create new one
                if(!gamethread.isAlive){
                    gamethread = Thread{game.play(score, go, view, btn)}
                    gamethread.start()
                }
            }
            else{
                // immediately end game thread when stop is pressed without waiting for sleep cycle
                if(gamethread.isAlive){
                    gamethread.interrupt()
                }
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        gamethread.interrupt()
    }
}

