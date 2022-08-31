package boj.gold

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.max
import kotlin.math.min

/**
 * G1 #SegmentTree
 *
 * query는 0 i j 꼴로 입력되는데 i < j라는 보장이 없다
 * */

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val solution = P2268수들의_합7()

    var split = readLine().split(" ")
    val nums = Array(split[0].toInt() + 1) { 0 }
    val tree = Array(nums.size * 4) { 0L }
    val repeat = split[1].toInt()

    val writer = BufferedWriter(OutputStreamWriter(System.out))
    repeat(repeat) {
        split = readLine().split(" ")
        if (split[0] == "0") { // query
            val n1 = split[1].toInt()
            val n2 = split[2].toInt()
            writer.write("${solution.query(tree, 1, min(n1, n2), max(n1, n2), 1, nums.lastIndex)}\n")
        } else { // update
            val targetIndex = split[1].toInt()
            val newValue = split[2].toInt()
            solution.update(tree, 1, targetIndex, targetIndex, 1, nums.lastIndex, newValue - nums[targetIndex])
            nums[targetIndex] = newValue
        }

    }

    writer.flush()
}

class P2268수들의_합7 {
    fun update(tree: Array<Long>, node: Int, left: Int, right: Int, start: Int, end: Int, diff: Int) {
        if (end < left || start > right) {
            return
        }
        tree[node] += diff.toLong()
        if (start == end) {
            return
        }

        val mid = (start + end) / 2
        update(tree, node * 2, left, right, start, mid, diff)
        update(tree, node * 2 + 1, left, right, mid + 1, end, diff)
    }

    fun query(tree: Array<Long>, node: Int, left: Int, right: Int, start: Int, end: Int): Long {
        if (end < left || start > right) {
            return 0
        }
        if (left <= start && end <= right) {
            return tree[node]
        }

        val mid = (start + end) / 2
        return query(tree, node * 2, left, right, start, mid) + query(tree, node * 2 + 1, left, right, mid + 1, end)
    }
}