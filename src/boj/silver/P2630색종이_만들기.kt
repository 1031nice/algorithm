import java.util.*

/**
 * S2 #Recursion
 *
 * P1992 쿼드트리와 완전히 같은 문제
 */

var blue = 0
var white = 0

fun main() = with(Scanner(System.`in`)) {
    val length = nextInt()
    val board = Array(length) { Array(length) { 0 } }
    (0 until length).forEach { row ->
        (0 until length).forEach { col ->
            board[row][col] = nextInt()
        }
    }
    func(board, 0, 0, length)
    println(white)
    println(blue)
}

fun func(board: Array<Array<Int>>, startRow: Int, startCol: Int, length: Int) {
    if (length == 1 || isAllSame(board, startRow, startCol, length)) {
        if (board[startRow][startCol] == 0) white++ else blue++
    }
    else {
        val nextLength = length / 2
        func(board, startRow, startCol, nextLength)
        func(board, startRow, startCol + nextLength, nextLength)
        func(board, startRow + nextLength, startCol, nextLength)
        func(board, startRow + nextLength, startCol + nextLength, nextLength)
    }
}

fun isAllSame(board: Array<Array<Int>>, startRow: Int, startCol: Int, length: Int): Boolean {
    return (startRow until startRow + length).all { row ->
        (startCol until startCol + length).all { col ->
            board[row][col] == board[startRow][startCol]
        }
    }
}
