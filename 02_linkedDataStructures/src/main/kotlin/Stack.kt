package org.example

/**
 * A class implementation of a stack.
 *
 * @param T the type of the value stored in the stack
 *
 * @constructor Creates a new instance of the stack class
 */
class Stack<T> {
    val list = LinkedList<T>()

    /**
     * Add [data] to the top of the stack
     */
    fun push(data: T) {
        list.pushFront(data)
    }

    /**
     * Remove the element at the top of the stack.  If the stack is empty, it remains unchanged.
     * @return the value at the top of the stack or nil if none exists
     */
    fun pop(): T? {
        return list.popFront()
    }

    /**
     * @return the value on the top of the stack or nil if none exists
     */
    fun peek(): T? {
        return list.peekFront()
    }

    /**
     * @return true if the stack is empty and false otherwise
     */
    fun isEmpty(): Boolean {
        return list.isEmpty()
    }
}