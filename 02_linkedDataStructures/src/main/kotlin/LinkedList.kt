package org.example

/**
 * A class representing a node in a doubly-linked list.
 *
 * @param T the type of the value stored in the node
 *
 * @property value the value stored in the node
 * @property previous the previous node in the linked list
 * @property next the next node in the linked list
 *
 * @constructor Creates a new instance of the Node class
 */
class Node<T>(val value: T, var next: Node<T>? = null, var previous: Node<T>? = null) {
    /**
     * Sets [previous] to the given element [prev]
     */
    fun linkPrev(prev: Node<T>?) {
        this.previous = prev
    }

    /**
     * Sets [next] to the given element [next]
     */
    fun linkNext(next: Node<T>?) {
        this.next = next
    }
}

/**
 * A class implementation of a doubly-linked list.
 *
 * @param T the type of the value stored in the node
 *
 * @property head the first node in the linked list
 * @property tail the last node in the linked list
 * @property length the number of nodes in the linked list
 *
 * @constructor Creates a new instance of the LinkedList class
 */
class LinkedList<T> {
    var head: Node<T>? = null
    var tail: Node<T>? = null
    var length: Int = 0

    /**
     * Adds the element [data] to the front of the linked list.
     */
    fun pushFront(data: T) {
        val new = Node(data, head)
        if (length == 0) {
            this.tail = new
        } else {
            head?.linkPrev(new)
        }
        this.head = new
        length++
    }

    /**
     * Adds the element [data] to the back of the linked list.
     */
    fun pushBack(data: T) {
        val new = Node(data, previous=tail)
        if (length == 0) {
            this.head = new
        } else {
            tail?.linkNext(new)
        }
        this.tail = new
        length++
    }

    /**
     * Removes an element from the front of the list. If the list is empty, it is unchanged.
     *
     * @return the value at the front of the list or nil if none exists
     */
    fun popFront(): T? {
        if (length > 0) {
            val value = head?.value
            this.head = head?.next
            this.head?.linkPrev(null)

            if (head == null) {
                this.tail = null
            }
            length--
            return value
        }
        return null
    }

    /**
     * Removes an element from the back of the list. If the list is empty, it is unchanged.
     *
     * @return the value at the back of the list or nil if none exists
     */
    fun popBack(): T? {
        if (length > 0) {
            val value = tail?.value
            this.tail = tail?.previous
            this.tail?.linkNext(null)
            if (tail == null) {
                this.head = null
            }
            length--
            return value
        }
        return null

    }

    /**
     * @return the value at the front of the list or nil if none exists
     */
    fun peekFront(): T? {
        return this.head?.value
    }

    /**
     * @return the value at the back of the list or nil if none exists
     */
    fun peekBack(): T? {
        return this.tail?.value
    }

    /**
     * @return true if the list is empty and false otherwise
     */
    fun isEmpty(): Boolean {
        return (length == 0)
    }
}
