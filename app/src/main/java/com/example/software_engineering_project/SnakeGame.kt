package com.example.software_engineering_project

import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import kotlin.random.Random

class SnakeGame{
    // The snake is a list of coordinate tuples representing the body parts
    var snakebody = arrayListOf<Pair<Int, Int>>()
    val gridX = 16
    val gridY = 16
    var apple = Pair(1, 2)
    var dir = 0
    var dir_buffer = 0
    var points = 0
    var started = false


    fun play(score: TextView, go: TextView, view: SnakeView, btn: Button){
        var last = Pair(0, 0)
        var tmp: Pair<Int, Int>
        var flag: Boolean
        snakebody.clear()
        snakebody.add(Pair(8, 8))
        snakebody.add(Pair(8, 9))
        snakebody.add(Pair(8, 10))
        score.text = "score: $points"
        started = true
        view.started = started
        view.invalidate()
        btn.setText("STOP")

        while(started){
            go.text = ""
            dir = dir_buffer  // take last button press from last sleep interval
            last = snakebody[0]
            if(dir == 0){  // up
                // move head
                snakebody[0] = Pair(snakebody[0].first, snakebody[0].second - 1)
                // if out of bounds teleport
                if(snakebody[0].second == -1){
                    snakebody[0] = Pair(snakebody[0].first, gridY - 1)
                }
            }
            else if(dir == 1){  // right
                // move head
                snakebody[0] = Pair(snakebody[0].first + 1, snakebody[0].second)
                // if out of bounds teleport
                if(snakebody[0].first == gridX){
                    snakebody[0] = Pair(0, snakebody[0].second)
                }
            }
            else if(dir == 2){ // down
                // move head
                snakebody[0] = Pair(snakebody[0].first, snakebody[0].second + 1)
                // if out of bounds teleport
                if(snakebody[0].second == gridY){
                    snakebody[0] = Pair(snakebody[0].first, 0)
                }
            }
            else if(dir == 3){  // left
                // move head
                snakebody[0] = Pair(snakebody[0].first - 1, snakebody[0].second)
                // if out of bounds teleport
                if(snakebody[0].first == -1){
                    snakebody[0] = Pair(gridX - 1, snakebody[0].second)
                }
            }
            // drag rest of the body and check collision
            for(i in 1 until snakebody.size){
                tmp = snakebody[i]
                snakebody[i] = last
                last = tmp
                if(snakebody[0] == snakebody[i]){
                    started = false
                }
            }
            // if apple eaten, add body part at tail, generate new apple outside of snake body
            if(snakebody[0] == apple && started){
                flag = true
                points++
                snakebody.add(last)  // This is so fucking slick I'm a genius
                while(flag){
                    flag = false
                    apple = Pair(Random.nextInt(0, gridX), Random.nextInt(0, gridY))
                    for(i in 0 until snakebody.size){  // TODO: this is kinda slow but idk how to do it differently
                        if(snakebody[i] == apple){
                            flag = true
                            break
                        }
                    }
                }
            }
            view.snakebody = snakebody
            view.apple = apple
            score.text = "score: $points"
            view.invalidate()
            try{
                Thread.sleep(200)  // 4 FPS!
            }catch(e: InterruptedException){
                btn.isClickable = false
                Thread.sleep(20)
                break
            }
        }
        go.text = "GAME OVER!"
        btn.text = "RESTART"
        apple = Pair(1, 2)
        points = 0
        dir = 0
        dir_buffer = 0
        started = false
        btn.isClickable = true
        return
    }


    // direction input needs to be temporarily stored in a buffer, otherwise it's possible to do a 180
    // by changing direction twice during one sleep cycle
    fun setDirection(d: Int){
        if(d == 0 && dir != 2){
            dir_buffer = d
        }
        if(d == 2 && dir != 0){
            dir_buffer = d
        }
        if(d == 1 && dir != 3){
            dir_buffer = d
        }
        if(d == 3 && dir != 1){
            dir_buffer = d
        }
    }
}