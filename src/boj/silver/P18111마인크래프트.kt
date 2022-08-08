package boj.silver

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

/**
 * S2 #BruteForce
 *
 * 규칙을 찾으려 했으나 안보임(가장 빈도가 많은 수를 기준으로 하면 항상 정답을 찾을 수 있다거나..)
 * 2차원 배열의 크기는 최대 25만개의 셀, 블럭의 최대 크기는 6400만개이지만 제일 중요한 높이는 고작 0~256
 * => 0부터 256까지 257개의 높이를 모두 검사해보자
 *      => 최저보다 낮게 만들거나 최고보다 높게 만드는 것은 불필요(최대한 빠른 작업이 목표)
 *      => min ~ max까지 검사
 * => 257 * 25만은 너무 크다
 *      => 분할정복으로 257을 log(257)으로 줄일 수 있을까?
 *          => mid에 대해 계산 후 절반을 버려도 괜찮다는 보장이 없어서 log 불가능
 *      => 반복을 줄일 수 있을까
 *          => 어떤 셀에 어떤 값이 있는지는 중요하지 않고 최종적인 합계만 중요하다
 *          => 높이는 최대 257개밖에 안되므로 배열에 각 높이의 빈도를 저장하면 2차원 배열의 크기가 아무리 크더라도 257개만 검사하면 된다
 *
 * !기존에 있던 블럭을 가방에 넣을 수 있음(재사용할 수 있음)
 */

var timeToHeight = 99_999_999 to -1

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var line = readLine().split(" ")
    val row = line[0].toInt()
    val col = line[1].toInt()
    val blocks = line[2].toInt()
    val board = Array(row) { Array(col) { 0 } }
    var min = 256
    var max = 0
    val freqs = Array(256 + 1) { 0 }
    (0 until row).forEach { r ->
        line = readLine().split(" ")
        (0 until col).forEach { c ->
            board[r][c] = line[c].toInt()
            min = min(min, board[r][c])
            max = max(max, board[r][c])
            freqs[board[r][c]]++ // 높이는 0부터 시작
        }
    }

    solve(freqs, board, blocks, min, max)

    println("${timeToHeight.first} ${timeToHeight.second}")
}

fun solve(freqs: Array<Int>, board: Array<Array<Int>>, blocks: Int, start: Int, end: Int) {
    if (start > end) {
        return
    }

    val mid = (start + end) / 2
    val time = getTime(freqs, blocks, mid)
    if (time != -1 &&
        (timeToHeight.first > time ||
        (timeToHeight.first == time && timeToHeight.second < mid))) {
        timeToHeight = time to mid
    }
    solve(freqs, board, blocks, start, mid - 1)
    solve(freqs, board, blocks, mid + 1, end)
}

private fun getTime(freqs: Array<Int>, blocks: Int, value: Int): Int {
    var time = 0
    var varBlocks = blocks
    freqs.withIndex().forEach { (height, freq) ->
        val diff = abs(value - height)
        if (height < value) { // 채워넣기
            time += diff * freq
            varBlocks -= diff * freq
        } else if (height > value) { // 빼기
            time += diff * 2 * freq
            varBlocks += diff * freq
        }
    }

    return if (varBlocks < 0) { -1 } else { time }
}

// O(NM), N: 세로, M: 가로
private fun setAs2(board: Array<Array<Int>>, blocks: Int, value: Int): Int {
    var time = 0
    var varBlocks = blocks
    for (row in board) {
        for (cell in row) {
            val diff = abs(value - cell)
            if (cell < value) { // 채워넣기
                time += diff
                varBlocks -= diff
            } else if (cell > value) { // 빼기
                time += diff * 2
                varBlocks += diff
            }
        }
    }

    return if (varBlocks < 0) { -1 } else { time }
}