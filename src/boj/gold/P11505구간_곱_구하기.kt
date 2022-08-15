package boj.gold

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

/**
 * G1 #SegmentTree
 *
 * newValue가 0일 때는 tree를 rebuild하고,
 * newValue가 0이 아닐 때는 트리를 타고 내려가면서
 * 해당 구간곱을 oldValue로 나눈 뒤 newValue를 곱하려고 접근
 *
 * 하지만 tree에는 1_000_000_007로 나눈 나머지가 저장이 되므로
 * tree[node] / oldValue * newValue의 결과가 oldValue를 newValue로 바꾼 구간곱이라고 보장할 수 없음
 *
 * 그냥 update마다 해당 값이 포함된 구간값을 다시 계산하며 된다
 */

const val MOD = 1_000_000_007

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var split = readLine().split(" ")
    val nums = Array(split[0].toInt() + 1) { 0L }
    val tree = Array(nums.size * 4) { 1L }
    val repeat = split[1].toInt() + split[2].toInt()
    (1..nums.lastIndex).forEach { nums[it] = readLine().toLong() }

    val writer = BufferedWriter(OutputStreamWriter(System.out))

    build(tree, nums, 1, 1, nums.lastIndex)

    repeat(repeat) {
        split = readLine().split(" ")
        if (split[0] == "1") { // update
            update(tree, 1, nums.lastIndex, 1, split[1].toInt(), split[2].toInt())
        } else { // query
            writer.write("${query(tree, 1, split[1].toInt(), split[2].toInt(), 1, nums.lastIndex)}\n")
        }
    }

    writer.flush()
}

fun build(tree: Array<Long>, nums: Array<Long>, node: Int, start: Int, end: Int): Long {
    if (start == end) {
        tree[node] = nums[start]
        return tree[node]
    }
    val mid = (start + end) / 2;
    tree[node] = build(tree, nums, node * 2, start, mid) * build(tree, nums, node * 2 + 1, mid + 1, end) % MOD;
    return tree[node]
}

fun update(tree: Array<Long>, start: Int, end: Int, node: Int, index: Int, newValue: Int): Long {
    return if (index < start || end < index) {
        tree[node]
    } else if (start == end) {
        tree[node] = newValue.toLong()
        tree[node]
    } else {
        val mid = (start + end) / 2;
        tree[node] = update(tree, start, mid, node * 2, index, newValue) * update(tree, mid + 1, end, node * 2 + 1, index, newValue) % MOD;
        tree[node]
    }
}

// tree에 1_000_000_007로 나눈 값이 저장되므로 (tree[node] / oldValue * newValue)가 oldValue를 newValue로 바꿨을 때의 구간곱이라는 보장이 없다
/* fun update2(tree: Array<Long>, start: Int, end: Int, node: Int, index: Int, oldValue: Long, newValue: Long) {
    if (index < start || end < index) {
        return
    }

    if (start == end) {
        tree[node] = newValue;
    } else {
        tree[node] = (tree[node] / oldValue * newValue) % MOD;
        val mid = (start + end) / 2;
        update2(tree, start, mid, node * 2, index, oldValue, newValue);
        update2(tree, mid + 1, end, node * 2 + 1, index, oldValue, newValue);
    }
 }*/

fun query(tree: Array<Long>, node: Int, left: Int, right: Int, start: Int, end: Int): Long {
    if (end < left || start > right) {
        return 1
    } else if (left <= start && end <= right) {
        return tree[node]
    }

    val mid = (start + end) / 2
    return query(tree, node * 2, left, right, start, mid) * query(tree, node * 2 + 1, left, right, mid + 1, end) % MOD
}