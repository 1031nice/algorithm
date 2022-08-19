package boj.gold

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.max
import kotlin.math.min

/**
 * G1 #SegmentTree
 */

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val solution = P2357최솟값과_최댓값()

    var split = readLine().split(" ")
    val nums = Array(split[0].toInt() + 1) { 0 }
    val tree = Array(nums.size * 4) { 0 to 0 }
    val repeat = split[1].toInt()
    (1..nums.lastIndex).forEach { nums[it] = readLine().toInt() }

    val writer = BufferedWriter(OutputStreamWriter(System.out))

    solution.build(tree, nums, 1, 1, nums.lastIndex)

    repeat(repeat) {
        split = readLine().split(" ")
        val (min, max) = solution.query(tree, 1, split[0].toInt(), split[1].toInt(), 1, nums.lastIndex)
        writer.write("$min $max\n")
    }

    writer.flush()
}

class P2357최솟값과_최댓값 {
    fun build(tree: Array<Pair<Int, Int>>, nums: Array<Int>, node: Int, start: Int, end: Int): Pair<Int, Int> {
        if (start == end) {
            tree[node] = nums[start] to nums[start]
            return tree[node]
        }
        val mid = (start + end) / 2
        val (lMin, lMax) = build(tree, nums, node * 2, start, mid)
        val (rMin, rMax) = build(tree, nums, node * 2 + 1, mid + 1, end)
        tree[node] = min(lMin, rMin) to max(lMax, rMax)
        return tree[node]
    }

    fun query(tree: Array<Pair<Int, Int>>, node: Int, left: Int, right: Int, start: Int, end: Int): Pair<Int, Int> {
        if (end < left || start > right) {
            return 1_000_000_001 to 0
        } else if (left <= start && end <= right) {
            return tree[node]
        }

        val mid = (start + end) / 2
        val (lMin, lMax) = query(tree, node * 2, left, right, start, mid)
        val (rMin, rMax) = query(tree, node * 2 + 1, left, right, mid + 1, end)
        return min(lMin, rMin) to max(lMax, rMax)
    }
}