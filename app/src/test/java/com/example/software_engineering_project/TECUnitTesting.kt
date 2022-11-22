package com.example.software_engineering_project

import org.junit.Test
import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine.
 */
class TECUnitTesting {
    // test for processing of direction inputs for Snake
    @Test
    fun test_snake_direction() {
        val game = SnakeGame()
        game.setDirection(1)  // set direction to right
        assertEquals(1, game.dir_buffer)
        game.dir = 1  // assume direction changed after one tick
        game.setDirection(3)  // set direction to left
        assertEquals(1, game.dir_buffer)  // can't do a 180, so direction stays right
    }
}