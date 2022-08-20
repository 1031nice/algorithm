package boj.gold

import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * G2 #BinaryTree
 */

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val solution = P2263트리의_순회()

    val nNode = readLine().toInt()
    val inorder = Array(nNode) { 0 }
    var split = readLine().split(" ")
    repeat(nNode) {
        inorder[it] = split[it].toInt()
    }
    val postorder = Array(nNode) { 0 }
    split = readLine().split(" ")
    repeat(nNode) {
        postorder[it] = split[it].toInt()
    }

    solution.printPreorder(inorder, postorder, 0, inorder.lastIndex, 0, postorder.lastIndex)
}

class P2263트리의_순회 {
    fun printPreorder(inorder: Array<Int>, postorder: Array<Int>, inorderStart: Int, inorderEnd: Int, postorderStart: Int, postorderEnd: Int) {
        if (inorderStart > inorderEnd) {
            return
        }
        val root = postorder[postorderEnd]
        val indexOfRoot = inorder.indexOf(root)
        val length = indexOfRoot - inorderStart

        print("${inorder[indexOfRoot]} ")
        printPreorder(inorder, postorder, inorderStart, indexOfRoot - 1, postorderStart, postorderStart + length - 1)
        printPreorder(inorder, postorder, indexOfRoot + 1, inorderEnd, postorderStart + length, postorderEnd - 1)
    }
}