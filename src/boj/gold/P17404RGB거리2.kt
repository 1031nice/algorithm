package boj.gold

import java.util.*

/**
 * G4 #DP
 *
 * 1149번 문제에 조건이 조금 더 붙었지만
 * 결국 중복을 계산을 찾고 중복 계산을 제거하는 게 핵심이다.
 *
 * 1149번에서는 top과 bottom에서의 선택이 서로 영향을 주지 않았기 때문에
 * k번째 어떤 블록을 골랐을 때 k번째 블록부터 마지막 블록까지 비용의 최솟값이 캐싱 가능했으나,
 * 이 문제에서는 top과 bottom이 서로 연결됨에 따라
 * k번째 어떤 블록을 골랐을 때 k번째 블록부터 마지막 블록까지의 비용의 최솟값이 캐싱이 불가능해졌다.
 * (top을 무엇을 골랐느냐에 따라 bottom이 영향을 받으므로
 * 비용의 최솟값 역시 top을 무엇을 골랐느냐에 따라 달라지기 때문)
 *
 * => top의 선택의 수만큼 캐시를 만들어서 해결
 * top이 1번이면서 k번째 어떤 블록을 골랐을 때 k번째 블록부터 마지막 블록까지 최소 비용 캐싱
 * top이 2번이면서 k번째 어떤 블록을 골랐을 때 k번째 블록부터 마지막 블록까지 최소 비용 캐싱
 * top이 3번이면서 k번째 어떤 블록을 골랐을 때 k번째 블록부터 마지막 블록까지 최소 비용 캐싱
 */

fun main() = with(Scanner(System.`in`)) {
    val N = nextInt()

    val matrix = Array(N) { Array(3) { 0 } }
    repeat(N) {
        matrix[it][0] = nextInt()
        matrix[it][1] = nextInt()
        matrix[it][2] = nextInt()
    }

    print(
        minOf(
            func(matrix, Array(N) { Array(3) { 1_000_000 } }, 0, 0, 0),
            func(matrix, Array(N) { Array(3) { 1_000_000 } }, 1, 0, 1),
            func(matrix, Array(N) { Array(3) { 1_000_000 } }, 2, 0, 2)
        )
    )
}

fun func(matrix: Array<Array<Int>>, cache: Array<Array<Int>>, topCol: Int, row: Int, col: Int): Int {
    if (row == matrix.lastIndex) {
        return if (topCol == col) {
            1_000_000
        } else {
            matrix[row][col]
        }
    } else if (cache[row][col] != 1_000_000) {
        return cache[row][col]
    }

    cache[row][col] = matrix[row][col] + when (col) {
        0 -> minOf(func(matrix, cache, topCol, row + 1, 1), func(matrix, cache, topCol, row + 1, 2))
        1 -> minOf(func(matrix, cache, topCol, row + 1, 0), func(matrix, cache, topCol, row + 1, 2))
        else -> minOf(func(matrix, cache, topCol, row + 1, 0), func(matrix, cache, topCol, row + 1, 1))
    }

    return cache[row][col]
}