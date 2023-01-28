package boj.silver

import java.util.*

fun main() = with(Scanner(System.`in`)) {
    val solution = P1890점프()
    val length = nextInt()
    val matrix = Array(length) { Array(length) { 0 } }
    for (i in 0 until length) {
        for (j in 0 until length) {
            matrix[i][j] = nextInt()
        }
    }

    print(solution.func(matrix, Array(length) { Array(length) { -1 } }, 0, 0))
}

class P1890점프 {
    fun func(matrix: Array<Array<Int>>, cache: Array<Array<Long>>, row: Int, col: Int): Long {
        if (row >= matrix.size || col >= matrix[row].size) {
            return 0
        } else if (cache[row][col] >= 0) {
            return cache[row][col]
        } else if (row == matrix.lastIndex && col == matrix[row].lastIndex) {
            return 1
        }

        val jump = matrix[row][col]
        if (jump == 0) {
            return 0
        }
        cache[row][col] = func(matrix, cache, row + jump, col) + func(matrix, cache, row, col + jump)
        return cache[row][col]
    }
}