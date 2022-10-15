package boj.gold

import java.util.*
import kotlin.collections.HashSet

/**
 * G4 #BruteForce
 *
 * 행단위로 채우려고 접근했을 때 시간초과
 * 한칸씩 채우려고 접근하고 Set 이용하여 탐색공간을 줄였더니 통과
 */

val rowSetArray = Array(9) { HashSet<Char>() }
val colSetArray = Array(9) { HashSet<Char>() }
val groupSetArray = Array(9) { HashSet<Char>() }

var solved = false

fun main() = with(Scanner(System.`in`)) {
    val solution = P2239스도쿠()

    val matrix = Array(9) { Array(9) { ' ' } }
    repeat(9) { row ->
        matrix[row] = next().toCharArray().toTypedArray()
    }

    solution.sudoku(matrix, 0)
}

class P2239스도쿠 {
    fun sudoku(matrix: Array<Array<Char>>, now: Int) {
        if (solved) {
            return
        }
        else if (now == 81) {
            repeat(9) { r ->
                repeat(9) { c ->
                    print("${matrix[r][c]}")
                }
                println()
            }
            solved = true
            return
        }

        val row = now / 9
        val col = now % 9
        val group = getGroup(row, col)

        val num = matrix[row][col]
        if (num != '0') {
            rowSetArray[row].add(num)
            colSetArray[col].add(num)
            groupSetArray[group].add(num)
            sudoku(matrix, now + 1)
        } else {
            for (ch in ('1'..'9')) {
                if (!rowSetArray[row].contains(ch) && !colSetArray[col].contains(ch) && !groupSetArray[group].contains(ch)) {
                    rowSetArray[row].add(num)
                    colSetArray[col].add(num)
                    groupSetArray[group].add(num)

                    matrix[row][col] = ch
                    if (rowCheck(matrix, row) && colCheck(matrix, col) && groupCheck(matrix, group)) {
                        sudoku(matrix, now + 1)
                    }
                    matrix[row][col] = '0'

                    rowSetArray[row].remove(num)
                    colSetArray[col].remove(num)
                    groupSetArray[group].remove(num)
                }
            }
        }
    }

    fun getGroup(row: Int, col: Int): Int {
        return row / 3 * 3 + col / 3
    }

    fun rowCheck(matrix: Array<Array<Char>>, row: Int): Boolean {
        val visited = Array(9) { false }
        for (i in 0 until 9) {
            val num = matrix[row][i]
            if (num == '0') {
                continue
            } else if (visited[num.digitToInt() - 1]) {
                return false
            }
            visited[num.digitToInt() - 1] = true
        }
        return true
    }

    fun colCheck(matrix: Array<Array<Char>>, col: Int): Boolean {
        val visited = Array(9) { false }
        for (i in 0 until 9) {
            val num = matrix[i][col]
            if (num == '0') {
                continue
            } else if (visited[num.digitToInt() - 1]) {
                return false
            }
            visited[num.digitToInt() - 1] = true
        }
        return true
    }

    fun groupCheck(matrix: Array<Array<Char>>, group: Int): Boolean {
        val set = HashSet<Int>()
        val rowStart = group / 3 * 3
        val colStart = group % 3 * 3
        var count = 0

        for (row in rowStart..rowStart+2) {
            for (col in colStart..colStart+2) {
                val num = matrix[row][col]
                if (num == '0') {
                    count++
                    continue
                }
                set.add(num.digitToInt())
            }
        }

        return set.size == 9 - count
    }
}