package org.example

class MeanExample {
    /*
    Possesses a correct and incorrect example of a mathematical mean function.
     */
    fun mean(arr: List<Int>): Double {
        /*
        Given an integer array of size > 0, return the mean of its items.
         */
        var sum = 0
        for (i in 0..<arr.count()) {
            sum += arr[i]
        }

        return sum.toDouble() / arr.count()
    }

    fun mean_incorrect(arr: List<Int>): Int {
        /*
        Given an integer array of size > 0, return the mean of its items.

        This function is written incorrectly to demonstrate unit testing capabilities.
         */
        var sum = 0
        for (i in 0..<arr.count()) {
            sum += arr[i]
        }
        // integer division
        return sum / arr.count()
    }
}


fun main() {
    val example = MeanExample()

    val testList = listOf(5, 2, 3)

    println("The mean of $testList is equal to ${example.mean(testList)}, not ${example.mean_incorrect(testList)}")
}