package boj.gold

import java.util.*

/**
 * G5 #BFS
 *
 * 숨바꼭질과 달리 숨바꼭질 3에서는 *2가 1초가 아닌 0초이다
 * 따라서 곱셈으로 갈 수 있는 위치는 거리가 0이므로 언제나 먼저 계산되어야 한다
 *
 * 큐에 그냥 넣어서는 0초가 나중에 계산될 수도 있다
 * 예를 들어, 1 -> 2는 *2로 가면 0초이고 +1로 가면 1초인데
 * 큐에 넣는 순서에 따라서 +1로 계산될 수도 있다
 *
 * 또한 5에서 시작하는 경우 5, 10, 20, ... 이 먼저 모두 계산된 후에
 * 6, 12, 24, ... 이런식으로 계산이 되어야 하는데
 * 그냥 큐를 사용할 경우 *2 이후에 +1 또는 -1이 큐에 들어감에 따라
 * 5, 10, 6, 4, 20, 11, 9, ... 이런식으로 큐에 쌓이므로 곱셈부터 먼저 계산되도록 처리할 수 없다
 *
 * 언제나 *2가 먼저 계산될 수 있도록 큐를 PQ로 변경하였다
 */
fun main() = with(Scanner(System.`in`)) {
    val start = nextInt()
    val target = nextInt()

    if (target <= start) {
        print(start - target)
        return
    }

    val visited = Array(target + 1 + 1) { false }
    val queue = PriorityQueue<Pair<Int, Int>> { p1, p2 -> p1.second.compareTo(p2.second) }
    queue.add(target to 0)
    while (queue.isNotEmpty()) {
        val (num, length) = queue.poll()
        if (num == start) {
            print(length)
            break
        }

        if (visited[num]) {
            continue
        }

        visited[num] = true
        if (num + 1 < visited.size && !visited[num + 1]) {
            queue.add(num + 1 to length + 1)
        }
        if (num - 1 >= 0 && !visited[num - 1]) {
            queue.add(num - 1 to length + 1)
        }
        if (num % 2 == 0 && !visited[num / 2]) {
            queue.add(num / 2 to length)
        }
    }
}