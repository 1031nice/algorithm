package boj.gold

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

/**
 * G1 #SegmentTree
 */
fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val solution = P2042구간_합_구하기()

    var split = readLine().split(" ")
    val nums = Array(split[0].toInt() + 1) { 0L }
    val tree = Array(nums.size * 4) { 0L }
    val repeat = split[1].toInt() + split[2].toInt()
    (1..nums.lastIndex).forEach { nums[it] = readLine().toLong() }

    val writer = BufferedWriter(OutputStreamWriter(System.out))

    solution.build(tree, nums, 1, 1, nums.lastIndex)

    repeat(repeat) {
        split = readLine().split(" ")
        if (split[0] == "1") { // update
            val before = nums[split[1].toInt()]
            nums[split[1].toInt()] = split[2].toLong()
            solution.update(tree, 1, nums.lastIndex, 1, split[1].toInt(), split[2].toLong() - before)
        } else { // query
            writer.write("${solution.query(tree, 1, split[1].toInt(), split[2].toInt(), 1, nums.lastIndex)}\n")
        }
    }

    writer.flush()
}

class P2042구간_합_구하기 {
    fun build(tree: Array<Long>, nums: Array<Long>, node: Int, start: Int, end: Int): Long {
        if (start == end) {
            tree[node] = nums[start]
            return tree[node]
        }
        val mid = (start + end) / 2
        tree[node] = build(tree, nums, node * 2, start, mid) + build(tree, nums, node * 2 + 1, mid + 1, end)
        return tree[node]
    }

    fun update(tree: Array<Long>, start: Int, end: Int, node: Int, index: Int, diff: Long) {
        if (index < start || end < index) {
            return
        }

        tree[node] += diff
        if (start != end) {
            val mid = (start + end) / 2
            update(tree, start, mid, node * 2, index, diff)
            update(tree, mid + 1, end, node * 2 + 1, index, diff)
        }
    }

    fun query(tree: Array<Long>, node: Int, left: Int, right: Int, start: Int, end: Int): Long {
        if (end < left || start > right) {
            return 0
        } else if (left <= start && end <= right) {
            return tree[node]
        }

        val mid = (start + end) / 2
        return query(tree, node * 2, left, right, start, mid) + query(tree, node * 2 + 1, left, right, mid + 1, end)
    }
}