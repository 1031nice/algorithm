package boj.silver

import java.util.*

/**
 * S2 #BFS
 *
 * adjacent matrix 메모리 초과 -> adjacent list
 */

fun main() = with(Scanner(System.`in`)) {
    val nNode = nextInt()
    val matrix = Array(nNode) { arrayListOf<Int>() }
    repeat(nNode - 1) {
        val n1 = nextInt() - 1
        val n2 = nextInt() - 1
        matrix[n1].add(n2)
        matrix[n2].add(n1)
    }

    val parent = Array(nNode) { 0 }

    val visited = Array(nNode) { false }
    val q = LinkedList<Int>()
    q.add(0)
    while (q.isNotEmpty()) {
        val now = q.pop()
        if (visited[now]) {
            continue
        }

        visited[now] = true
        for (node in matrix[now]) {
            if (!visited[node]) {
                q.add(node)
                parent[node] = now + 1
            }
        }
    }

    (1..parent.lastIndex).forEach { println(parent[it]) }
}