package boj.silver

import java.util.*

/**
 * S1 #Tree #Recursion
 */

class Node {
    var name = ""
    var left: Node? = null
    var right: Node? = null
}

fun main() = with(Scanner(System.`in`)) {
    val nNode = nextInt()
    val nodes = hashMapOf<String, Node>()
    (0 until nNode).forEach {
        val newNode = Node()
        newNode.name = ('A' + it).toString()
        nodes[newNode.name] = newNode
    }

    repeat(nNode) {
        val node = nodes[next()]
        val leftName = next()
        val rightName = next()
        if (leftName != ".") {
            node!!.left = nodes[leftName]
        }
        if (rightName != ".") {
            node!!.right = nodes[rightName]
        }
    }

    val startNode = nodes["A"]
    printPreOrder(startNode)
    println()
    printInOrder(startNode)
    println()
    printPostOrder(startNode)
}

fun printPreOrder(startNode: Node?) {
    if (startNode == null) {
        return
    }

    print(startNode.name)
    printPreOrder(startNode.left)
    printPreOrder(startNode.right)
}

fun printInOrder(startNode: Node?) {
    if (startNode == null) {
        return
    }

    printInOrder(startNode.left)
    print(startNode.name)
    printInOrder(startNode.right)
}

fun printPostOrder(startNode: Node?) {
    if (startNode == null) {
        return
    }

    printPostOrder(startNode.left)
    printPostOrder(startNode.right)
    print(startNode.name)
}
