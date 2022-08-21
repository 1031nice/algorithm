package boj.platinum

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.max
import kotlin.math.min

/**
 * P4 #SegmentTree #LazyPropagation
 *
 *
 */

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val solution = P10999구간_합_구하기2()

    var split = readLine().split(" ")
    val nums = Array(split[0].toInt() + 1) { 0L }
    val tree = Array(nums.size * 4) { 0L to 0 }
    val lazy = Array(nums.size * 4) { 0L }
    val repeat = split[1].toInt() + split[2].toInt()

    (1..nums.lastIndex).forEach { nums[it] = readLine().toLong() }

    solution.build(tree, nums, 1, 1, nums.lastIndex)

    val writer = BufferedWriter(OutputStreamWriter(System.out))
    repeat(repeat) {
        split = readLine().split(" ")
        if (split[0] == "1") { // update
            solution.update(tree, lazy, 1, split[1].toInt(), split[2].toInt(), 1, nums.lastIndex, split[3].toLong())
        } else { // query
            writer.write("${solution.query(tree, lazy, 1, split[1].toInt(), split[2].toInt(), 1, nums.lastIndex).first}\n")
        }
    }

    writer.flush()
}

class P10999구간_합_구하기2 {
    fun build(tree: Array<Pair<Long, Int>>, nums: Array<Long>, node: Int, start: Int, end: Int): Pair<Long, Int> {
        if (start > end) {
            return 0L to 0
        } else if (start == end) {
            tree[node] = nums[start] to 1
            return tree[node]
        }
        val mid = (start + end) / 2
        val (lSum, lSize) = build(tree, nums, node * 2, start, mid)
        val (rSum, rSize) = build(tree, nums, node * 2 + 1, mid + 1, end)
        tree[node] = lSum + rSum to lSize + rSize
        return tree[node]
    }

    fun update(tree: Array<Pair<Long, Int>>, lazy: Array<Long>, node: Int, left: Int, right: Int, start: Int, end: Int, value: Long) {
        if (end < left || start > right) {
            return
        } else if (left <= start && end <= right) {
            lazy[node] += value
            return
        }

        val updateSize = min(end, right) - max(start, left) + 1
        val (sum, size) = tree[node]
        tree[node] = sum + updateSize * value to size
        val mid = (start + end) / 2
        update(tree, lazy, node * 2, left, right, start, mid, value)
        update(tree, lazy, node * 2 + 1, left, right, mid + 1, end, value)
    }

    fun query(tree: Array<Pair<Long, Int>>, lazy: Array<Long>, node: Int, left: Int, right: Int, start: Int, end: Int): Pair<Long, Int> {
        if (end < left || start > right) {
            return 0L to 0
        }

        // 노드가 포함하는 구간이 모두 업데이트 되어야 할 때만(구간이 모두 포함될 때만) lazy에 넣어뒀으므로
        // update size = end - start + 1
        if (lazy[node] != 0L) {
            val (sum, size) = tree[node]
            tree[node] = sum + (end - start + 1) * lazy[node] to size
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
        val (lSum, lSize) = query(tree, lazy, node * 2, left, right, start, mid)
        val (rSum, rSize) = query(tree, lazy, node * 2 + 1, left, right, mid + 1, end)
        return lSum + rSum to lSize + rSize
    }
}