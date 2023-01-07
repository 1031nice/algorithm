package leetcode.medium

import java.lang.Math.min
import kotlin.math.max

fun main() {
    val input = arrayOf(
            intArrayOf(3, 0, 8, 4),
            intArrayOf(2, 4, 5, 7),
            intArrayOf(9, 2, 6, 3),
            intArrayOf(0, 3, 1, 0)
    )
    println(maxIncreaseKeepingSkyline(input))
}

fun maxIncreaseKeepingSkyline(grid: Array<IntArray>): Int {
    val rowMax = Array(grid.size) { 0 }
    val colMax = Array(grid.size) { 0 }

    for (i in grid.indices) {
        rowMax[i] = grid[i].maxOf { it }
    }
    for (col in grid.indices) {
        var max = 0
        for (row in grid.indices) {
            max = max(max, grid[row][col])
        }
        colMax[col] = max
    }

    var sum = 0
    for (row in grid.indices) {
        for (col in grid[row].indices) {
            val min = min(rowMax[row], colMax[col])
            sum += max(min - grid[row][col], 0)
        }
    }

    return sum
}