package boj.platinum

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

/**
 * P4 #SegmentTree #LazyPropagation
 */

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val solution = P16975수열과_쿼리21()

    val nums = Array(readLine().toInt() + 1) { 0 }
    val tree = Array(nums.size * 4) { 0L }
    val lazy = Array(nums.size * 4) { 0L }
    var split = readLine().split(" ")
    split.indices.forEach { nums[it + 1] = split[it].toInt() }

    solution.build(tree, nums, 1, 1, nums.lastIndex)

    val writer = BufferedWriter(OutputStreamWriter(System.out))
    repeat(readLine().toInt()) {
        split = readLine().split(" ")
        if (split[0] == "1") { // update
            solution.update(tree, lazy, 1, split[1].toInt(), split[2].toInt(), 1, nums.lastIndex, split[3].toInt())
        } else { // query
            writer.write("${solution.query(tree, lazy, 1, split[1].toInt(), split[1].toInt(), 1, nums.lastIndex)}\n")
        }
    }

    writer.flush()
}

class P16975수열과_쿼리21 {
    fun build(tree: Array<Long>, nums: Array<Int>, node: Int, start: Int, end: Int) {
        if (start > end) {
            return
        } else if (start == end) {
            tree[node] = nums[start].toLong()
            return
        }
        val mid = (start + end) / 2
        build(tree, nums, node * 2, start, mid)
        build(tree, nums, node * 2 + 1, mid + 1, end)
    }

    fun update(tree: Array<Long>, lazy: Array<Long>, node: Int, left: Int, right: Int, start: Int, end: Int, value: Int) {
        if (end < left || start > right) {
            return
        } else if (left <= start && end <= right) {
            lazy[node] += value.toLong()
            return
        }

        val mid = (start + end) / 2
        update(tree, lazy, node * 2, left, right, start, mid, value)
        update(tree, lazy, node * 2 + 1, left, right, mid + 1, end, value)
    }

    fun query(tree: Array<Long>, lazy: Array<Long>, node: Int, left: Int, right: Int, start: Int, end: Int): Long {
        if (end < left || start > right) {
            return 0
        }

        if (lazy[node] != 0L) {
            tree[node] += lazy[node]
            if (start != end) {
                lazy[node * 2] += lazy[node]
                lazy[node * 2 + 1] += lazy[node]
            }
            lazy[node] = 0
        }

        if (left <= start && end <= right) {
            return tree[node]
        }

        val mid = (start + end) / 2
        return query(tree, lazy, node * 2, left, right, start, mid) + query(tree, lazy, node * 2 + 1, left, right, mid + 1, end)
    }
}