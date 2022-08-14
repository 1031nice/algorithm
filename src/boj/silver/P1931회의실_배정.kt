package boj.silver

import java.util.*

/**
 * S1 #Greedy
 *
 * 회의 시간이 겹치는 그룹 중에서 단 하나의 회의만 선택할 수 있는데
 * 그룹의 기준은 가장 먼저 끝나는 회의를 기준으로 한다
 *
 * ex)
 * (1, 4) 기준 그룹 (3, 5), (0, 6)
 * 그 다음 가장 먼저 끝나는 회의 (5, 7)
 * (5, 7) 기준 그룹 (3, 8), (5, 9), (6, 10)
 * 그 다음 가장 먼저 끝나는 회의
 * (8, 11)
 * ...
 */

fun main() = with(Scanner(System.`in`)) {
    val minEndPq = PriorityQueue<Pair<Int, Int>> { p1, p2 ->
        if (p1.second == p2.second) {
            p1.first.compareTo(p2.first)
        } else {
            p1.second.compareTo(p2.second)
        }
    }

    repeat(nextInt()) {
        minEndPq.add(nextInt() to nextInt())
    }

    var lastEndTime = -1
    var count = 0
    while (minEndPq.isNotEmpty()) {
        val (startTime, endTime) = minEndPq.poll()
        if (startTime >= lastEndTime) {
            lastEndTime = endTime
            count++
        }
    }

    print(count)
}