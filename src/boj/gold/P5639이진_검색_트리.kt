package boj.gold

import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * G5 #BinarySearchTree
 */

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val solution = P5639이진_검색_트리()

    val preorder = arrayListOf<Int>()
    while (true) {
        val line = readLine()
        if (line.isNullOrBlank()) {
            break
        }
        preorder.add(line.toInt())
    }

    solution.printPostorder(preorder, 0, preorder.lastIndex)
}

class P5639이진_검색_트리 {
    fun printPostorder(preorder: ArrayList<Int>, start: Int, end: Int) {
        if (start > end) {
            return
        } else if (start == end) {
            println(preorder[start])
            return
        }

        var indexOfRightSubtreeRoot = end + 1
        for (i in start..end) {
            if (preorder[i] > preorder[start]) {
                indexOfRightSubtreeRoot = i
                break
            }
        }

        printPostorder(preorder, start + 1, indexOfRightSubtreeRoot - 1)
        printPostorder(preorder, indexOfRightSubtreeRoot, end)
        println(preorder[start])
    }
}