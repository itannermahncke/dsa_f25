package org.example

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    // test list sizes
    val sizes = listOf(1, 10, 100, 1000, 10000)

    // algorithms
    val sortingAlgorithms = Sorter()
    val runtimes = sortingAlgorithms.benchmarkAlgorithms(sizes)
    sortingAlgorithms.plotBenchmarks(sizes, runtimes)
}