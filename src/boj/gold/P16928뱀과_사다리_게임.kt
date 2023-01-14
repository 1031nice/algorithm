package boj.gold

import java.util.*

/**
 * G5 #BFS
 *
 * 처음엔 DFS로 접근했으나 최초로 목적지에 도달했을 때
 * 최단 경로라는 보장이 없으므로 BFS로 푸는 게 더 효율적.
 *
 * 사다리 또는 뱀을 만나면 주사위를 굴리지 않고 특정 지점으로 이동하는데
 * 이때 최단 경로임을 보장하면서 탐색을 계속 진행하려면
 * 사다리 또는 뱀을 타고 이동한 곳을 큐에 넣고 끝내는 것이 아니라
 * 해당 위치에서 바로 탐색(+1 ~ +6)을 이어나가야 함.
 * 사다리 또는 뱀을 타고 이동한 것은 탐색이 아니기 때문(주사위 굴리지 않음, BFS 동심원 변화 없음).
 *
 * 예를 들어, K번 주사위를 굴려서 뱀 또는 사다리가 있는 곳에 도달했을 때
 * 뱀 또는 사다리를 통해 특정 지점으로 이동한 뒤 큐에 넣고 현재의 탐색을 끝낸다면,
 * 큐에 맨 끝에 들어가게 되는데 이때 큐의 앞쪽에 있는 것들 중 K+1 이상이 있을 수 있음.
 * 그럼 K+1이 K 보다 먼저 실행되어 최단 경로 탐색이 제대로 이루어지지 않음
 * (최초로 목적지에 도달하는 그 시점에 주사위 횟수가 최소임을 보장할 수 없음).
 *
 * 정리하면, 뱀과 사다리를 통해 이동하는 것은 횟수가 카운트 되지 않으므로
 * 뱀과 사다리를 타고 이동한 뒤에 추가로 탐색을 이어 나가야만
 * K번째 동심원에서 탐색할 수 있는 것을 모두 탐색한 셈.
 */

fun main() = with(Scanner(System.`in`)) {
    val ladders = nextInt()
    val snakes = nextInt()
    val matrix = Array(10) { Array(10) { 0 } }
    repeat(ladders + snakes) {
        val cell = nextInt() - 1
        val row = cell / 10
        val col = cell % 10
        matrix[row][col] = nextInt() - 1
    }

    val visited = Array(100) { false }
    val q = LinkedList<Pair<Int, Int>>()
    q.add(0 to 0) // cell to count

    while (q.isNotEmpty()) {
        val (cell, cnt) = q.poll()
        if (visited[cell]) {
            continue
        }
        visited[cell] = true

        val row = cell / 10
        val col = cell % 10
        if (row == 9 && col == 9) {
            print(cnt)
            return
        }

        val nextCell = if (matrix[row][col] > 0) matrix[row][col] else cell
        for (i in 1..6) {
            if (nextCell + i < 100 && !visited[nextCell + i]) {
                q.add(nextCell + i to cnt + 1)
            }
        }
    }
}