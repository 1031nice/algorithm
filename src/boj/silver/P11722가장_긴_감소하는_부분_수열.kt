package boj.silver

import java.util.*
import kotlin.math.max

/**
 * S2 #DP
 */

fun main() = with(Scanner(System.`in`)) {
    val nums = Array(nextInt()) { 0 }
    repeat(nums.size) {
        nums[it] = next().toInt()
    }

    val cache = Array(nums.size) { 1 }

    var ret = 1
    for (i in nums.indices) {
        for (j in 0 until i) {
            if (nums[j] > nums[i]) {
                cache[i] = max(cache[i], cache[j] + 1)
                ret = max(ret, cache[i])
            }
        }
    }

    print(ret)
}