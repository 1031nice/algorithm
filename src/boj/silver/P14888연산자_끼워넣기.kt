package boj.silver

import java.util.*
import kotlin.math.max
import kotlin.math.min

/**
 * S1 #BruteForce
 */

var max = -1234567890
var min = 1234567890

fun main() = with(Scanner(System.`in`)) {

    val nums = Array(nextInt()) { 0 }
    repeat(nums.size) { nums[it] = nextInt() }
    val ops = Array(4) { 0 }
    repeat(4) { ops[it] = nextInt() }

    func(nums, ops, 1, nums[0])
    println("$max\n$min")
}

fun func(nums: Array<Int>, ops: Array<Int>, now: Int, result: Int) {
    if (now == nums.size) {
        min = min(min, result)
        max = max(max, result)
        return
    }

    for (i in ops.indices) {
        if (ops[i] > 0) {
            ops[i]--
            func(nums, ops, now + 1, calc(result, nums[now], i))
            ops[i]++
        }
    }
}

fun calc(num1: Int, num2: Int, opIndex: Int): Int {
    return when (opIndex) {
        0 -> num1 + num2
        1 -> num1 - num2
        2 -> num1 * num2
        else -> num1 / num2
    }
}