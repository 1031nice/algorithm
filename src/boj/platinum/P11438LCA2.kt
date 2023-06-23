package boj.platinum

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.pow

class P11438LCA2 {
    fun lcs(depth: Array<Int>, parent: Array<Array<Int>>, n1: Int, n2: Int, max: Int): Int {
        var varN1 = n1
        var varN2 = n2
        if (depth[varN1] < depth[varN2]) {
            val temp = varN1
            varN1 = varN2
            varN2 = temp
        }

        for (k in max downTo 0) {
            if (2.0.pow(k) <= depth[varN1] - depth[varN2]) {
                varN1 = parent[k][varN1]
            }
        }

        for (k in max downTo 0) {
            if (parent[k][varN1] != parent[k][varN2]) {
                varN1 = parent[k][varN1]
                varN2 = parent[k][varN2]
            }
        }

        var LCA = varN1
        if (varN1 != varN2) {
            LCA = parent[0][LCA]
        }
        return LCA
    }
}

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val solution = P11438LCA2()

    val nodeSize = readLine().toInt()

    var temp = 1
    var max = 0
    while (temp <= nodeSize) {
        temp *= 2
        max++
    }

    val adjList = Array(nodeSize + 1) { mutableListOf<Int>() }
    val visited = Array(nodeSize + 1) { false }
    val parent = Array(max + 1) { Array(nodeSize + 1) { 0 } }
    val depth = Array(nodeSize + 1) { 0 }

    repeat(nodeSize - 1) {
        val split = readLine().split(" ")
        val n1 = split[0].toInt()
        val n2 = split[1].toInt()
        adjList[n1].add(n2)
        adjList[n2].add(n1)
    }


    val q = LinkedList<Int>()
    q.add(1)
    depth[1] = 0
    visited[1] = true

    while (q.isNotEmpty()) {
        val now = q.poll()
        for (next in adjList[now]) {
            if (visited[next]) {
                continue
            }

            visited[next] = true
            parent[0][next] = now
            depth[next] = depth[now] + 1
            q.add(next)
        }
    }

    for (k in 1..max) {
        for (n in 1..nodeSize) {
            parent[k][n] = parent[k - 1][parent[k - 1][n]]
        }
    }

    val querySize = readLine().toInt()
    repeat(querySize) {
        var split = readLine().split(" ")
        var n1 = split[0].toInt()
        var n2 = split[1].toInt()

        writer.write("${solution.lcs(depth, parent, n1, n2, max)}\n")
    }

    writer.flush()
}