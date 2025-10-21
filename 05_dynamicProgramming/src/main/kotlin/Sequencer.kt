package org.example

import kotlin.math.max

class Sequencer {
    fun needlemanWunsch(s1: String, s2: String): Int {
        val valueTable: Array<Array<Int>> = Array(s1.length + 1) { Array(s2.length + 1) { 0 } }

        // first row is epsilon_1
        for (i in 0 until s2.length + 1) {
            valueTable[0][i] = i * -1
        }

        // first column is epsilon_2
        for (j in 0 until s1.length + 1) {
            valueTable[j][0] = j * -1
        }

        // now use dynamic programming to fill the rest of the table
        for (i in 1 until s1.length + 1) {
            for (j in 1 until s2.length + 1) {
                val alignment = when (s1[i - 1] == s2[j - 1]) {
                    true -> 1
                    false -> -1
                }
                val match = valueTable[i-1][j-1] + alignment
                val s1Insertion = -1 + valueTable[i-1][j]
                val s2Insertion = -1 + valueTable[i][j-1]
                valueTable[i][j] = max(match, max(s1Insertion, s2Insertion))
            }
        }

        // return full sequencing score
        return valueTable[s1.length][s2.length]
    }
}