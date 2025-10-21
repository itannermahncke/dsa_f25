package org.example

import org.knowm.xchart.*
import org.knowm.xchart.style.markers.SeriesMarkers

import kotlin.time.measureTime
import kotlin.time.DurationUnit
import kotlin.random.Random
import kotlin.time.Duration

/**
 * The Sorter class possesses several implementations of classic sorting algorithms and capabilities to benchmark them.
 */
class Sorter {

    fun plotBenchmarks(sizes: List<Int>, runtimes: Map<String, MutableList<Double>>) {
        val chart = XYChartBuilder()
            .width(800)
            .height(600)
            .title("Sorting Algorithm Average Runtimes")
            .xAxisTitle("Input Size (n)")
            .yAxisTitle("Time (seconds)")
            .build()

        chart.styler.isLegendVisible = true
        chart.styler.isToolTipsEnabled = true
        chart.styler.markerSize = 6
        chart.styler.isXAxisLogarithmic = true

        for ((algo, times) in runtimes) {
            if (algo == "sizes") continue
            chart.addSeries(algo, sizes, times).setMarker(SeriesMarkers.CIRCLE)
        }

        SwingWrapper(chart).displayChart()
    }
    /**
     * Benchmark all four algorithms possessed by this class by noting their runtimes when sorting lists of given sizes.
     *
     * @param sizes a list of all list sizes to test with each algorithm
     * @return a map where keys are algorithm names and values are lists of runtimes with index corresponding to those in [sizes]
     */
    fun benchmarkAlgorithms(sizes: List<Int>): MutableMap<String, MutableList<Double>> {
        // build the algorithm map
        val algorithms = mapOf("insertion" to ::runInsertionSort, "selection" to ::runSelectionSort, "merge" to ::runMergeSort, "quick" to ::runQuickSort)

        // build the runtime map
        val runtimes: MutableMap<String, MutableList<Double>> = mutableMapOf()
        for (algo in listOf("insertion", "selection", "merge", "quick")) {
            runtimes[algo] = mutableListOf()
        }

        // for each test list size
        var runtime: Duration
        for (size in sizes) {
            // test list of defined size
            val testList = (1 until size).map { Random.nextInt(100000) }.toMutableList()

            // test and document the average runtime of each sorting algorithm
            for ((name, fn) in algorithms) {
//                val runtimesToAverage: MutableList<Double> = mutableListOf()
//                for (i in 1..10) {
//                    val testCopy = testList
//                    runtime = measureTime { fn(testCopy) }
//                    runtimesToAverage.add(runtime.toDouble(DurationUnit.SECONDS))
//                }
//                runtimes[name]?.add(runtimesToAverage.average())
                val testCopy = testList
                runtime = measureTime { fn(testCopy) }
                runtimes[name]?.add(runtime.toDouble(DurationUnit.SECONDS))
            }
        }
        return runtimes
    }

    // the following public functions standardize the inputs and outputs of the sorts
    fun runInsertionSort(list: MutableList<Int>) {
        insertionSort(list)
    }
    fun runSelectionSort(list: MutableList<Int>) {
        selectionSort(list)
    }
    fun runMergeSort(list: MutableList<Int>) {
        mergeSort(list)
    }
    fun runQuickSort(list: MutableList<Int>) {
        quickSort(list, 0, list.lastIndex)
    }

    /**
     * Insertion Sort
     *
     * Sort a list in place by iterating through aall elements and swap-sorting it backwards.
     *
     * Time complexity: O(n^2)
     * This is because in the worst case scenario, all n elements will be compared against all n
     * other elements, resulting in a time complexity of n*n = n^2.
     *
     * @param list the list to sort in place
     */
    private fun insertionSort(list: MutableList<Int>) {
        // for each element in the list
        for (i in 1..list.lastIndex) {
            // starting with this element, swap-sort backwards until in place
            var j = i
            while (j > 0 && list[j - 1] > list[j]) {
                val temp = list[j - 1]
                list[j - 1] = list[j]
                list[j] = temp
                j -= 1
            }
        }
    }

