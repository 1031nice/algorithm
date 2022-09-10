package boj.gold

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

/**
 * G1 #SegmentTree
 */

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val solution = P12837가계부()

    var split = readLine().split(" ")
    val nums = Array(split[0].toInt() + 1) { 0 }
    val tree = Array(nums.size * 4) { 0L }

    val writer = BufferedWriter(OutputStreamWriter(System.out))

    repeat(split[1].toInt()) {
        split = readLine().split(" ")
        if (split[0] == "1") { // update
            solution.update(tree, 1, nums.lastIndex, 1, split[1].toInt(), split[2].toInt())
        } else { // query
            writer.write("${solution.query(tree, 1, split[1].toInt(), split[2].toInt(), 1, nums.lastIndex)}\n")
        }
    }

    writer.flush()
}

class P12837가계부 {
    fun update(tree: Array<Long>, start: Int, end: Int, node: Int, index: Int, num: Int) {
        if (index < start || end < index) {
            return
        }

        tree[node] += num.toLong()
        if (start != end) {
            val mid = (start + end) / 2
            update(tree, start, mid, node * 2, index, num)
            update(tree, mid + 1, end, node * 2 + 1, index, num)
        }
    }

    fun query(tree: Array<Long>, node: Int, left: Int, right: Int, start: Int, end: Int): Long {
        if (end < left || start > right) {
            return 0
        } else if (left <= start && end <= right) {
            return tree[node]
        }

        val mid = (start + end) / 2
        return query(tree, node * 2, left, right, start, mid) +
                query(tree, node * 2 + 1, left, right, mid + 1, end)
    }
}