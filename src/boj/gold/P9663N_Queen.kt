package boj.gold

import java.util.*

/**
 * G4 #BruteForce
 */

var cnt = 0

fun main() = with(Scanner(System.`in`)) {
    val num = nextInt()
    val matrix = Array(num) { Array(num) { 0 } }
    func(matrix, num, 0)
    println(cnt)
}

fun func(matrix: Array<Array<Int>>, num: Int, row: Int) {
    if (num == 0) {
        cnt++
        return
    }

    for (col in 0 until matrix[row].size) {
        if (check(matrix, row, col)) {
            matrix[row][col] = 1
            func(matrix, num - 1, row + 1)
            matrix[row][col] = 0
        }
    }
}

val dirs = arrayOf(-1 to 0, -1 to 1, 0 to 1, 1 to 1, 1 to 0, 1 to -1, 0 to -1, -1 to -1)

fun check(matrix: Array<Array<Int>>, row: Int, col: Int): Boolean {
    for ((dR, dC) in dirs) {
        var newRow = row
        var newCol = col
        while (newRow in 0 until matrix.size && newCol in 0 until matrix[newRow].size) {
            if (matrix[newRow][newCol] != 0) {
                return false
            }
            newRow += dR
            newCol += dC
        }
    }
    return true
}
