package boj.gold

import java.util.*

/**
 * G5 #DFS
 */
fun main() = with(Scanner(System.`in`)) {
    val nNode = nextInt()
    val matrix = Array(nNode) { CharArray(nNode) }
    for (i in 0 until nNode) {
        matrix[i] = next().toCharArray()
    }

    var count1 = 0
    var count2 = 0

    var visited = Array(nNode) { Array(nNode) { false } }
    for (i in matrix.indices) {
        for (j in 0 until matrix[i].size) {
            if (!visited[i][j]) {
                dfs(matrix, visited, i, j, matrix[i][j])
                count1++
            }
        }
    }

    for (i in matrix.indices) {
        for (j in 0 until matrix[i].size) {
            if (matrix[i][j] == 'G') {
                matrix[i][j] = 'R'
            }
        }
    }

    visited = Array(nNode) { Array(nNode) { false } }
    for (i in 0 until matrix.size) {
        for (j in 0 until matrix[i].size) {
            if (!visited[i][j]) {
                dfs(matrix, visited, i, j, matrix[i][j])
                count2++
            }
        }
    }

    print("$count1 $count2")
}

val dirRow = intArrayOf(-1, 0, 1, 0)
val dirCol = intArrayOf(0, 1, 0, -1)

fun dfs(matrix: Array<CharArray>, visited: Array<Array<Boolean>>, row: Int, col: Int, char: Char) {
    if (row !in 0..matrix.lastIndex || col !in 0..matrix[row].lastIndex
        || visited[row][col]
        || matrix[row][col] != char
    ) {
        return
    }

    visited[row][col] = true
    (0 until 4).forEach {
        dfs(matrix, visited, row + dirRow[it], col + dirCol[it], char)
    }

}
