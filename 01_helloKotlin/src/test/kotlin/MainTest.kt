package org.example

import kotlin.test.Test
import kotlin.test.assertEquals
import org.example.MeanExample as meanExample

class MainTest {
    /*
    This class tests the MeanExample class in Main.kt.
    */

    // mean() should be capable returning a value that can be cast to an integer
    @Test
    fun to_int() {
        val example = meanExample()
        val testArr: List<Int> = listOf(4, 5, 3)
        assertEquals(example.mean(testArr).toInt(), 4)
        assertEquals(example.mean_incorrect(testArr), 4)
    }

    // given a one-element array, mean() should return that element
    @Test
    fun one_element() {
        val example = meanExample()
        val testArr: List<Int> = listOf(5)
        val expected: Double = 5.0
        assertEquals(expected, example.mean(testArr))
        assertEquals(expected, example.mean_incorrect(testArr).toDouble())
    }

    // mean() should return type Double
    @Test
    fun return_double() {
        val example = meanExample()
        val testArr: List<Int> = listOf(4, 5, 2)
        assertEquals(example.mean(testArr), 9.667, absoluteTolerance = 0.001)
        assertEquals(example.mean_incorrect(testArr).toDouble(), 9.667, absoluteTolerance = 0.001)
    }
}