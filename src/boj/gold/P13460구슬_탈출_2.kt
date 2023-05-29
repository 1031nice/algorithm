package boj.gold

import java.util.*

data class Ball(
    var row: Int,
    var col: Int,
    val cnt: Int = 0,
    val goal: Boolean = false,
) {
    fun meFirst(dir: Int, otherBall: Ball): Boolean = when (dir) {
        0 -> { // UP
            row <= otherBall.row
        }
        1 -> { // RIGHT
            col >= otherBall.col
        }
        2 -> { // DOWN
            row >= otherBall.row
        }
        else -> { // LEFT
            col <= otherBall.col
        }
    }

}

fun main() = with(Scanner(System.`in`)) {
    val row = nextInt()
    val col = nextInt()
    val matrix = Array(row) { CharArray(col) { ' ' } }
    repeat(row) {
        matrix[it] = next().toCharArray()
    }

    var redBall = Ball(0, 0)
    var blueBall = Ball(0, 0)

    for ((i, r) in matrix.withIndex()) {
        for ((j, ch) in r.withIndex()) {
            if (ch == 'R') {
                redBall = Ball(i, j)
                matrix[i][j] = '.' // 매번 들고다닐 것이므로 원본에서는 값을 없앤다
            }
            if (ch == 'B') {
                blueBall = Ball(i, j)
                matrix[i][j] = '.' // 매번 들고다닐 것이므로 원본에서는 값을 없앤다
            }
        }
    }

    val q = LinkedList<Pair<Ball, Ball>>()
    q.add(redBall to blueBall)

    while (q.isNotEmpty()) {
        val (nowRedBall, nowBlueBall) = q.poll()

        if (nowRedBall.cnt > 10 || nowBlueBall.cnt > 10) {
            println(-1)
            return
        }

        repeat(4) {
            if (roll(matrix, q, nowRedBall.copy(), nowBlueBall.copy(), it)) {
                return@main
            }
        }

    }
}

private fun roll(
    matrix: Array<CharArray>,
    q: LinkedList<Pair<Ball, Ball>>,
    redBall: Ball,
    blueBall: Ball,
    dir: Int,
): Boolean {
    if (redBall.meFirst(dir, blueBall)) {
        val newRedBall = P13460구슬_탈출_2.roll(matrix, redBall, dir)
        if (!newRedBall.goal) {
            matrix[newRedBall.row][newRedBall.col] = '#'
        }
        val newBlueBall = P13460구슬_탈출_2.roll(matrix, blueBall, dir)
        if (!newRedBall.goal) {
            matrix[newRedBall.row][newRedBall.col] = '.'
        }

        if (!newBlueBall.goal) {
            if (newRedBall.goal) {
                println(if (newRedBall.cnt <= 10) newRedBall.cnt else -1)
                return true
            }
            q.add(newRedBall to newBlueBall)
        }
    } else {
        val newBlueBall = P13460구슬_탈출_2.roll(matrix, blueBall, dir)
        if (!newBlueBall.goal) {
            matrix[newBlueBall.row][newBlueBall.col] = '#'
            val newRedBall = P13460구슬_탈출_2.roll(matrix, redBall, dir)
            if (newRedBall.goal) {
                println(if (newRedBall.cnt <= 10) newRedBall.cnt else -1)
                return true
            }
            matrix[newBlueBall.row][newBlueBall.col] = '.'
            q.add(newRedBall to newBlueBall)
        }
    }
    return false
}

object P13460구슬_탈출_2 {
    val dirRow = arrayOf(-1, 0, 1, 0)
    val dirCol = arrayOf(0, 1, 0, -1)

    fun roll(matrix: Array<CharArray>, ball: Ball, dir: Int): Ball {
        while (true) {
            ball.row += dirRow[dir]
            ball.col += dirCol[dir]
            if (matrix[ball.row][ball.col] == '#') {
                break
            } else if (matrix[ball.row][ball.col] == 'O') {
                return Ball(0, 0, ball.cnt + 1, true)
            }
        }

        ball.row -= dirRow[dir]
        ball.col -= dirCol[dir]

        return Ball(ball.row, ball.col, ball.cnt + 1, false)
    }

}
