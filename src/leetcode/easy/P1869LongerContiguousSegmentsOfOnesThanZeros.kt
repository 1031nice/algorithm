package leetcode.easy

import kotlin.math.max

/**
 * 정수 배열이 주어질 때 연속된 1의 최대 길이가 연속된 0의 최대 길이보다 길다면 true
 */

fun checkZeroOnes(s: String): Boolean {
    var ones = 0
    var zeros = 0
    var maxOnes = 0
    var maxZeros = 0

    for (i in 0..s.lastIndex) {
        if (s[i] == '1') {
            maxZeros = max(maxZeros, zeros)
            zeros = 0
            ones++
        } else {
            maxOnes = max(maxOnes, ones)
            ones = 0
            zeros++
        }
    }

    return max(maxOnes, ones) > max(maxZeros, zeros)
}