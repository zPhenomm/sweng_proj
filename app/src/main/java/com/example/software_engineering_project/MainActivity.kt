/**
 * The main menu of my Everyday Companion App
 *
 * @author Max Hannawald
 */
package com.example.software_engineering_project

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate


/**
 * Creates the main menu, maps buttons, sets the submenu for options
 *
 * @constructor Create the Main activity
 */
class MainActivity : AppCompatActivity(), OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // create listeners for buttons
        var btn: Button = findViewById(R.id.btnCalc)
        btn.setOnClickListener(this)
        btn = findViewById(R.id.btnNotes)
        btn.setOnClickListener(this)
        btn = findViewById(R.id.btnSnake)
        btn.setOnClickListener(this)
        btn = findViewById(R.id.btnStop)
        btn.setOnClickListener(this)
        btn = findViewById(R.id.btnWeather)
        btn.setOnClickListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    // toggle dark mode
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_UNSPECIFIED ||
            AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        return super.onOptionsItemSelected(item)
    }

    // main menu buttons onclick
    override fun onClick(v: View) {
        when(v.id){
            R.id.btnWeather -> {val intent = Intent(this, WeatherActivity::class.java)
                startActivity(intent)}
            R.id.btnCalc -> {val intent = Intent(this, CalcActivity::class.java)
                startActivity(intent)}
            R.id.btnSnake -> {val intent = Intent(this, SnakeActivity::class.java)
                startActivity(intent)}
            R.id.btnStop -> {val intent = Intent(this, StopActivity::class.java)
                startActivity(intent)}
            R.id.btnNotes -> {val intent = Intent(this, NotesActivity::class.java)
                startActivity(intent)}
        }
    }
}
