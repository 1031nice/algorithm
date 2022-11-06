package boj.gold

import java.util.*
import kotlin.math.max

/**
 * G5 #DP #String
 */
fun main() = with(Scanner(System.`in`)) {
    val s1 = next()
    val s2 = next()

    val matrix = Array(s1.length + 1) { Array(s2.length + 1) { 0 } }

    (1..s1.length).forEach { i ->
        (1..s2.length).forEach { j ->
            if (s1[i - 1] == s2[j - 1]) {
                matrix[i][j] = matrix[i - 1][j - 1] + 1
            } else {
                matrix[i][j] = max(matrix[i - 1][j], matrix[i][j - 1])
            }
        }
    }

    print(matrix[s1.length][s2.length])
}