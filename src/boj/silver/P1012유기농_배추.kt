package boj.silver

import java.util.*

fun main() = with(Scanner(System.`in`)) {
    repeat(nextInt()) {
        val row = nextInt()
        val col = nextInt()
        val matrix = Array(row) { Array(col) { 0 } }
        repeat(nextInt()) {
            matrix[nextInt()][nextInt()] = 1
        }

        val visited = Array(matrix.size) { Array(matrix[0].size) { false } }
        var cnt = 0
        for (i in matrix.indices) {
            for (j in matrix[i].indices) {
                if (matrix[i][j] == 0 || visited[i][j]) {
                    continue
                }

                bfs(matrix, visited, i, j)
                cnt++
            }
        }
        println(cnt)
    }
}

fun bfs(matrix: Array<Array<Int>>, visited: Array<Array<Boolean>>, row: Int, col: Int) {
    val dir = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)
    val q = LinkedList<Pair<Int, Int>>()
    q.add(row to col)

    while (q.isNotEmpty()) {
        val (r, c) = q.poll()
        if (visited[r][c]) {
            continue
        }
        visited[r][c] = true

        for (d in dir.indices) {
            val newRow = r + dir[d].first
            val newCol = c + dir[d].second
            if (newRow >= matrix.size || newRow < 0 || newCol >= matrix[newRow].size || newCol < 0 || visited[newRow][newCol] || matrix[newRow][newCol] == 0) {
                continue
            }

            q.add(newRow to newCol)
        }
    }
}