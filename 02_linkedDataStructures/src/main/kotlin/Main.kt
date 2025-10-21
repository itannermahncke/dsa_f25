package org.example

/**
 * Exercise 1
 * Reverse a given stack out-of-place using a secondary stack.
 *
 * @param stack the given stack to reverse
 * @return A new stack with all original elements of [stack] in reverse order
 */
fun <T> reverseStackOutOfPlace(stack: Stack<T>): Stack<T> {
    val tempStack = Stack<T>()
    while (!stack.isEmpty()) {
        when (val element = stack.pop()) {
            null -> println("Warning: null element found in non-empty stack")
            else -> tempStack.push(element)
        }
    }
    return tempStack
}

/**
 * Exercise 1
 * Reverse a given stack in-place by reversing the underlying linked list.
 *
 * @param stack the given stack to reverse
 */
fun <T> reverseStackInPlace(stack: Stack<T>) {
    var current: Node<T>? = stack.list.tail
    var temp: Node<T>? = null

    while (current != null) {
        temp = current.next
        current.next = current.previous
        current.previous = temp
        current = current.next
    }

    // Swap head and tail
    val oldHead = stack.list.head
    stack.list.head = stack.list.tail
    stack.list.tail = oldHead
}

/**
 * Exercise 2
 *
 * Solve the valid parentheses problem using a stack.
 *
 * @param str a string of bracket symbols that could be valid or not
 * @return true if [str] is a valid set of parentheses; false otherwise
 */
fun isValidParentheses(str: String): Boolean {
    val symbolStack: Stack<Char> = Stack<Char>()
    val symbolMap: Map<Char, Char> = mapOf('{' to '}', '(' to ')', '[' to ']')

    // base case: odd number of characters is invalid
    if (str.length % 2 != 0) {
        return false
    }

    // otherwise: count each parenthetical
    for (element in str) {
        // add any opening brackets to the top of the stack
        if (element in symbolMap.keys) {
            symbolStack.push(element)
        // compare any closing brackets to the top of the stack
        } else {
            // no opening brackets left in the stack to match with
            if (symbolStack.isEmpty()) {
                return false
            }
            // draw the top opening bracket
            val lastOpenSymbol = symbolStack.pop()
            // the top opening bracket did not match the next closing bracket
            if (symbolMap[lastOpenSymbol] != element) {
                return false
            }
        }
    }
    // failsafe if the stack has any remaining opening brackets
    return symbolStack.isEmpty()
}

/**
 * Exercise 3
 *
 * Solve the copy stack problem.
 *
 * @param originalStack the original stack
 */
fun <T> copyStack(originalStack: Stack<T>): Stack<T> {
    val newStack = Stack<T>()
    val auxQueue = Queue<T>()

    // Step 1: pop every element from the original stack into the queue
    // Element order: reversed
    while (!originalStack.isEmpty()) {
        auxQueue.enqueue(originalStack.pop()!!)
    }

    // Step 2: dequeue every element from the queue back into the original stack
    // Element order: reversed
    while (!auxQueue.isEmpty()) {
        originalStack.push(auxQueue.dequeue()!!)
    }

    // pop every element from the original stack back into the queue
    // Element order: original
    while (!originalStack.isEmpty()) {
        auxQueue.enqueue(originalStack.pop()!!)
    }

    // Step 4: dequeue every element from the queue back into both stacks
    // Element order: original
    while (!auxQueue.isEmpty()) {
        originalStack.push(auxQueue.dequeue()!!)
        newStack.push(auxQueue.dequeue()!!)
    }

    return newStack
}