package com.example.software_engineering_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.TextView
import android.util.Log


class SnakeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.snake_activity)

        val view: SnakeView = findViewById(R.id.snakeView)
        val score: TextView = findViewById(R.id.score)
        val game = SnakeGame()
        var started = false

        // create listeners for buttons
        var btn: Button = findViewById(R.id.btnUp)
        btn.setOnClickListener{
            if(game.dir != 2){
                game.dir = 0
            }
        }

        btn = findViewById(R.id.btnDown)
        btn.setOnClickListener {
            if(game.dir != 0){
                game.dir = 2
            }
        }
        btn = findViewById(R.id.btnLeft)
        btn.setOnClickListener{
            if(game.dir != 1) {
                game.dir = 3
            }
        }
        btn = findViewById(R.id.btnRight)
        btn.setOnClickListener{
            if(game.dir != 3){
                game.dir = 1
            }
        }
        btn = findViewById(R.id.btnStart)
        btn.setOnClickListener{
            if(!started){
                started = true
                Thread { game.play(score, view) }.start()
            }
        }
    }
}