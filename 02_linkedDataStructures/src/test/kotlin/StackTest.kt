import org.example.Stack
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class StackTest {
    lateinit var emptyStack: Stack<Int>

    @BeforeEach
    fun setUp() {
        emptyStack = Stack<Int>()
    }

    @Test
    fun popEmpty() {
        val result = emptyStack.pop()
        assertNull(result, "Pop from empty stack was not null")
    }


    @Test
    fun pushPopOneElement() {
        val expectedVal = 1
        emptyStack.push(expectedVal)
        val result = emptyStack.pop()
        assertEquals(result, expectedVal, "Pushed and popped element are not equal")
    }

    @Test
    fun showLIFO() {
        val first = 1
        val second = 2
        emptyStack.push(first)
        emptyStack.push(second)
        val result = emptyStack.pop()
        assertEquals(second, result, "Last pushed element was not the first popped element")
        assertNotEquals(first, result, "First pushed element was the first popped element")
    }

    @Test
    fun peekNoElement() {
        val result1 = emptyStack.peek()
        assertNull(result1, "The result of peeking on an empty stack was a non-null value")
    }

    @Test
    fun peekOneElement() {
        val expectedVal = 1
        emptyStack.push(expectedVal)
        val result2 = emptyStack.peek()
        assertEquals(expectedVal, result2, "Peeked element was not the element in the stack")
    }

    @Test
    fun peekLIFO() {
        val first = 1
        val second = 2
        emptyStack.push(first)
        emptyStack.push(second)
        val result = emptyStack.peek()
        assertEquals(second, result, "Last pushed element was not the peeked element")
        assertNotEquals(first, result, "First pushed element was the peeked element")
    }

    @Test
    fun showIsEmpty() {
        assertTrue(emptyStack.isEmpty(), "Empty stack reports as not empty")
        emptyStack.push(0)
        assertFalse(emptyStack.isEmpty(), "Nonempty stack reports as empty")
        val result = emptyStack.pop()
        assertTrue(emptyStack.isEmpty(), "Empty stack reports as not empty after pop")
    }

}