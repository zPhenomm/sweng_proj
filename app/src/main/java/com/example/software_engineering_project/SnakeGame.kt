package com.example.software_engineering_project

import android.util.Log
import android.widget.TextView

class SnakeGame {
    var dir = 0
    // The snake is a list of coordinate tuples representing the body parts
    var snakebody = arrayListOf<Pair<Int, Int>>()
    val gridX = 16
    val gridY = 16
    var apple = Pair(1f, 2f)
    var started = false


    fun play(score: TextView, view: SnakeView){
        started = true
        var last = Pair(0, 0)
        var tmp = Pair(0, 0)
        snakebody.add(Pair(8, 8))
        snakebody.add(Pair(8, 9))
        snakebody.add(Pair(8, 10))
        score.text = "score: 0"
        view.invalidate()
        while(started){
            if(dir == 0){  // up
                // move head
                last = snakebody[0]
                snakebody[0] = Pair(snakebody[0].first, snakebody[0].second - 1)
                // if out of bounds teleport
                if(snakebody[0].second == -1){
                    snakebody[0] = Pair(snakebody[0].first, gridY)
                }
                // drag rest of the body
                for(i in 1 until snakebody.size){
                    tmp = snakebody[i]
                    snakebody[i] = last
                    last = tmp
                }
            }
            else if(dir == 1){  // right
                // move head
                last = snakebody[0]
                snakebody[0] = Pair(snakebody[0].first + 1, snakebody[0].second)
                // if out of bounds teleport
                if(snakebody[0].first == gridX + 1){
                    snakebody[0] = Pair(0, snakebody[0].second)
                }
                // drag rest of the body
                for(i in 1 until snakebody.size){
                    tmp = snakebody[i]
                    snakebody[i] = last
                    last = tmp
                }
            }
            else if(dir == 2){ // down
                // move head
                last = snakebody[0]
                snakebody[0] = Pair(snakebody[0].first, snakebody[0].second + 1)
                // if out of bounds teleport
                if(snakebody[0].second == gridY + 1){
                    snakebody[0] = Pair(snakebody[0].first, 0)
                }
                // drag rest of the body
                for(i in 1 until snakebody.size){
                    tmp = snakebody[i]
                    snakebody[i] = last
                    last = tmp
                }
            }
            else if(dir == 3){  // left
                // move head
                last = snakebody[0]
                snakebody[0] = Pair(snakebody[0].first - 1, snakebody[0].second)
                // if out of bounds teleport
                if(snakebody[0].first == -1){
                    snakebody[0] = Pair(gridX, snakebody[0].second)
                }
                // drag rest of the body
                for(i in 1 until snakebody.size){
                    tmp = snakebody[i]
                    snakebody[i] = last
                    last = tmp
                }
            }
            view.snakebody = snakebody
            view.started = started
            view.invalidate()
            Thread.sleep(300)
        }
    }
}