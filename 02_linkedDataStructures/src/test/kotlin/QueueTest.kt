import org.example.Queue
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class QueueTest {
    lateinit var emptyQueue: Queue<Int>

    @BeforeEach
    fun setUp() {
        emptyQueue = Queue<Int>()
    }

    @Test
    fun dequeueEmpty() {
        val result = emptyQueue.dequeue()
        assertNull(result, "Dequeue from empty queue was not null")
    }

    @Test
    fun enqueueDequeueOneElement() {
        val expectedVal = 1
        emptyQueue.enqueue(expectedVal)
        val result = emptyQueue.dequeue()
        assertEquals(result, expectedVal, "Queued and dequeued element are not equal")
    }

    @Test
    fun showFIFO() {
        val first = 1
        val second = 2
        emptyQueue.enqueue(first)
        emptyQueue.enqueue(second)
        val result1 = emptyQueue.dequeue()
        assertEquals(first, result1, "First queued element was not the first dequeued element")
        assertNotEquals(second, result1, "Last queued element was the first dequeued element")
    }

    @Test
    fun peekNoElement() {
        val result1 = emptyQueue.peek()
        assertNull(result1, "The result of peeking on an empty queue was a non-null value")
    }

    @Test
    fun peekOneElement() {
        val expectedVal = 1
        emptyQueue.enqueue(expectedVal)
        val result2 = emptyQueue.peek()
        assertEquals(expectedVal, result2, "Peeked element was not the element in the queue")
    }

    @Test
    fun peekFIFO() {
        val first = 1
        val second = 2
        emptyQueue.enqueue(first)
        emptyQueue.enqueue(second)
        val result = emptyQueue.peek()
        assertEquals(first, result, "First queued element was not the peeked element")
        assertNotEquals(second, result, "Last queued element was the peeked element")
    }

    @Test
    fun showIsEmpty() {
        assertTrue(emptyQueue.isEmpty(), "Empty queue reports as not empty")
        emptyQueue.enqueue(0)
        assertFalse(emptyQueue.isEmpty(), "Nonempty queue reports as empty")
        val result = emptyQueue.dequeue()
        assertTrue(emptyQueue.isEmpty(), "Empty queue reports as not empty after dequeue")
    }

}