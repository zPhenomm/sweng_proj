/**
 * A stopwatch
 *
 * @author Max Hannawald
 */
package com.example.software_engineering_project

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale


/** The View to display the time */
lateinit var viewClock: TextView
/** Count of 100 milliseconds */
var hundredths = 0
/** Indicates if stopwatch is running */
var running = false

/**
 * Stopwatch activity. Creates buttons and stops time.
 *
 * @constructor Create empty Stop activity
 */
class StopActivity : AppCompatActivity(), OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.stop_acvtivity)

        viewClock = findViewById(R.id.timer)

        // create listeners for buttons
        var btn: Button = findViewById(R.id.startClock)
        btn.setOnClickListener(this)
        btn = findViewById(R.id.stopClock)
        btn.setOnClickListener(this)
        btn = findViewById(R.id.resetClock)
        btn.setOnClickListener(this)

        // save timer so you can do something else and keep the timer running
        if (savedInstanceState != null) {
            hundredths = savedInstanceState.getInt("hundredths")
            running = savedInstanceState.getBoolean("running")
        }
        runTimer()
    }

    // onclick action
    override fun onClick(v: View) {
        when(v.id){
            R.id.startClock -> running = true
            R.id.stopClock -> running = false
            R.id.resetClock -> {running = false
                                hundredths = 0}
        }
    }

    // restore states after return
    override fun onSaveInstanceState(
        savedInstanceState: Bundle
    ) {
        savedInstanceState.putInt("hundredths", hundredths)
        savedInstanceState.putBoolean("running", running)
    }

    /**
     * Handles the stopwatch. Counts every 100 milliseconds
     * Converts counter to other timeunits and displays.
     */
    private fun runTimer() {
        // small code so unlike snake we can use a handler instead of a separate class in a thread
        val handler = Handler()
        handler.post(object : Runnable {
            override fun run() {
                val hundred: Int = hundredths % 10
                val secs: Int = (hundredths / 10) % 60
                val mins: Int = (hundredths / 600) % 60
                val hrs: Int = (hundredths / 36000)

                val time: String = java.lang.String.format(Locale.getDefault(),
                                    "%d:%02d:%02d:%01d", hrs, mins, secs, hundred)
                viewClock.text = time

                if (running) {
                    hundredths++
                }
                handler.postDelayed(this, 99)  // account for exec time
            }
        })
    }
}
