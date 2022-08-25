package boj.gold

import java.util.*

/**
 * G3 #BFS
 */

fun main() = with(Scanner(System.`in`)) {
    val size = nextInt()
    val matrix = Array(size) { Array(size) { 0 } }
    var sharkLoc = 0 to 0
    (0 until size).forEach { r ->
        (0 until size).forEach { c ->
            matrix[r][c] = nextInt()
            if (matrix[r][c] == 9) {
                sharkLoc = r to c
                matrix[r][c] = 0
            }
        }
    }

    var sumOfDist = 0
    var sharkSize = 2
    var eat = 0
    val q: Queue<Pair<Pair<Int, Int>, Int>> = LinkedList()

    val dirs = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)

    q.add(sharkLoc to 0)
    while (true) {
        val visited = Array(size) { Array(size) {false} }
        val candis = arrayListOf<Pair<Pair<Int, Int>, Int>>()
        var minDist = size * size
        while (q.isNotEmpty()) {
            val (loc, dist) = q.poll()
            val (row, col) = loc
            if (visited[row][col]) {
                continue
            }

            visited[row][col] = true
            if (matrix[row][col] in 1 until sharkSize) { // 자신보다 작은 물고기가 있는 칸
                if (minDist >= dist) {
                    minDist = dist
                    candis.add(loc to dist)
                }
            } else if (matrix[row][col] == 0 || matrix[row][col] == sharkSize) { // 최초 상어 위치 또는 물고기가 없는 위치 또는 같은 크기 물고기
                for ((dR, dC) in dirs) {
                    val newRow = row + dR
                    val newCol = col + dC
                    if (newRow in 0 until size && newCol in 0 until size && !visited[newRow][newCol]) {
                        q.add((newRow to newCol) to dist + 1)
                    }
                }
            }
        }

        candis.sortWith { p1, p2 ->
            if (p1.first.first == p2.first.first) { // row 높이가 같다면
                p1.first.second.compareTo(p2.first.second) // 더 왼쪽에 있는 column
            } else {
                p1.first.first.compareTo(p2.first.first) // 더 위쪽에 있는 row
            }
        }

        // 물고리를 잡아먹지 못했으면 그대로 종료
        if (candis.isEmpty()) {
            print(sumOfDist)
            return
        } else {
            val (loc, dist) = candis[0]
            sumOfDist += dist
            if (++eat == sharkSize) {
                sharkSize++
                eat = 0
            }
            q.add(loc to 0)
            matrix[loc.first][loc.second] = 0
        }
    }
}