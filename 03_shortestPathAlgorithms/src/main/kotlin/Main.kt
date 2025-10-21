package org.example

import java.io.File

/**
 * Convert a CSV file of integers to a 2D array/matrix.
 *
 * @param filepath a string filepath to a CSV of integers.
 * @return a 2D array representation of an integer matrix.
 */

fun csvToMatrix(filepath: String): Array<Array<Int>> {
    val rows = File(filepath).readLines()
    return Array(rows.size) { i ->
        val items = rows[i].split(",")
        Array(items.size) { j ->
            items[j].toInt()
        }
    }
}

/**
 * Convert a 2D array matrix into a directed weighted graph.
 *
 * The graph is structured such that each node has a directed edge to the nodes immediately down and to the right. This structure lends itself to solving the Euler 81 problem.
 *
 * @param matrix a 2D array representation of an integer matrix.
 * @return matrixGraph a graph containing matrix values for solving Euler 81.
 */
fun buildEuler81Graph(matrix: Array<Array<Int>>): Graph<Pair<Int, Int>> {
    // build the graph now that items are spatially related
    val matrixGraph = Graph<Pair<Int, Int>>()
    for ((i, row) in matrix.withIndex()) {
        for ((j, item) in row.withIndex()) {
            matrixGraph.addVertex(Pair(i, j))
            // down edge
            if (i < matrix.size - 2) {
                matrixGraph.addEdge(Pair(i, j), Pair(i + 1, j), matrix[i + 1][j].toDouble())
            }
            // right edge
            if (j < matrix[0].size - 2) {
                matrixGraph.addEdge(Pair(i, j), Pair(i, j + 1), matrix[i][j + 1].toDouble())
            }
        }
    }
    return matrixGraph
}

/**
 * Solve Euler 81 using Dijkstra's Algorithm.
 *
 * Problem Statement: Find the minimal path sum from the top left to the bottom right by only moving right and down in the matrix.
 *
 * @param filepath a string filepath to a CSV of integers.
 * @return the lowest possible sum given the problem constraints.
 */
fun euler81(filepath: String): Double? {
    // matrix and key values
    val matrix = csvToMatrix(filepath)
    val first = Pair(0, 0)
    val last = Pair(matrix.size - 1, matrix[0].size - 1)

    // build graph with only down and right connections
    val euler81Graph = buildEuler81Graph(matrix)

    // find the shortest path using only right and down
    val path = euler81Graph.dijkstraSearch(first, last)
    path?.let {
        return it.sumOf { pair: Pair<Int, Int> -> matrix[pair.first][pair.second].toDouble() }
    }
    return null
}

fun main() {
    val result = euler81("/home/ivymahncke/Documents/F25/Data Structures/assignment-03/src/main/resources/matrix.csv")
    println(result)
}