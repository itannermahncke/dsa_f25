# Data Structures & Algorithms F25 Assignments

## Assignment 01: Hello Kotlin

### [Assignment Link](https://olindsa2025.github.io/assignments/assignment_01.html)

### Overview

This is the first assignment I completed for DSA 2025. My goal was to practice Kotlin syntax and style by revamping a script I'd written in another language for Kotlin specifically. In this assignment, I also answered some questions for the teaching team on my learning style and my goals for the class.

### Directory Structure
- `README.md` contains a description of the assignment and a reflection on my first time working with Kotlin
- `src` contains my first Kotlin project, a demonstration of unit testing on a correct and incorrect implementation of a mean function
- `WelcomeToDSA.pdf` contains my responses to a few survey questions about learning goals and pedagogy

## Assignment 02: Linked Data Structures

### [Assignment Link](https://olindsa2025.github.io/assignments/assignment_02.html)

### Overview

In this assignment, I implemented the doubly-linked list data structure and used it to implement two abstract data types: the stack and the queue. I then used my stack and queue to solve the following LeetCode-style coding challenges:
- Reverse a stack (out-of-place and in-place)
- Solve the [valid parentheses problem](https://leetcode.com/problems/valid-parentheses/description/)
- Copy a stack using 1 auxiliary queue only

### Directory Structure
- `src` contains the following files, as well as unit testing files for each:
  - `LinkedList.kt` is my implementation of a doubly-linked list
  - `Stack.kt` is my implementation of a stack using a doubly-linked list
  - `Queue.kt` is my implementation of a queue using a doubly-linked list
  - `Main.kt` contains solutions to several coding challenges using stacks and queues

## Assignment 03: Graphs and Shortest Path Algorithms

### [Assignment Link](https://olindsa2025.github.io/assignments/assignment_03.html)

### Overview

In this assignment, I implemented the directed, weighted graph data structure. I also implemented the MinHeap data structure as the underlying structure to a priority queue. Next, I wrote functions to perform breadth-first search, depth-first search, and Dijkstra's algorithm on my graph. Finally, I used Dijkstra's algorithm to solve a shortest-path problem.

### Directory Structure
- `src` contains the following files:
    - `Graph.kt` is my implementation of a directed, weighted graph, as well as three classic search algorithms
    - `MinHeap.kt` is my implementation of a min heap data structure
    - `MinPriorityQueue.kt` is a wrapper around the min heap I used to implement a priority queue
    - `Main.kt` contains solutions to the [Euler 81 path sum puzzle](https://projecteuler.net/problem=81)

## Assignment 04: Sorting Algorithms and Time Complexity

### [Assignment Link](https://olindsa2025.github.io/assignments/assignment_04.html)

### Overview

In this assignment, I implemented and benchmarked four standard sorting algorithms to prove the time complexity of each one. I also practiced using the Master Theorem to analyze the runtime of divide-and-conquer algorithms.

### Directory Structure
- `benchmarking.md` contains a discussion of the results of my runtime benchmarking process
- `src` contains the following files:
    - `Sorter.kt` houses my implementation of insertion sort, selection sort, merge sort, and quicksort, as well as code to benchmark the runtime of each algorithm
    - `Main.kt` simply contains code to run the benchmarking experiment
- `MasterTheoremWorksheet.pdf` contains my notes on the Master Theorem, as well as solutions to [MIT's Intro to Algorithms Master Theorem Worksheet](https://courses.csail.mit.edu/6.046/spring02/handouts/master.pdf)

## Assignment 05: Divide-And-Conquer Algorithms and Dynamic Programming