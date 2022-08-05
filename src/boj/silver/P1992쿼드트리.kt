package boj.silver

import java.util.*

/**
 * S1 #Recursion
 */

fun main() = with(Scanner(System.`in`)) {
    val length = nextInt()
    val board = Array<String?>(length) { null }
    (0 until length).forEach { row ->
        board[row] = next()
    }
    val stringBuilder = StringBuilder()
    func(stringBuilder, board, 0, 0, length)
    print(stringBuilder.toString())
}

fun func(stringBuilder: StringBuilder, board: Array<String?>, startRow: Int, startCol: Int, length: Int) {
    if (length == 1 || isAllSame(board, startRow, startCol, length)) {
        stringBuilder.append(board[startRow]!![startCol])
    }
    else {
        stringBuilder.append("(")
        val nextLength = length / 2
        func(stringBuilder, board, startRow, startCol, nextLength)
        func(stringBuilder, board, startRow, startCol + nextLength, nextLength)
        func(stringBuilder, board, startRow + nextLength, startCol, nextLength)
        func(stringBuilder, board, startRow + nextLength, startCol + nextLength, nextLength)
        stringBuilder.append(")")
    }
}

fun isAllSame(board: Array<String?>, startRow: Int, startCol: Int, length: Int): Boolean {
    return (startRow until startRow + length).all { row ->
        (startCol until startCol + length).all { col ->
            board[row]!![col] == board[startRow]!![startCol]
        }
    }
}