    /**
     * Selection Sort
     *
     * Sort a list in place by repeatedly searching for the smallest element in the list and placing
     * it before all unsorted elements.
     *
     * Time complexity: O(n^2)
     * This is because in the worst-case scenario, the entire list of n elements is searched for
     * each n element, resulting in a time complexity of n*n = n^2.
     *
     * @param list the list to sort in place
     */
    private fun selectionSort(list: MutableList<Int>) {
        // outer element
        for (i in list.indices) {
            var indexOfMin = i
            // inner element, looking for minimum
            for (j in i+1..list.lastIndex) {
                if (list[j] < list[indexOfMin]) {
                    indexOfMin = j
                }
            }
            // minimum found, make swap
            val temp = list[i]
            list[i] = list[indexOfMin]
            list[indexOfMin] = temp
        }
    }

    /**
     * Merge Sort
     *
     * Sort a list by recursively splitting it in half until each element is alone in a sublist.
     * Then, re-merge the sublists into a sorted list.
     *
     * Time complexity: O(n * log n)
     * This is because the number of times you can recursively cut n elements in half until you
     * have single elements is log(n). Separately, it takes n actions to re-merge those elements.
     * This results in a time complexity of O(n * log(n)).
     *
     * @param list the list to sort
     *
     * @return the sorted array.
     */
    private fun mergeSort(list: MutableList<Int>): List<Int> {
        // base case: a list with 0 or 1 elements is already sorted
        if (list.size <= 1) {
            return list
        }

        // recursive case: a list with more than 1 element must be split in half
        val mid = list.size / 2
        val left = mergeSort(list.subList(0, mid))
        val right = mergeSort(list.subList(mid, list.size))

        // begin merging the sublists back together
        return mergeHelper(left, right)
    }

    /**
     * Helper function for mergeSort to re-merge elements in the list.
     *
     * @param left half of the list to re-merge
     * @param right half of the list to re-merge
     *
     * @return the sorted list in totality
     */
    private fun mergeHelper(left: List<Int>, right: List<Int>): List<Int> {
        val result = mutableListOf<Int>()

        // while neither are empty, pop top elements and add the smaller of the two
        var l = 0
        var r = 0

        while (l < left.size && r < right.size) {
            if (left[l] <= right[r]) {
                result.add(left[l])
                l++
            } else {
                result.add(right[r])
                r++
            }
        }

        // now, one of the two arrays are empty. enter the rest of the nonempty array, which is guaranteed to be sorted
        while (l < left.size) {
            result.add(left[l])
            l++
        }
        while (r < right.size) {
            result.add(right[r])
            r++
        }
        return result
    }

    /**
     * Quick Sort
     *
     * Given a list and limits of the list, swap elements to the left and right of a pivot point
     * until the elements overlap. Repeat the process for each side of the pivot until all
     * elements are sorted.
     *
     * @param list the list to sort
     * @param l index of the leftmost element
     * @param r index of the rightmost element
     */
    private fun quickSort(list: MutableList<Int>, l: Int, r: Int) {
        if ((l < r) && (l >= 0) && (r >= 0)) {
            val pivot = quickHelper(list, l, r)
            quickSort(list, l, pivot)
            quickSort(list, pivot + 1, r)
        }
    }

    /**
     * Helper function for quickSort. given a sublist and limits for the sublist, swap elements
     * to the left and right of a pivot point until the elements overlap. Then, return the point
     * of overlap as the next pivot.
     *
     * Time complexity: O(n * log(n))
     * This is because the divide-and-conquer aspect of the algorithm is log(n), like merge sort.
     * During the partition step, each element in the n element array is checked, which takes n
     * steps. Combining these results in a time complexity of O(n * log(n))
     *
     * @param list the list to sort
     * @param l index of the leftmost element of the sublist
     * @param r index of the rightmost element of the sublist
     *
     * @return the overlap point, to be used as the next pivot point
     */
    private fun quickHelper(list: MutableList<Int>, l: Int, r: Int): Int {
        // first element of sublist
        val pivot = list[(l + r) / 2]

        var i = l - 1
        var j = r + 1

        while (true) {
            // look for a left-side element greater than the pivot
            do {
                i += 1
            } while (list[i] < pivot)
            // look for a right-side element less than the pivot
            do {
                j -= 1
            } while (list[j] > pivot)

            // if the pointers cross, kill the loop
            if (i >= j) {
                return j
            }

            // if the pointers don't cross, swap them
            val temp = list[i]
            list[i] = list[j]
            list[j] = temp
        }
    }

}