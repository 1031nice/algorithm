package boj.silver

import java.util.*

/**
 * S3 #Greedy
 */

fun main() = with(Scanner(System.`in`)) {
    val nNode = nextInt()
    val edges = Array(nNode) { 0 } // edges[i] = distance from i-1 to i
    (1 until nNode).forEach { edges[it] = nextInt() }
    var minCost = -1
    var answer = 0L
    var dist = 0L
    repeat(nNode) {
        val nowCost = nextInt()
        if (minCost == -1) {
            minCost = nowCost
        }
        dist += edges[it]
        if (minCost > nowCost) {
            answer += dist * minCost
            dist = 0
            minCost = nowCost
        }
    }

    if (dist != 0L) {
        answer += dist * minCost
    }

    println(answer)
}