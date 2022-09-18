package boj.gold

import java.util.*
import kotlin.math.max

/**
 * G4 #BruteForce
 */

fun main() = with(Scanner(System.`in`)) {
    val solution = P1987알파벳()

    val row = nextInt()
    val col = nextInt()
    val matrix = Array(row) { Array(col) { ' ' } }

    (0 until row).forEach { r ->
        val next = next()
        for (c in next.indices) {
            matrix[r][c] = next[c]
        }
    }

    val visited = Array(26) { false }
    print(solution.func(matrix, visited, 0, 0, 0))
}

class P1987알파벳 {
    val dirRow = arrayOf(-1, 0, 1, 0)
    val dirCol = arrayOf(0, 1, 0, -1)

    fun func(matrix: Array<Array<Char>>, visited: Array<Boolean>, row: Int, col: Int, cnt: Int): Int {
        if (row !in 0 until matrix.size || col !in 0 until matrix[row].size) {
            return 0
        }

        val now = matrix[row][col] - 'A'
        if (visited[now]) {
            return cnt
        }

        visited[now] = true
        var ret = cnt + 1
        for (dir in 0..3) {
            val newRow = row + dirRow[dir]
            val newCol = col + dirCol[dir]
            ret = max(ret, func(matrix, visited, newRow, newCol, cnt + 1))
        }
        visited[now] = false

        return ret
    }
}