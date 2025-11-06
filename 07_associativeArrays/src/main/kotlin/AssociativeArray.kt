package org.example

import kotlin.math.absoluteValue

// node class; hash function points to a linked list rather than a single value
data class Node<K, V>(var key: K, var value: V, var next: Node<K, V>? = null)

/**
 * Represents a mapping of keys to values.
 * @param K the type of the keys
 * @param V the type of the values
 */
class AssociativeArray<K, V> {
    // prime values for hashing, chosen by capacity
    private val primes = listOf(53, 97, 193, 389, 769, 1543, 3079)
    private var primeIdx = 0

    // table info
    private var capacity = 16
    private var table = Array<Node<K, V>?>(capacity) { null }
    private var numElements = 0

    /**
     * Insert the mapping from the key, [k], to the value, [v].
     * If the key already maps to a value, replace the mapping.
     */
    operator fun set(k: K, v: V) {
        // get hash index
        val hashIndex = hash(k)

        // if it's the first item, place in as new node
        if (table[hashIndex] == null) {
            // add item and potentially rehash
            table[hashIndex] = Node(k, v)
            numElements++
            if (numElements >= capacity) {
                rehash()
            }
            return
        }

        // find the existing key or get to the last node
        var currentNode = table[hashIndex]!!
        while (currentNode.next != null) {
            if (currentNode.key == k) {
                currentNode.value = v
                return
            }
            currentNode = currentNode.next!!
        }

        // check the last node
        if (currentNode.key == k) {
            currentNode.value = v
            return
        }

        // append a new node
        currentNode.next = Node(k, v)
        numElements++
        if (numElements >= capacity) {
            rehash()
        }
    }

    /**
     * @return true if [k] is a key in the associative array
     */
    operator fun contains(k: K): Boolean {
        // reuse get search functionality
        return get(k) != null
    }

    /**
     * @return the value associated with the key [k] or null if it doesn't exist
     */
    operator fun get(k: K): V?{
        // get hash index
        val hashIndex = hash(k)

        // search for the key in the hash table
        var latestNode = table[hashIndex]
        while (latestNode != null) {
            if (latestNode.key == k) {
                return latestNode.value
            }
            latestNode = latestNode.next
        }
        return null
    }

    /**
     * Remove the key, [k], from the associative array
     * @param k the key to remove
     * @return true if the item was successfully removed and false if the element was not found
     */
    fun remove(k: K): Boolean{
        // get hash index
        val hashIndex = hash(k)

        // base case: missing or first item
        if (table[hashIndex] == null) {
            return false
        }
        if (table[hashIndex]!!.key == k) {
            table[hashIndex] = table[hashIndex]!!.next
            numElements--
            return true
        }

        // search for the key in the hash table
        var priorNode = table[hashIndex]!!
        var latestNode = priorNode.next
        while (latestNode != null) {
            // remove from the chain if found
            if (latestNode.key == k) {
                priorNode.next = latestNode.next
                numElements--
                return true
            }
            priorNode = latestNode
            latestNode = latestNode.next
        }
        return false
    }

    /**
     * @return the number of elements stored in the hash table
     */
    fun size(): Int {
        return numElements
    }

    /**
     * @return the full list of key value pairs for the associative array
     */
    fun keyValuePairs(): List<Pair<K, V>>{
        val kvs = mutableListOf<Pair<K, V>>()
        for (ll in table) {
            var node = ll
            while (node != null) {
                kvs.add(Pair(node.key, node.value))
                node = node.next
            }
        }
        return kvs
    }

    /**
     * Encode the key [k] using a hashing function
     * @param k the key to hash
     * @return the index of [k] in the hash table
     */
    private fun hash(k: K): Int {
        val prime = primes[primeIdx]
        val rawHash = when (k) {
            is Int -> (k * prime).absoluteValue
            is Long -> (k.toInt() * prime).absoluteValue
            else -> (k.hashCode().absoluteValue * prime)
        }
        return rawHash % capacity
    }


    /**
     * Rehash the table to the next available prime number.
     */
    private fun rehash() {
        // first, double capacity and iterate prime
        capacity *= 2
        if (primeIdx < primes.size) {
            primeIdx += 1
        }

        // reset table
        val tableContents = keyValuePairs()
        numElements = 0
        table = Array (capacity) { null }

        // rebuild table using new prime
        for (kvp in tableContents) {
            set(kvp.first, kvp.second)
        }
    }

}
