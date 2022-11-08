package com.example.software_engineering_project

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

lateinit var locInput: EditText
lateinit var weatherImage: ImageView
lateinit var weatherText: TextView


class WeatherActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.weather_activity)

        locInput = findViewById(R.id.editLocation)
        weatherImage = findViewById(R.id.weatherImage)
        weatherText = findViewById(R.id.weatherText)

        val btn: Button = findViewById(R.id.btnGo)
        btn.setOnClickListener{
            getWeather(locInput.text.toString())
        }
    }


    fun getWeather(loc: String){
        val url = "https://api.openweathermap.org/data/2.5/weather?q=$loc&appid=76f9daa39f304e23a2e475d3950c7d20&units=metric"
        var weather = ""
        val queue = Volley.newRequestQueue(this)
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response -> weather = "Response: %s".format(response.toString()) },
            { error ->  weather = "error"}
        )
        queue.add(jsonObjectRequest)

        // utilizing a handler to wait for API response
        val handler = Handler()
        handler.post(object : Runnable {
            override fun run() {
                handler.postDelayed(this, 100)  // wait for API response
                if(weather != ""){
                    displayWeather(weather)
                    handler.removeCallbacks(this)
                }
            }
        })
    }


    fun displayWeather(weather: String){
        if(weather == "error"){
            weatherText.text = "Invalid location"
            weatherImage.setImageResource(android.R.color.transparent)
            return
        }
        // extract information from JSON
        // I really wish I was coding in Python right now
        var tmp = weather.substring(weather.indexOf("weather"), weather.indexOf("feels_like"))
        var icon = tmp.substring(tmp.indexOf("icon"), tmp.indexOf("\"base\""))
        icon = "w" + icon.substring(icon.indexOf(":") + 2, icon.indexOf("}") - 1)
        var des = tmp.substring(tmp.indexOf("description"), tmp.indexOf("icon"))
        des = des.substring(des.indexOf(":") + 2, des.indexOf(",") - 1)
        tmp = tmp.substring(tmp.indexOf("temp"))
        var temp = tmp.substring(tmp.indexOf(":") + 1, tmp.indexOf(",")) + "°"
        // display info
        weatherText.text = des + "\n"  + temp
        val resID = resources.getIdentifier(icon, "drawable", packageName)
        weatherImage.setImageResource(resID)
    }
}