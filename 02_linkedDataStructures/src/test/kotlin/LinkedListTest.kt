import org.example.LinkedList
import org.example.Node
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class LinkedListTest {
    lateinit var emptyLinkedList: LinkedList<Int>
    lateinit var populatedLinkedList: LinkedList<Int>

    @BeforeEach
    fun setUp() {
        emptyLinkedList = LinkedList<Int>()
        populatedLinkedList = LinkedList<Int>()
        populatedLinkedList.head = Node(0)
        populatedLinkedList.pushBack(1)
        populatedLinkedList.pushBack(2)
    }

    @Test
    fun pushFrontEmpty() {
        val expectedValue = 1
        emptyLinkedList.pushFront(expectedValue)
        assertEquals(emptyLinkedList.head?.value, emptyLinkedList.tail?.value, "Head and tail are not the same value")
        assertEquals(expectedValue, emptyLinkedList.head?.value, "Pushed value was not saved in the head")
    }

    @Test
    fun pushBackEmpty() {
        val expectedValue = 1
        emptyLinkedList.pushBack(expectedValue)
        assertEquals(emptyLinkedList.head?.value, emptyLinkedList.tail?.value, "Head and tail are not the same value")
        assertEquals(emptyLinkedList.tail?.value, expectedValue, "Pushed value was not saved in the tail")
    }

    @Test
    fun pushFrontPopulated() {
        val pushValue = -1
        val oldHead = populatedLinkedList.head
        populatedLinkedList.pushFront(pushValue)
        assertEquals(pushValue, populatedLinkedList.head?.value, "Pushed value was not saved in the head")
        assertEquals(oldHead, populatedLinkedList.head?.next, "New head does not point to the previous head")
        assertEquals(populatedLinkedList.head?.next?.previous, populatedLinkedList.head, "Old head does not point to the new head")
    }

    @Test
    fun pushBackPopulated() {
        val pushValue = 3
        val oldTail = populatedLinkedList.tail
        populatedLinkedList.pushBack(pushValue)
        assertEquals(pushValue, populatedLinkedList.tail?.value, "Pushed value was not saved in the tail")
        assertEquals(oldTail, populatedLinkedList.tail?.previous, "New tail does not point to the previous tail")
        assertEquals(populatedLinkedList.tail?.previous?.next, populatedLinkedList.tail, "Old tail does not point to the new tail")
    }

    @Test
    fun popFrontEmpty() {
        val result = emptyLinkedList.popFront()
        assertNull(result, "The result of popping from an empty list was $result, not null")
        assertEquals(0, emptyLinkedList.length, "Popping resulted in a nonzero list length")
    }

    @Test
    fun popFrontSingle() {
        val toPush = -1
        emptyLinkedList.pushFront(toPush)
        val result = emptyLinkedList.popFront()
        assertEquals(toPush, result, "The result of popping from an populated list was $result, not $toPush")
        assertEquals(0, emptyLinkedList.length, "Popping from a list of 1 resulted in a nonzero list length")
        assertNull(emptyLinkedList.tail, "Popping from a list of 1 did not result in a null tail")
        assertNull(emptyLinkedList.head, "Popping from a list of 1 did not result in a null head")
    }

    @Test
    fun popFrontMulti() {
        val expectedVal = populatedLinkedList.head?.value
        val expectedLen = populatedLinkedList.length - 1
        val newHead = populatedLinkedList.head?.next
        val result = populatedLinkedList.popFront()
        assertEquals(expectedVal, result, "The popped value was $result when $expectedVal was expected")
        assertEquals(newHead, populatedLinkedList.head, "The new head is not the expected value")
        assertNull(populatedLinkedList.head?.previous, "The new head has a non-null previous value")
        assertEquals(expectedLen, populatedLinkedList.length, "The new length is an unexpected value")
    }

    @Test
    fun popBackEmpty() {
        val result = emptyLinkedList.popBack()
        assertNull(result, "The result of popping from an empty list was $result, not null")
        assertEquals(0, emptyLinkedList.length, "Popping resulted in a nonzero list length")
    }

    @Test
    fun popBackSingle() {
        val toPush = 1
        emptyLinkedList.pushBack(toPush)
        val result = emptyLinkedList.popBack()
        assertEquals(toPush, result, "The result of popping from an populated list was $result, not $toPush")
        assertEquals(0, emptyLinkedList.length, "Popping from a list of 1 resulted in a nonzero list length")
        assertNull(emptyLinkedList.tail, "Popping from a list of 1 did not result in a null tail")
        assertNull(emptyLinkedList.head, "Popping from a list of 1 did not result in a null head")
    }

    @Test
    fun popBackMulti() {
        val expectedVal = populatedLinkedList.tail?.value
        val expectedLen = populatedLinkedList.length - 1
        val newTail = populatedLinkedList.tail?.previous
        val result = populatedLinkedList.popBack()
        assertEquals(expectedVal, result, "The popped value was $result when $expectedVal was expected")
        assertEquals(newTail, populatedLinkedList.tail, "The new tail is not the expected value")
        assertNull(populatedLinkedList.tail?.next, "The new tail has a non-null next value")
        assertEquals(expectedLen, populatedLinkedList.length, "The new length is an unexpected value")
    }

    @Test
    fun peekFrontEmpty() {
        val result = emptyLinkedList.peekFront()
        assertNull(result, "The result of front-peeking from an empty list was a non-null value")
    }

    @Test
    fun peekFrontPopulated() {
        val result = populatedLinkedList.peekFront()
        val expectedLen = populatedLinkedList.length
        assertEquals(populatedLinkedList.head?.value, result, "The result of front-peeking from a list was $result, not head value ${populatedLinkedList.head?.value}")
        assertEquals(expectedLen, populatedLinkedList.length, "Front-peeking from a list modified its length")
    }

    @Test
    fun peekBackEmpty() {
        val result = emptyLinkedList.peekBack()
        assertNull(result, "The result of back-peeking from an empty list was a non-null value")
    }

    @Test
    fun peekBackPopulated() {
        val result = populatedLinkedList.peekBack()
        val expectedLen = populatedLinkedList.length
        assertEquals(populatedLinkedList.tail?.value, result, "The result of back-peeking from a list was $result, not tail value ${populatedLinkedList.tail?.value}")
        assertEquals(expectedLen, populatedLinkedList.length, "Back-peeking from a list modified its length")
    }

    @Test
    fun isEmptyListEmpty() {
        val result = emptyLinkedList.isEmpty()
        assertTrue(result, "Empty list was reported as not empty")
    }

    @Test
    fun isPopulatedListNonEmpty() {
        val result = populatedLinkedList.isEmpty()
        assertFalse(result, "Non-empty list was reported as empty")
    }

}