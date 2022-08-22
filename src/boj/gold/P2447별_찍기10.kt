package boj.gold

import java.util.*

/**
 * G5 #Recursion
 *
 * 왜 바로 출력하려고 했을까
 * 배열에 저장해둔 뒤 마지막에 한 번에 출력할 수 있는데
 */

fun main() = with(Scanner(System.`in`)) {
    val num = nextInt()
    val matrix = Array(num) { Array(num) {' '} }
    func(matrix, num, 0, 0)
    print(buildString {
        (0 until num).forEach { row ->
            (0 until num).forEach { col ->
                append(matrix[row][col])
            }
            append("\n")
        }
    })
}

fun func(matrix: Array<Array<Char>>, num: Int, row: Int, col: Int) {
    if (num == 1) {
        matrix[row][col] = '*'
        return
    }

    repeat(3) { r ->
        repeat(3) inner@{ c ->
            if (r == 1 && c == 1) {
                return@inner
            }
            func(matrix, num / 3, row + num * r / 3, col + num * c / 3)
        }
    }
}