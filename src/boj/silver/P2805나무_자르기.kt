package boj.silver

import java.util.*
import kotlin.math.max

/**
 * S2 #DivideAndConquer
 *
 * 랜선 자르기와 같은 문제
 */

fun main() = with(Scanner(System.`in`)) {
    val nTree = nextInt()
    val height = nextInt()
    val trees = Array(nTree) { 0 }
    var maxHeight = 0
    (0 until nTree).forEach {
        trees[it] = nextInt()
        maxHeight = max(maxHeight, trees[it])
    }

    val solution = P2805나무_자르기()
    solution.divideAndConquer(trees, 1, maxHeight, height)
    print(solution.answer)
}

class P2805나무_자르기 {
    var answer = 0

    fun divideAndConquer(trees: Array<Int>, start: Int, end: Int, target: Int) {
        if (start > end) {
            return
        }

        val midHeight = (start + end.toLong()) / 2
        val sum = trees.sumOf { max(it - midHeight, 0) }

        if (sum >= target) {
            answer = midHeight.toInt()
            divideAndConquer(trees, (midHeight + 1).toInt(), end, target)
        } else {
            divideAndConquer(trees, start, (midHeight - 1).toInt(), target)
        }
    }
}
