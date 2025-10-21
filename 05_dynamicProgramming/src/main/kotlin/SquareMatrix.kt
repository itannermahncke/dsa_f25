package org.example

import org.knowm.xchart.style.markers.Square

/**
 * Square SquareMatrix class
 */
class SquareMatrix(val n: Int) {
    // underlying data using row major ordering
    val matrixValues = DoubleArray(n*n)

    /**
     * Get the size of the matrix; i.e. number of rows (equal to number of columns)
     *
     * @return the size of the matrix.
     */
    fun size(): Int {
        return n
    }

    /**
     * Get the value at a given coordinate in this matrix
     *
     * @param row the row of the cell to fetch the value from
     * @param col the column of the cell to fetch the value from
     *
     * @return the value at this coordinate
     */
    fun getValue(row: Int, col: Int): Double {
        return matrixValues[row*n+col]
    }

    /**
     * Get the value at a given index in this matrix
     *
     * @param index the index of the desired value in the matrix as a linear array
     *
     * @return the value at this index
     */
    fun getValue(index: Int): Double {
        return matrixValues[index]
    }

    /**
     * Get a row of values from the matrix
     *
     * @param index the index of the requested row
     *
     * @return a list copy of the requested row
     */
    fun getRow(index: Int): List<Double> {
        return matrixValues.slice(index * n until index * n + n)
    }

    /**
     * Get a column of values from the matrix
     *
     * @param index the index of the requested column
     *
     * @return a list copy of the requested column
     */
    fun getCol(index: Int): List<Double> {
        val column = mutableListOf<Double>()
        for (i in 0 until n) {
            column.add(this.getValue(i, index))
        }
        return column
    }

    /**
     * Set the value at a given coordinate in this matrix
     *
     * @param row the row of the cell to contain the new value
     * @param col the column of the cell to contain the new value
     * @param value the new value to place in the cell
     */
    fun setValue(row: Int, col: Int, value: Double) {
        matrixValues[row*n+col] = value
    }

    /**
     * Get the value at a given index in this matrix
     *
     * @param index the index of the destination cell in the matrix as a linear array
     * @param value the new value to place in the cell
     */
    fun setValue(index: Int, value: Double) {
        matrixValues[index] = value
    }

    /**
     * Fill the entire matrix with given values.
     *
     * @param values a list of values with size equal to the number of elements in the matrix
     */
    fun fillMatrix(values: List<Double>) {
        for (i in 0 until n) {
            this.setValue(i, values[i])
        }
    }

    /**
     * Return a copy of the matrix quartered into 4 sub-matrices
     *
     * @return a list of sub-matrices containing values from this matrix's four quadrants
     */
    fun splitMatrix(): List<SquareMatrix>? {
        // doesn't work when n is an odd number
        if (n % 2 != 0) {
            print("Cannot quarter this matrix")
            return null
        }

        // create list to return
        val m = n/2
        val quarters = List(4) { SquareMatrix(m) }

        // iterate through each new matrix
        for (i in 0 until m) {
            for (j in 0 until m) {
                quarters[0].setValue(i, j, this.getValue(i, j))
                quarters[1].setValue(i, j, this.getValue(i, j + m))
                quarters[2].setValue(i, j, this.getValue(i + m, j))
                quarters[3].setValue(i, j, this.getValue(i + m, j + m))
            }
        }

        return quarters
    }

    /**
     * Combine four square sub-matrices into a single matrix
     */
    private fun combineMatrix(quarters: List<SquareMatrix>): SquareMatrix {
        val m = quarters[0].size()
        val n = m * 2
        val result = SquareMatrix(n)

        for (i in 0 until m) {
            for (j in 0 until m) {
                // top-left
                result.setValue(i, j, quarters[0].getValue(i, j))
                // top-right
                result.setValue(i, j + m, quarters[1].getValue(i, j))
                // bottom-left
                result.setValue(i + m, j, quarters[2].getValue(i, j))
                // bottom-right
                result.setValue(i + m, j + m, quarters[3].getValue(i, j))
            }
        }

        return result
    }

    operator fun plus(matrix: SquareMatrix): SquareMatrix {
        // create sum and return
        val sum = SquareMatrix(n)
        for (i in 0 until n) {
            sum.setValue(i, this.getValue(i) + matrix.getValue(i))
        }
        return sum
    }

    operator fun minus(matrix: SquareMatrix): SquareMatrix {
        // create sum and return
        val sum = SquareMatrix(n)
        for (i in 0 until n) {
            sum.setValue(i, this.getValue(i) - matrix.getValue(i))
        }
        return sum
    }

    operator fun times(matrix: SquareMatrix): SquareMatrix? {
        if (this.size() != matrix.size()) {
            print("Cannot multiply these matrices!")
            return null
        }

        // create product and return
        val product = SquareMatrix(n)
        // row index
        for (r in 0 until n) {
            val row = this.getRow(r)
            // column index
            for (c in 0 until n) {
                val col = matrix.getCol(c)
                // element-wise multiplication of row and col
                var cellSum = 0.0
                for (i in 0 until n) {
                    cellSum += row[i] * col[i]
                }
                product.setValue(r, c, cellSum)
            }
        }
        return product
    }

    fun strassenMultiply(matrix: SquareMatrix): SquareMatrix {
        if (this.size() != matrix.size()) {
            print("Cannot multiply these matrices!")
            return SquareMatrix(0)
        }
        // base case: single-value matrix
        if (matrix.size() == 1) {
            val newMatrix = SquareMatrix(1)
            newMatrix.setValue(0, this.getValue(0) * matrix.getValue(0))
            return newMatrix
        }

        // make quadrants
        val (A11, A12, A21, A22) = this.splitMatrix()!!
        val (B11, B12, B21, B22) = matrix.splitMatrix()!!

        // Compute intermediate matrices
        val M1 = (A11 + A22).strassenMultiply(B11 + B22)
        val M2 = (A21 + A22).strassenMultiply(B11)
        val M3 = (A11).strassenMultiply(B12 - B22)
        val M4 = (A22).strassenMultiply(B21 - B11)
        val M5 = (A11 + A12).strassenMultiply(B22)
        val M6 = (A21 - A11).strassenMultiply(B11 + B12)
        val M7 = (A12 - A22).strassenMultiply(B21 + B22)

        // Combine results
        val C11 = M1 + M4 - M5 + M7
        val C12 = M3 + M5
        val C21 = M2 + M4
        val C22 = M1 - M2 + M3 + M6
        val quarters = listOf(C11, C12, C21, C22)
        return this.combineMatrix(quarters)
    }
}