package org.example

import kotlin.random.Random
import kotlin.time.DurationUnit
import kotlin.time.measureTime
import org.knowm.xchart.SwingWrapper
import org.knowm.xchart.XYChartBuilder
import org.knowm.xchart.XYSeries

fun plotRuntimes(sizes: List<Int>, runtimes: Map<String, List<Double>>) {
    val chart = XYChartBuilder()
        .width(800)
        .height(600)
        .title("Matrix Multiplication Benchmark")
        .xAxisTitle("Matrix Size (n x n)")
        .yAxisTitle("Runtime (seconds)")
        .build()
    chart.styler.isXAxisLogarithmic = true
    for ((label, data) in runtimes) {
        chart.addSeries(label, sizes, data)
    }

    SwingWrapper(chart).displayChart()
}

fun benchmarkMatrixMultiplication(testMatrices: List<Pair<SquareMatrix, SquareMatrix>>): Map<String, List<Double>> {
    val runtimes = mutableMapOf("traditional multiplication" to mutableListOf<Double>(), "strassen multiplication" to mutableListOf<Double>())
    for (matrixPair in testMatrices) {
        // traditional
        val tradRuntime = measureTime { matrixPair.first * matrixPair.second }
        runtimes["traditional multiplication"]!!.add(tradRuntime.toDouble(DurationUnit.SECONDS))

        // strassen
        val strassenRuntime = measureTime { matrixPair.first.strassenMultiply(matrixPair.second) }
        runtimes["strassen multiplication"]!!.add(strassenRuntime.toDouble(DurationUnit.SECONDS))
    }

    return runtimes
}

fun createTestDataSet(sizes: List<Int>): List<Pair<SquareMatrix, SquareMatrix>> {
    val testData = mutableListOf<Pair<SquareMatrix, SquareMatrix>>()
    for (size in sizes) {
        // left matrix
        val matrix1 = SquareMatrix(size)
        matrix1.fillMatrix((1 until size*size).map { Random.nextDouble(10.0) })
        // right matrix
        val matrix2 = SquareMatrix(size)
        matrix2.fillMatrix((1 until size*size).map { Random.nextDouble(10.0) })
        testData.add(Pair(matrix1, matrix2))
    }
    return testData
}

fun runMatrixMultiplicationBenchmarking() {
    val sizesSmall = listOf(4, 8, 16, 32, 64, 128)
    var testData = createTestDataSet(sizesSmall)
    var runtimes = benchmarkMatrixMultiplication(testData)
    plotRuntimes(sizesSmall, runtimes)

    val sizesBig = listOf(256, 512, 1024, 2048, 4096, 8192)
    testData = createTestDataSet(sizesSmall)
    runtimes = benchmarkMatrixMultiplication(testData)
    plotRuntimes(sizesBig, runtimes)
}

fun main() {
}