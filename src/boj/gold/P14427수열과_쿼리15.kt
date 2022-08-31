package boj.gold

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

/**
 * G2 #SegmentTree
 */

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val solution = P14427수열과_쿼리15()

    val nums = Array(readLine().toInt() + 1) { 0 }
    val tree = Array(nums.size * 4) { 1_000_000_001 to -1 }
    var split = readLine().split(" ")
    repeat(split.size) { i -> nums[i + 1] = split[i].toInt() }
    solution.build(tree, nums, 1, 1, nums.lastIndex)

    val writer = BufferedWriter(OutputStreamWriter(System.out))
    repeat(readLine().toInt()) {
        split = readLine().split(" ")
        if (split[0] == "1") { // update
            val targetIndex = split[1].toInt()
            val newValue = split[2].toInt()
            solution.update(tree, 1, targetIndex, targetIndex, 1, nums.lastIndex, newValue)
        } else { // query
            writer.write("${tree[1].second}\n")
        }

    }

    writer.flush()
}

class P14427수열과_쿼리15 {
    fun build(tree: Array<Pair<Int, Int>>, nums: Array<Int>, node: Int, start: Int, end: Int): Pair<Int, Int> {
        if (start > end) {
            return 1_000_000_001 to -1
        } else if (start == end) {
            tree[node] = nums[start] to start
            return tree[node]
        }

        val mid = (start + end) / 2
        val (lNum, lIndex) = build(tree, nums, node * 2, start, mid)
        val (rNum, rIndex) = build(tree, nums, node * 2 + 1, mid + 1, end)
        tree[node] = if (lNum > rNum) {
            rNum to rIndex
        } else {
            lNum to lIndex
        }
        return tree[node]
    }

    fun update(tree: Array<Pair<Int, Int>>, node: Int, left: Int, right: Int, start: Int, end: Int, newValue: Int): Pair<Int, Int> {
        if (end < left || start > right) {
            return tree[node]
        } else if (start == end) {
            tree[node] = newValue to start
            return tree[node]
        }

        val mid = (start + end) / 2
        val (lNum, lIndex) = update(tree, node * 2, left, right, start, mid, newValue)
        val (rNum, rIndex) = update(tree, node * 2 + 1, left, right, mid + 1, end, newValue)
        tree[node] = if (lNum > rNum) {
            rNum to rIndex
        } else {
            lNum to lIndex
        }
        return tree[node]
    }
}