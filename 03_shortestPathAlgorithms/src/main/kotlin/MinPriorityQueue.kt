package org.example

/**
 * ``MinPriorityQueue`` maintains a priority queue where the lower
 *  the priority value, the sooner the element will be removed from
 *  the queue.
 *  @param T the representation of the items in the queue
 */
class MinPriorityQueue<T> {
    val internalHeap = MinHeap<T>()

    /**
     * @return true if the queue is empty, false otherwise
     */
    fun isEmpty(): Boolean {
        return internalHeap.isEmpty()
    }

    /**
     * Add [elem] with at level [priority]
     */
    fun addWithPriority(elem: T, priority: Double) {
        val result = internalHeap.insert(elem, priority)
        if (!result) {
            adjustPriority(elem, priority)
        }
    }

    /**
     * Get the next (lowest priority) element.
     * @return the next element in terms of priority.  If empty, return null.
     */
    fun next(): T? {
        return internalHeap.getMin()
    }

    /**
     * Adjust the priority of the given element
     * @param elem whose priority should change
     * @param newPriority the priority to use for the element
     *   the lower the priority the earlier the element int
     *   the order.
     */
    fun adjustPriority(elem: T, newPriority: Double) {
        internalHeap.adjustHeapNumber(elem, newPriority)
    }
}
