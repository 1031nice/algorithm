package boj.gold

import java.util.*

var cnt2623 = 0

/**
 * G3 #TopologicalSort
 *
 * 위상정렬은 DFS를 통해 풀 수도 있고 -> withDfs()
 * DFS를 이용하지 않고도 풀이가 가능하다 -> withoutDfs()
 */

fun main() = with(Scanner(System.`in`)) {
    val solution = P2623음악프로그램()
    val nNode = nextInt()
    val matrix = Array(nNode) { arrayListOf<Int>() }
//    val ingoingEdges = Array(nNode) { 0 }

    repeat(nextInt()) {
        val len = nextInt()
        var now = nextInt() - 1
        for (i in 1 until len) {
            val next = nextInt() - 1
            matrix[now].add(next)
//            ingoingEdges[next]++
            now = next
        }
    }

    val visited = Array(nNode) { false }
    val result = LinkedList<Int>()

    solution.withDfs(nNode, visited, result, matrix)
//    solution.withoutDfs(nNode, ingoingEdges, visited, result, matrix)
}

val WHITE = 0
val GRAY = 1
val BLACK = 2
var CYCLE = false

class P2623음악프로그램 {
    // O(N+M)
    fun withDfs(nNode: Int, visited: Array<Boolean>, result: LinkedList<Int>, matrix: Array<ArrayList<Int>>) {
        val color = Array(nNode) { WHITE }
        (0 until nNode).forEach { node ->
            if (!visited[node]) {
                dfs(result, matrix, visited, color, node)
                if (CYCLE) {
                    print("0")
                    return
                }
            }
        }
        result.forEach { println(it + 1) }
    }

    private fun dfs(
        nodes: LinkedList<Int>,
        matrix: Array<ArrayList<Int>>,
        visited: Array<Boolean>,
        color: Array<Int>,
        start: Int
    ) {
        visited[start] = true
        color[start] = GRAY

        for (neighbor in matrix[start]) {
            if (!visited[neighbor]) {
                dfs(nodes, matrix, visited, color, neighbor)
            } else if (color[neighbor] == GRAY) {
                CYCLE = true
                return
            }
        }

        nodes.addFirst(start)
        color[start] = BLACK
    }

    // O(N^2)
    fun withoutDfs(
        nNode: Int,
        ingoingEdges: Array<Int>,
        visited: Array<Boolean>,
        result: LinkedList<Int>,
        matrix: Array<ArrayList<Int>>
    ) {
        while (true) {
            if (cnt2623 == nNode) {
                break
            }

            val (min, minIndex) = getMinToMinIndex(ingoingEdges, visited)
            if (min != 0) {
                println(0)
                return
            }

            matrix[minIndex].forEach { ingoingEdges[it]-- }
            visited[minIndex] = true
            cnt2623++

            result.addLast(minIndex + 1)
        }
        result.forEach { println("$it") }
    }

    private fun getMinToMinIndex(ingoingEdges: Array<Int>, visited: Array<Boolean>): Pair<Int, Int> {
        var min = 1_001
        var minIndex = -1
        for ((i, v) in ingoingEdges.withIndex()) {
            if (visited[i]) {
                continue
            }
            if (min > v) {
                min = v
                minIndex = i
            }
        }
        return Pair(min, minIndex)
    }
}