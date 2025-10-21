package org.example

/**
 * A class implementation of a queue.
 *
 * @param T the type of the value stored in the queue
 *
 * @constructor Creates a new instance of the queue class
 */
class Queue<T> {
    val list = LinkedList<T>()
    /**
     * Add [data] to the end of the queue.
     */
    fun enqueue(data: T) {
        list.pushBack(data)
    }

    /**
     * Remove the element at the front of the queue.  If the queue is empty, it remains unchanged.
     * @return the value at the front of the queue or nil if none exists
     */
    fun dequeue(): T? {
        return list.popFront()
    }

    /**
     * @return the value at the front of the queue or nil if none exists
     */
    fun peek(): T? {
        return list.popFront()
    }

    /**
     * @return true if the queue is empty and false otherwise
     */
    fun isEmpty(): Boolean {
        return list.isEmpty()
    }
}
