package boj.gold

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.min

/**
 * G1 #SegmentTree
 *
 * 빌드할 때는 관심 범위 밖인 경우 초깃값을 반환해야 하지만
 * 업데이트할 때는 관심 범위 밖인 경우 초깃값이 아니라 원래 저장되어 있던 값을 반환해야 한다
 *
 * 업데이트는 반환이 있을 수도 있고, 없을 수도 있는데
 * 반환이 있는 경우는 자식의 값이 수정된 후에야 부모의 값을 수정할 수 있다는 뜻이고(내려갔다 올라오면서 수정),
 * 반환이 없는 경우는 자식을 확인하지 않고 부모를 수정해도 문제 없다는 뜻으로 볼 수 있다(내려가면서 수정)
 */

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val solution = P14438수열과_쿼리17()

    val nums = Array(readLine().toInt() + 1) { 0 }
    val tree = Array(nums.size * 4) { 1_000_000_001 }
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
            writer.write("${solution.query(tree, 1, split[1].toInt(), split[2].toInt(), 1, nums.lastIndex)}\n")
        }

    }

    writer.flush()
}

class P14438수열과_쿼리17 {
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

    fun update(tree: Array<Int>, node: Int, left: Int, right: Int, start: Int, end: Int, newValue: Int): Int {
        if (end < left || start > right) {
            return tree[node]
        } else if (start == end) {
            tree[node] = newValue
            return tree[node]
        }

        val mid = (start + end) / 2
        tree[node] = min(update(tree, node * 2, left, right, start, mid, newValue),
            update(tree, node * 2 + 1, left, right, mid + 1, end, newValue))
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
        return min(query(tree, node * 2, left, right, start, mid),
            query(tree, node * 2 + 1, left, right, mid + 1, end))
    }
}