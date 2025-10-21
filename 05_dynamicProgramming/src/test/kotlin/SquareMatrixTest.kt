import org.example.SquareMatrix
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SquareMatrixTest {

    private fun makeMatrix(values: List<Double>, n: Int): SquareMatrix {
        val m = SquareMatrix(n)
        for (i in values.indices) {
            m.setValue(i, values[i])
        }
        return m
    }

    @Test
    fun testSingleElementMatrixMultiplication() {
        val a = makeMatrix(listOf(5.0), 1)
        val b = makeMatrix(listOf(3.0), 1)
        val result = a * b
        assertEquals(15.0, result!!.getValue(0, 0), 1e-6)
    }

    @Test
    fun test2x2MatrixMultiplication() {
        val a = makeMatrix(listOf(
            1.0, 2.0,
            3.0, 4.0
        ), 2)
        val b = makeMatrix(listOf(
            2.0, 0.0,
            1.0, 2.0
        ), 2)

        val expected = listOf(
            1*2 + 2*1, 1*0 + 2*2,
            3*2 + 4*1, 3*0 + 4*2
        )

        val result = a * b
        for (i in expected.indices) {
            assertEquals(expected[i].toDouble(), result!!.getValue(i), 1e-6)
        }
    }

    @Test
    fun testStrassenMatchesRegularMultiplication_2x2() {
        val a = makeMatrix(listOf(
            1.0, 3.0,
            7.0, 5.0
        ), 2)
        val b = makeMatrix(listOf(
            6.0, 8.0,
            4.0, 2.0
        ), 2)

        val regular = a * b
        val strassen = a.strassenMultiply(b)

        for (i in 0 until 4) {
            assertEquals(regular!!.getValue(i), strassen.getValue(i), 1e-6, "Mismatch at index $i")
        }
    }

    @Test
    fun testStrassenMatchesRegularMultiplication_4x4() {
        val a = makeMatrix(listOf(
            1.0, 2.0, 3.0, 4.0,
            5.0, 6.0, 7.0, 8.0,
            9.0, 10.0, 11.0, 12.0,
            13.0, 14.0, 15.0, 16.0
        ), 4)

        val b = makeMatrix(listOf(
            16.0, 15.0, 14.0, 13.0,
            12.0, 11.0, 10.0, 9.0,
            8.0, 7.0, 6.0, 5.0,
            4.0, 3.0, 2.0, 1.0
        ), 4)

        val regular = a * b
        val strassen = a.strassenMultiply(b)

        for (i in 0 until a.n * a.n) {
            assertEquals(regular!!.getValue(i), strassen.getValue(i), 1e-6, "Mismatch at index $i")
        }
    }

    @Test
    fun testIncompatibleSizesReturnNull() {
        val a = SquareMatrix(2)
        val b = SquareMatrix(3)
        val result = a * b
        val strassen = a.strassenMultiply(b)
        assertNull(result, "Multiplying incompatible matrices should return null")
        assertEquals(strassen.size(), 0, "Multiplying incompatible matrices with Strassen should return empty matrix")
    }
}
