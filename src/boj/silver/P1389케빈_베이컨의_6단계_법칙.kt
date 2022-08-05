package boj.silver

import java.util.*

/**
 * S1 #BFS
 *
 * all to all 가장 짧은 거리를 계산해야 한다
 * 어떤 알고리즘이 있었던 것 같은데, 까먹어서 BFS로 구현했다
 * 가중치가 없는 edge의 경우 BFS로 계산하는 게 DFS 보다 거리를 계산하기 더 쉬운 것 같다
 *
 * 그래프의 연결관계를 나타내는 board라는 2차원 배열에 거리를 덮어쓰는 실수를 했는데,
 * 문제 조건상 모든 노드는 연결되어 있으므로 board에 거리를 덮어쓰면 모든 노드가 연결이 되어 답이 이상해진다
 * 연결상태를 1로 나타내다가 1보다 더 큰 다른 숫자로 덮어쓰는 것은 연결상태에 아무 영향도 주지 않지만,
 * 연결이 없음을 나타내는 0에 다른 값을 덮어써선 안된다
 * 공간을 조금 아끼려다가 꽤 오래 헤맸다
 */

fun main() = with(Scanner(System.`in`)) {
    val nNodes = nextInt()
    val nEdges = nextInt()
    val board = Array(nNodes) { Array(nNodes) { 0 } }
    val minLengthBoard = Array(nNodes) { Array(nNodes) { 0 } }
    repeat(nEdges) {
        val n1 = nextInt() - 1
        val n2 = nextInt() - 1
        board[n1][n2] = 1
        board[n2][n1] = 1
    }

    (0 until nNodes).forEach { bfsStartNode ->
        val queue = LinkedList<Pair<Int, Int>>()
        queue.addLast(bfsStartNode to 0)
        val visited = Array(nNodes) { false }

        while(queue.isNotEmpty()) {
            val nodeToDist = queue.removeFirst()
            val node = nodeToDist.first
            val dist = nodeToDist.second

            if (visited[node]) {
                continue
            }

            visited[node] = true
            minLengthBoard[bfsStartNode][node] = dist

            for (i in 0 until board[node].size) {
                if (!visited[i] && board[node][i] == 1) {
                    queue.addLast(i to dist + 1)
                }
            }
        }
    }

    val answer = minLengthBoard.withIndex().map { (i, row) ->
        i to row.sumOf { it }
    }.toList().sortedWith { p1, p2 ->
        if (p1.second == p2.second) {
            p1.first.compareTo(p2.first)
        } else {
            p1.second.compareTo(p2.second)
        }
    }.first()
    print(answer.first + 1)
}