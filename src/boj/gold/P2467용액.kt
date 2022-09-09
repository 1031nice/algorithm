package boj.gold

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.abs

/**
 * G5 #TwoPointer
 */

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    readLine()
    val split = readLine().split(" ")
    val nums = Array(split.size) { 0 }
    (split.indices).forEach { nums[it] = split[it].toInt() }

    var toRight = 0
    var toLeft = nums.lastIndex
    var closestSum = 2_000_000_000L
    var closestPair = 0 to 0
    while (toRight < toLeft) {
        val sum = nums[toLeft].toLong() + nums[toRight].toLong()
        if (abs(closestSum) > abs(sum)) {
            closestSum = sum
            closestPair = toLeft to toRight
        }
        if (sum > 0) {
            toLeft--
        } else {
            toRight++
        }
    }

    print("${nums[closestPair.second]} ${nums[closestPair.first]}")
}