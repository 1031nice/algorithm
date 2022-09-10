package boj.gold

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

/**
 * G1 #SegmentTree
 */

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val solution = P14428수열과_쿼리16()

    val nums = Array(readLine().toInt() + 1) { 0 }
    val tree = Array(nums.size * 4) { 1_000_000_001 to 1_000_000_001 }
    var split = readLine().split(" ")
    repeat(split.size) { i -> nums[i + 1] = split[i].toInt() }
    repeat(nums.lastIndex) { solution.update(tree, 1, it + 1, it + 1, 1, nums.lastIndex, nums[it + 1]) }
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    repeat(readLine().toInt()) {
        split = readLine().split(" ")
        if (split[0] == "1") { // update
            val targetIndex = split[1].toInt()
            val newValue = split[2].toInt()
            solution.update(tree, 1, targetIndex, targetIndex, 1, nums.lastIndex, newValue)
            nums[targetIndex] = newValue
        } else { // query
            writer.write("${solution.query(tree, 1, split[1].toInt(), split[2].toInt(), 1, nums.lastIndex).second}\n")
        }
    }

    writer.flush()
}

class P14428수열과_쿼리16 {
    fun update(tree: Array<Pair<Int, Int>>, node: Int, left: Int, right: Int, start: Int, end: Int, newValue: Int): Pair<Int, Int> {
        if (end < left || start > right) {
            return tree[node]
        } else if (start == end) {
            tree[node] = newValue to start
            return tree[node]
        }

        val mid = (start + end) / 2
        val (lMin, lIndex) = update(tree, node * 2, left, right, start, mid, newValue)
        val (rMin, rIndex) = update(tree, node * 2 + 1, left, right, mid + 1, end, newValue)
        tree[node] = getMin(lMin, rMin, lIndex, rIndex)
        return tree[node]
    }

    fun query(tree: Array<Pair<Int, Int>>, node: Int, left: Int, right: Int, start: Int, end: Int): Pair<Int, Int> {
        if (end < left || start > right) {
            return 1_000_000_001 to 1_000_000_001
        } else if (left <= start && end <= right) {
            return tree[node]
        }

        val mid = (start + end) / 2
        val (lMin, lIndex) = query(tree, node * 2, left, right, start, mid)
        val (rMin, rIndex) = query(tree, node * 2 + 1, left, right, mid + 1, end)
        return getMin(lMin, rMin, lIndex, rIndex)
    }

    private fun getMin(lMin: Int, rMin: Int, lIndex: Int, rIndex: Int) = if (lMin == rMin) {
        if (lIndex < rIndex) lMin to lIndex else rMin to rIndex
    } else if (lMin < rMin) {
        lMin to lIndex
    } else {
        rMin to rIndex
    }
}