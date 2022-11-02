package com.example.software_engineering_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView


class SnakeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.snake_activity)

        val view: SnakeView = findViewById(R.id.snakeView)
        val score: TextView = findViewById(R.id.score)
        val go: TextView = findViewById(R.id.go)
        val game = SnakeGame()

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
                game.started = true
                Thread { game.play(score, go, view, btn) }.start()  // separate thread so UI doesnt freeze when calling sleep
                btn.text = "Stop"
            }
            else{
                game.started = false
                btn.text = "Restart"
            }
        }
    }
}