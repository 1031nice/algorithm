package boj.gold

import java.util.*

/**
 * #G3 #TopologicalSort
 */

fun main() = with(Scanner(System.`in`)) {
    val nNode = nextInt()
    val nEdge = nextInt()
    val matrix = Array(nNode) { arrayListOf<Int>() }

    repeat(nEdge) { matrix[nextInt() - 1].add(nextInt() - 1) }

    val visited = Array(nNode) { false }
    val result = LinkedList<Int>()
    repeat(nNode) { topologicalSort(result, matrix, visited, it) }
    result.forEach { print("$it ") }
}

fun topologicalSort(result: LinkedList<Int>, matrix: Array<ArrayList<Int>>, visited: Array<Boolean>, start: Int) {
    if (visited[start]) {
        return
    }

    visited[start] = true
    for (next in matrix[start]) {
        if (!visited[next]) {
            topologicalSort(result, matrix, visited, next)
        }
    }
    result.addFirst(start + 1)
}