package org.example

import kotlin.collections.ArrayDeque


/**
 * A class representing a weighted, directed graph.
 *
 * This class contains functions from Assignment 3, as well as search functions from Day 6.
 *
 */
class Graph<T> {
    // key: source vertex
    // val: edge map (key: destination vertex, val: edge weight)
    val vertexMap: MutableMap<T, MutableMap<T, Double>> = mutableMapOf()

    /**
     * @return the vertices in the graph
     */
    fun getVertices(): Set<T> {
        return vertexMap.keys
    }

    /**
     * Add a vertex [v] to the graph with no edges
     */
    fun addVertex(v: T) {
        vertexMap[v] = mutableMapOf<T, Double>()
    }

    /**
     * Add an edge between [from] and [to] with edge weight [cost]
     */
    fun addEdge(from: T, to: T, cost: Double) {
        vertexMap[from]?.also { fromEdges ->
            fromEdges[to] = cost
        } ?: run {
            vertexMap[from] = mutableMapOf(to to cost)
        }
    }

    /**
     * Get all the edges that begin at [from]
     * @return a map where each key represents a vertex connected to [from] and the value represents the edge weight.
     */
    fun getEdges(from: T): Map<T, Double> {
        return vertexMap[from] ?: emptyMap()
    }

    /**
     * Remove all edges and vertices from the graph
     */
    fun clear() {
        vertexMap.clear()
    }

    /**
     * Search through a graph using a breadth-first search, treating the edges of the graph as unweighted.
     *
     * @param start the node to start the search
     * @param target the node to search for
     * @return true if and only if path exists between [start] and [target]
     */
    fun breadthFirstSearch(start: T, target: T): Boolean {
        // base case: no edges or not in set
        if (!vertexMap.containsKey(start)) {
            return false
        }

        // set up the lists
        val toVisit: MutableSet<T> = mutableSetOf(start)
        val queue = ArrayDeque<T>()
        queue.addLast(start)

        while (queue.isNotEmpty()) {
            // FIFO structure: first node in the queue is the first to be searched
            var currentNode = queue.removeFirst()
            if (currentNode == target) {
                return true
            }

            // search every adjacent node
            vertexMap[currentNode]?.let { currentEdges ->
                for ((adjacentNode, _) in currentEdges) {
                    if (!toVisit.contains(adjacentNode)) {
                        toVisit.add(adjacentNode)
                        queue.addLast(adjacentNode)
                    }
                }
            }
        }
        // no path
        return false
    }

    /**
     * Search through a graph using a depth-first search, treating the edges of the graph as unweighted.
     *
     * @param start the node to start the search
     * @param target the node to search for
     * @return true if and only if path exists between [start] and [target]
     */
    fun depthFirstSearch(start: T, target: T): Boolean {
        // base case: no edges or not in set
        if (!vertexMap.containsKey(start)) {
            return false
        }

        // set up the lists
        val toVisit: MutableSet<T> = mutableSetOf(start)
        val stack = ArrayDeque<T>()
        stack.addLast(start)

        while (stack.isNotEmpty()) {
            // LIFO structure: latest node in the stack is the first to be searched
            var currentNode = stack.removeLast()
            if (currentNode == target) {
                return true
            }

            // search every adjacent node
            vertexMap[currentNode]?.let { currentEdges ->
                for ((adjacentNode, _) in currentEdges) {
                    if (!toVisit.contains(adjacentNode)) {
                        toVisit.add(adjacentNode)
                        stack.addLast(adjacentNode)
                    }
                }
            }
        }
        // no path
        return false
    }


    /**
     * Run Dijkstra’s algorithm to search through a graph for the shortest path.
     *
     * @param source The node at which to start the path
     * @param dest The node at which to end the path
     * @return the shortest path (not just the cost) between [source] and [destination]. If no path exists between the two nodes, return null.
     */
    fun dijkstraSearch(source: T, target: T): List<T>? {
        // base case: no edges or not in set
        if (!vertexMap.containsKey(source)) {
            return null
        }

        // set up the lists
        // tracking distances for shortest path to latest node
        val distanceMap = mutableMapOf<T, Double>()
        for (v in vertexMap.keys) {distanceMap[v] = Double.POSITIVE_INFINITY}
        distanceMap[source] = 0.0

        // map the shortest path as vertices: value is the predecessor of key
        val retraceMap = mutableMapOf<T, T?>()
        retraceMap[source] = null

        // set of seen nodes
        val visited: MutableSet<T> = mutableSetOf()

        // priority queue to figure out which vertex to visit next based on costs
        val priorityQueue = MinPriorityQueue<T>()
        priorityQueue.addWithPriority(source, 0.0)

        while (!priorityQueue.isEmpty()) {
            // always check the seen node with the lowest available cost
            var currentNode = priorityQueue.next()

            // skip if seen
            if (visited.contains(currentNode)) continue
            visited.add(currentNode!!)

            // when the target is found, retrace the shortest path
            if (currentNode == target) {
                val shortestPath = mutableListOf<T>()
                var node = currentNode
                while (node != null) {
                    shortestPath.add(node)
                    node = retraceMap[node]
                }
                return shortestPath.reversed().toList()
            }

            // search every adjacent node
            vertexMap[currentNode]?.let { currentEdges ->
                for ((node, cost) in currentEdges) {
                    val newCost = distanceMap[currentNode]!! + cost
                    if (newCost < distanceMap[node]!!) {
                        distanceMap[node] = newCost
                        retraceMap[node] = currentNode
                        priorityQueue.addWithPriority(node, newCost)
                    }
                }
            }
        }
        // no path
        return null
    }
}