package boj.gold

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.min

/**
 * G4 #TwoPointer
 *
 * 투포인터임을 알 수 있었던 단서들: 연속된 // 자연수 // 부분합
 */

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var split = readLine().split(" ")
    val size = split[0].toInt()
    val target = split[1].toInt()
    val nums = Array(size) { 0 }
    split = readLine().split(" ")
    repeat(size) { nums[it] = split[it].toInt() }

    var start = 0
    var end = 1
    var sum = nums[start]
    var minLength = 100_001
    while (end > start && end < size) {
        if (sum >= target) {
            minLength = min(minLength, end - start)
            sum -= nums[start]
            start++
        } else {
            sum += nums[end++]
        }
    }

    while (sum >= target && end > start) {
        minLength = min(minLength, end - start)
        sum -= nums[start++]
    }

    println(if (minLength == 100_001) 0 else minLength)
}