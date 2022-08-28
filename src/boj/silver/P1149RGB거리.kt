package boj.silver

import java.util.*

/**
 * S1 #DP
 */

fun main() = with(Scanner(System.`in`)) {
    val N = nextInt()

    val matrix = Array(N) { Array(3) { 0 } }
    repeat(N) {
        matrix[it][0] = nextInt()
        matrix[it][1] = nextInt()
        matrix[it][2] = nextInt()
    }

    val cache = Array(N) { Array(3) { 1_000_000 } }

    print(minOf(func(matrix, cache, 0, 0),
        func(matrix, cache, 0, 1),
        func(matrix, cache, 0, 2)))
}

fun func(matrix: Array<Array<Int>>, cache: Array<Array<Int>>, row: Int, col: Int): Int {
    if (row == matrix.lastIndex) {
        return matrix[row][col]
    } else if (cache[row][col] != 1_000_000) {
        return cache[row][col]
    }

    cache[row][col] = matrix[row][col] + when(col) {
        0 -> minOf(func(matrix, cache, row + 1, 1), func(matrix, cache, row + 1, 2))
        1 -> minOf(func(matrix, cache, row + 1, 0), func(matrix, cache, row + 1, 2))
        else -> minOf(func(matrix, cache, row + 1, 0), func(matrix, cache, row + 1, 1))
    }

    return cache[row][col]
}