package com.example.software_engineering_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.TextView
import android.util.Log


class SnakeActivity : AppCompatActivity(), OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.snake_activity)

        // create listeners for buttons
        var btn: Button = findViewById(R.id.btnUp)
        btn.setOnClickListener(this)
        btn = findViewById(R.id.btnDown)
        btn.setOnClickListener(this)
        btn = findViewById(R.id.btnLeft)
        btn.setOnClickListener(this)
        btn = findViewById(R.id.btnRight)
        btn.setOnClickListener(this)
        btn = findViewById(R.id.btnStart)
        btn.setOnClickListener(this)

        val view: SnakeView = findViewById(R.id.snakeView)
        val score: TextView = findViewById(R.id.score)

    }

    // handler for button clicks
    override fun onClick(v: View) {
        when (v.getId()) {
            R.id.btnWeather -> {
                val intent = Intent(this, WeatherActivity::class.java)
                startActivity(intent)
            }
            R.id.btnCalc -> {
                val intent = Intent(this, CalcActivity::class.java)
                startActivity(intent)
            }
            R.id.btnSnake -> {
                val intent = Intent(this, SnakeActivity::class.java)
                startActivity(intent)
            }
            R.id.btnStop -> {
                val intent = Intent(this, StopActivity::class.java)
                startActivity(intent)
            }
            R.id.btnNotes -> {
                val intent = Intent(this, NotesActivity::class.java)
                startActivity(intent)
            }
        }
    }

}