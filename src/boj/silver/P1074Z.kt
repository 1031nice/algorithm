package boj.silver

import java.util.*
import kotlin.math.pow

/**
 * S1 #Recursion
 */

fun main() = with(Scanner(System.`in`)) {
    print(getOrder(0, 0, nextInt() to nextInt(), 2.0.pow(nextInt()).toInt()))
}

fun getOrder(startRow: Int, startCol: Int, loc: Pair<Int, Int>, length: Int): Int {
    var sector = 0
    var nextStartRow = startRow
    var nextStartCol = startCol

    if (loc.first < startRow + length/2 && loc.second < startCol + length/2) {
        sector = 1
    } else if (loc.first < startRow + length/2 && loc.second >= startCol + length/2) {
        sector = 2
        nextStartCol = startCol + length/2
    } else if (loc.first >= startRow + length/2 && loc.second < startCol + length/2) {
        sector = 3
        nextStartRow = startRow + length/2
    } else {
        sector = 4
        nextStartCol = startCol + length/2
        nextStartRow = startRow + length/2
    }

    return if (length == 2) {
        sector - 1
    } else {
        (sector - 1) * length / 2 * length / 2 + getOrder(nextStartRow, nextStartCol, loc, length / 2)
    }
}