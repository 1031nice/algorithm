package boj.gold

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.min

/**
 * G1 #SegmentTree
 */

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val solution = P10868최솟값()

    var split = readLine().split(" ")
    val nums = Array(split[0].toInt() + 1) { 0 }
    val tree = Array(nums.size * 4) { 1_000_000_001 }
    val repeat = split[1].toInt()
    (1..nums.lastIndex).forEach { nums[it] = readLine().toInt() }

    solution.build(tree, nums, 1, 1, nums.lastIndex)

    val writer = BufferedWriter(OutputStreamWriter(System.out))
    repeat(repeat) {
        split = readLine().split(" ")
        writer.write("${solution.query(tree, 1, split[0].toInt(), split[1].toInt(), 1, nums.lastIndex)}\n")
    }

    writer.flush()
}

class P10868최솟값 {
    fun build(tree: Array<Int>, nums: Array<Int>, node: Int, start: Int, end: Int): Int {
        if (start > end) {
            return 1_000_000_001
        } else if (start == end) {
            tree[node] = nums[start]
            return tree[node]
        }

        val mid = (start + end) / 2
        tree[node] = min(build(tree, nums, node * 2, start, mid), build(tree, nums, node * 2 + 1, mid + 1, end))
        return tree[node]
    }

    fun query(tree: Array<Int>, node: Int, left: Int, right: Int, start: Int, end: Int): Int {
        if (end < left || start > right) {
            return 1_000_000_001
        }
        if (left <= start && end <= right) {
            return tree[node]
        }

        val mid = (start + end) / 2
        return min(query(tree, node * 2, left, right, start, mid), query(tree, node * 2 + 1, left, right, mid + 1, end))
    }
}