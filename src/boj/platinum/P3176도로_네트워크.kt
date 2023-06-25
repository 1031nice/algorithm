package boj.platinum

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow

class P3176도로_네트워크 {
    fun lcs(
        depth: Array<Int>, parent: Array<Array<Int>>, minAncestor: Array<Array<Int>>, maxAncestor: Array<Array<Int>>,
        n1: Int, n2: Int, max: Int,
    ): Pair<Int, Int> {
        var varN1 = n1
        var varN2 = n2

        var maxEdge = 0
        var minEdge = 1_000_001

        if (depth[varN1] < depth[varN2]) {
            val temp = varN1
            varN1 = varN2
            varN2 = temp
        }

        for (k in max downTo 0) {
            if (2.0.pow(k) <= depth[varN1] - depth[varN2]) {
                maxEdge = max(maxEdge, maxAncestor[k][varN1])
                minEdge = min(minEdge, minAncestor[k][varN1])
                varN1 = parent[k][varN1]
            }
        }

        for (k in max downTo 0) {
            if (parent[k][varN1] != parent[k][varN2]) {
                maxEdge = max(maxEdge, maxAncestor[k][varN1])
                minEdge = min(minEdge, minAncestor[k][varN1])

                maxEdge = max(maxEdge, maxAncestor[k][varN2])
                minEdge = min(minEdge, minAncestor[k][varN2])

                varN1 = parent[k][varN1]
                varN2 = parent[k][varN2]
            }
        }

        if (varN1 != varN2) {
            maxEdge = max(maxEdge, maxAncestor[0][varN1])
            minEdge = min(minEdge, minAncestor[0][varN1])

            maxEdge = max(maxEdge, maxAncestor[0][varN2])
            minEdge = min(minEdge, minAncestor[0][varN2])
        }
        return minEdge to maxEdge
    }
}

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val solution = P3176도로_네트워크()

    val nodeSize = readLine().toInt()

    var temp = 1
    var max = 0
    while (temp <= nodeSize) {
        temp *= 2
        max++
    }

    val adjList = Array(nodeSize + 1) { mutableListOf<Pair<Int, Int>>() }
    val visited = Array(nodeSize + 1) { false }
    val parent = Array(max + 1) { Array(nodeSize + 1) { 0 } }
    val maxAncestor = Array(max + 1) { Array(nodeSize + 1) { 0 } }
    val minAncestor = Array(max + 1) { Array(nodeSize + 1) { 1_000_001 } }
    val depth = Array(nodeSize + 1) { 0 }

    repeat(nodeSize - 1) {
        val split = readLine().split(" ")
        val n1 = split[0].toInt()
        val n2 = split[1].toInt()
        val w = split[2].toInt()
        adjList[n1].add(n2 to w)
        adjList[n2].add(n1 to w)
    }

    val q = LinkedList<Int>()
    q.add(1)
    depth[1] = 0
    visited[1] = true

    while (q.isNotEmpty()) {
        val now = q.poll()
        for ((next, w) in adjList[now]) {
            if (visited[next]) {
                continue
            }

            visited[next] = true
            parent[0][next] = now
            maxAncestor[0][next] = w
            minAncestor[0][next] = w
            depth[next] = depth[now] + 1
            q.add(next)
        }
    }

    for (k in 1..max) {
        for (n in 1..nodeSize) {
            parent[k][n] = parent[k - 1][parent[k - 1][n]]
            maxAncestor[k][n] = max(maxAncestor[k - 1][n], maxAncestor[k - 1][parent[k - 1][n]])
            minAncestor[k][n] = min(minAncestor[k - 1][n], minAncestor[k - 1][parent[k - 1][n]])
        }
    }

    val querySize = readLine().toInt()
    repeat(querySize) {
        var split = readLine().split(" ")
        var n1 = split[0].toInt()
        var n2 = split[1].toInt()

        val (minEdge, maxEdge) = solution.lcs(depth, parent, minAncestor, maxAncestor, n1, n2, max)
        writer.write("$minEdge $maxEdge\n")
    }

    writer.flush()
}
