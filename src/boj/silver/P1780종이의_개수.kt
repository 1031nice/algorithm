package boj.silver

import java.util.*

/**
 * S2 #Recursion
 */

fun main() = with(Scanner(System.`in`)) {
    val length = nextInt()
    val board = Array(length) { Array(length) {0} }
    (0 until length).forEach { row ->
        (0 until length).forEach { col ->
            board[row][col] = nextInt()
        }
    }

    val answer = intArrayOf(0, 0, 0)
    func(board, 0, 0, length, answer)
    answer.forEach { println(it) }
}

fun func(board: Array<Array<Int>>, startRow: Int, startCol: Int, length: Int, answer: IntArray) {
    if (length == 1) {
        answer[board[startRow][startCol] + 1]++
        return
    }

    var isAllSame = true
    val value = board[startRow][startCol]
    (startRow until startRow + length).forEach outer@{ row ->
        (startCol until startCol + length).forEach inner@{ col ->
            if (board[row][col] != value) {
                isAllSame = false
                return@outer
            }
        }
    }

    if (isAllSame) {
        answer[value + 1]++
    } else {
        val nextLength = length / 3
        (0 until 3).forEach { it1 ->
            (0 until 3).forEach { it2 ->
                func(board, startRow + nextLength * it1, startCol + nextLength * it2, nextLength, answer)
            }
        }
    }
}

