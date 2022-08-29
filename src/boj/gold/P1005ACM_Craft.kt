package boj.gold

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.max

/**
 * G3 #TopologicalSort #DP
 *
 * 건물 A를 짓는 데 준비하는 시간(A를 짓기 위해 먼저 지어져야 하는 건물들이 모두 지어지는 데 걸리는 시간) readyTime
 * 건물 A만 짓는 데 걸리는 시간 buildTime
 * 건물 A를 짓는 데 필요한 총 시간 totalTime
 * 건물 A를 짓기 위해 먼저 지어져야 하는 건물을 A-1, A-2, ... 라고 하면
 *
 * totalTime = readyTime + buildTime
 *           = maxOf(totalTime(A-1), totalTime(A-2), ...) + buildTime
 * F(A)      = maxOf(F(A-1), F(A-2), ...) + buildTime
 *
 * 건물 A를 짓기 위해 먼저 지어져야 하는 건물들 = 건물 A의 ingoing edge로 이어진 건물들
 */

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    repeat(readLine().toInt()) {
        var split = readLine().split(" ")
        val nNode = split[0].toInt()
        val nEdge = split[1].toInt()
        val matrix = Array(nNode) { Array(nNode) { 0 } }
        val cache = Array(nNode) { -1 }
        val times = Array(nNode) { 0 }
        split = readLine().split(" ")
        repeat(nNode) { times[it] = split[it].toInt() }
        repeat(nEdge) {
            split = readLine().split(" ")
            val target = split[0].toInt() - 1
            val source = split[1].toInt() - 1
            matrix[source][target] = 1
        }

        println(dfs(matrix, cache, times, readLine().toInt() - 1))
    }
}

fun dfs(matrix: Array<Array<Int>>, cache: Array<Int>, times: Array<Int>, start: Int): Int {
    if (cache[start] != -1) {
        return cache[start]
    }

    var max = 0
    for (i in 0 until matrix[start].size) {
        if (matrix[start][i] > 0) {
            max = max(max, dfs(matrix, cache, times, i))
        }
    }
    cache[start] = times[start] + max
    return cache[start]
}