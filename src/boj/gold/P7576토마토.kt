package boj.gold

import java.util.*

/**
 * G5 #BFS
 *
 * 출발점이 여러개 있을 수 있는 BFS
 * => 여러 출발점에 대해 동시에 BFS를 진행해야 한다
 * => Queue 특성상 별도의 처리를 해줄 필요 없음
 *    최초에 출발점들만 큐에 잘 넣는다면 너비 순서는 지켜짐
 *
 * 문제는 모든 곳을 방문하기 위해 몇번 반복해야 하는지 구하는 것
 * => 너비와 너비를 구분해야함(큐에 그냥 넣으면 어디까지가 이번 너비이고 어디부터가 다음 너비인지 알 수 없음)
 * => 큐에 넣을 때 너비를 알 수 있는 값과 함께 넣거나,
 *    별도의 저장공간을 만들어 너비마다 모든 원소를 해당 저장공간에 옮겨담아 너비 구분 가능
 */
fun main() = with(Scanner(System.`in`)) {
    val col = nextInt()
    val row = nextInt()
    val matrix = Array(row) { Array(col) {0} }
    val visited = Array(row) { Array(col) {false} }
    val queue: Queue<Pair<Int, Int>> = LinkedList()
    val directions = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1) // URDL

    (0 until row).forEach { i ->
        (0 until col).forEach { j ->
            val now = nextInt()
            matrix[i][j] = now
            if (now == 1) {
                queue.add(i to j)
            }
        }
    }

    if (isAllVisited(matrix)) {
        print(0)
        return
    }

    var count = 0
    while(queue.isNotEmpty()) {
        val tomatoes = arrayListOf<Pair<Int, Int>>()
        repeat(queue.size) { tomatoes.add(queue.poll()) }
        var change = false

        for (tomato in tomatoes) {
            val (nowRow, nowCol) = tomato
            if (visited[nowRow][nowCol] || matrix[nowRow][nowCol] == -1) {
                continue
            }

            matrix[nowRow][nowCol] = 1
            visited[nowRow][nowCol] = true
            change = true

            for ((dirRow, dirCol) in directions) {
                val newRow = nowRow + dirRow
                val newCol = nowCol + dirCol
                if (isValidIndex(matrix.size, matrix[0].size, newRow, newCol)) {
                    queue.add(newRow to newCol)
                }
            }
        }

        if (change) {
            count++
        }
    }

    print(if (isAllVisited(matrix)) {
        count-1
    } else {
        -1
    })
}

fun isValidIndex(rowSize: Int, colSize: Int, row: Int, col: Int): Boolean {
    return row >= 0 && col >= 0 && row < rowSize && col < colSize
}

fun isAllVisited(matrix: Array<Array<Int>>) : Boolean{
    return matrix.all { row -> row.none { it == 0 } }
}