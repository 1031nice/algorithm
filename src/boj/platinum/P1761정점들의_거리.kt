package boj.platinum

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val nodeSize = readLine().toInt()
    val adjList = Array(nodeSize + 1) { mutableListOf<Pair<Int, Int>>() }
    val visited = Array(nodeSize + 1) { false }
    val parent = Array(nodeSize + 1) { 0 }
    val depth = Array(nodeSize + 1) { 0 }
    val dist = Array(nodeSize + 1) { 0 }

    repeat(nodeSize - 1) {
        val split = readLine().split(" ")
        val n1 = split[0].toInt()
        val n2 = split[1].toInt()
        val d = split[2].toInt()
        adjList[n1].add(n2 to d)
        adjList[n2].add(n1 to d)
    }

    val q = LinkedList<Int>()
    q.add(1)
    parent[1] = 0
    dist[1] = 0
    depth[1] = 0
    visited[1] = true

    while (q.isNotEmpty()) {
        val now = q.poll()
        for ((next, d) in adjList[now]) {
            if (visited[next]) {
                continue
            }

            visited[next] = true
            parent[next] = now
            dist[next] = dist[now] + d
            depth[next] = depth[now] + 1
            q.add(next)
        }
    }

    val querySize = readLine().toInt()
    repeat(querySize) {
        var split = readLine().split(" ")
        var n1 = split[0].toInt()
        var n2 = split[1].toInt()

        val lcs = lcs(depth, parent, n1, n2)

        println(dist[n1] + dist[n2] - 2 * dist[lcs])
    }
}

fun lcs(depth: Array<Int>, parent: Array<Int>, n1: Int, n2: Int): Int {
    var varN1 = n1
    var varN2 = n2
    if (depth[varN1] < depth[varN2]) {
        val temp = varN1
        varN1 = varN2
        varN2 = temp
    }

    while (depth[varN1] != depth[varN2]) {
        varN1 = parent[varN1]
    }
    while (varN1 != varN2) {
        varN1 = parent[varN1]
        varN2 = parent[varN2]
    }

    return varN1
}