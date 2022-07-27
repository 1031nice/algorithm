package boj.silver

import java.util.*
import kotlin.math.min

/**
 * S4 #BruteForce
 */
fun main() = with(Scanner(System.`in`)) {
    val row = nextInt()
    val col = nextInt()
    val board = Array<Array<Char>?>(row) { null }
    (0 until row).forEach {
        board[it] = next().toCharArray().toTypedArray()
    }

    var min = 2500
    for (i in 7 until row) {
        for (j in 7 until col) {
            min = min(min, getMin(board, i-7, i, j-7, j))
        }
    }

    print(min)
}

fun getMin(board: Array<Array<Char>?>, rowStart: Int, rowEnd: Int, colStart: Int, colEnd: Int): Int {
    val answer1 = "WBWBWBWBBWBWBWBWWBWBWBWBBWBWBWBWWBWBWBWBBWBWBWBWWBWBWBWBBWBWBWBW"
    val answer2 = "BWBWBWBWWBWBWBWBBWBWBWBWWBWBWBWBBWBWBWBWWBWBWBWBBWBWBWBWWBWBWBWB"

    var boardString = ""
    (rowStart..rowEnd).forEach { row ->
        (colStart..colEnd).forEach { col ->
            boardString += board[row]!![col]
        }
    }

    val diff1 = (0..answer1.lastIndex).count {
        answer1[it] != boardString[it]
    }
    val diff2 = (0..answer2.lastIndex).count {
        answer2[it] != boardString[it]
    }
    return min(diff1, diff2)
}
