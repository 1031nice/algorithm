package boj.silver

import java.util.*

fun main() = with(Scanner(System.`in`)) {
    val nNode = nextInt()
    val matrix = Array(nNode) { Array(nNode) { 0 } }

    (0 until nNode).forEach { row ->
        (0 until nNode).forEach { col ->
            matrix[row][col] = nextInt()
        }
    }

    (0 until nNode).forEach {
        val visited = Array(nNode) { false }
        dfs(matrix, visited, it, 0)
        visited.forEach { print("${if (it) 1 else 0} ") }
        println()
    }
}

fun dfs(matrix: Array<Array<Int>>, visited: Array<Boolean>, start: Int, count: Int) {
    if (visited[start]) {
        return
    }

    // 이 문제에서는 시작점에서 시작하는 그 시점에는 시작점이 방문되지 않은 것으로 본다
    if (count != 0) {
        visited[start] = true
    }
    for (col in 0 until matrix[start].size) {
        if (matrix[start][col] == 1 && !visited[col]) {
            dfs(matrix, visited, col, count + 1)
        }
    }
}

    