package boj.gold

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

/**
 * G4 #DP
 *
 * DP 문제를 풀 때 모르겠다는 생각이 들었던 때를 돌아보면
 * 문제를 재귀적인 관점에서 바라보지 못하고 전체 문제를 해결할 수 있는 풀이를 찾으려고 했던 것 같다
 * 어려운 문제는 어려운 문제 그대로 남겨두고 그 문제의 크기만 줄이다가(recursive),
 * 줄이고 줄여서 더이상 문제가 어렵게 느껴지지 않을 때(base case) 문제를 푸이는 게 재귀이고
 * 이렇게 접근하면 해결해야 하는 문제 난이도를 확실히 낮출 수 있다
 * 그리고 그 과정에서 만약 중복이 발생한다면
 * 중복을 제거함으로써 속도를 비약적으로 높일 수 있다
 */

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val length = readLine().toInt()
    val nums = Array(length) { 0 }
    readLine().split(" ").withIndex().forEach { (index, numString) ->
        nums[index] = numString.toInt()
    }

    val query = readLine().toInt()
    val cache = Array(length) { Array(length) { 0 } }
    repeat(query) {
        val split = readLine().split(" ")
        val start = split[0].toInt() - 1
        val end = split[1].toInt() - 1
        writer.write("${if (isPalindrome(nums, cache, start, end)) 1 else 0}\n")
    }

    writer.flush()
}

fun isPalindrome(nums: Array<Int>, cache: Array<Array<Int>>, start: Int, end: Int): Boolean {
    if (start == end) {
        return true
    } else if (start > end) {
        return false
    } else if (end - start == 1) {
        return nums[start] == nums[end]
    }

    if (nums[start] == nums[end]) {
        return if (cache[start + 1][end - 1] == 1) {
            cache[start][end] = 1
            true
        } else if (cache[start + 1][end - 1] == -1) {
            cache[start][end] = -1
            false
        } else {
            if (isPalindrome(nums, cache, start + 1, end - 1)) {
                cache[start][end] = 1
            } else {
                cache[start][end] = -1
            }
            cache[start][end] == 1
        }
    }

    cache[start][end] = -1
    return false
}