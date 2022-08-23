package boj.gold

import java.util.*

/**
 * G4
 *
 * 풀이는 바로 떠올렸으나 구현하는 과정에서 실수가 많았다
 * 때로는 코테라 할지라도 중복되는 부분은 함수로 빼는 게 도움이 되는 것 같다
 *
 * 마지막에 최대/최소를 출력할 때
 * 큐에 같은 수가 남아 있을 수 있으므로 위의 반복에서 처리해줬던 것처럼
 * poll 이후에  Map에서 value를 감소시켜줬어야 했는데,
 * 하나만 찾으면 된다고 생각해서인지, 밤에 집중력이 떨어졌는지 감소를 시키지 않아서 헤맸다
 */
fun main() = with(Scanner(System.`in`)) {
    repeat(nextInt()) {
        val maxQ = PriorityQueue<Int>(reverseOrder())
        val minQ = PriorityQueue<Int>()
        val removeFromMinQ = hashMapOf<Int, Int>()
        val removeFromMaxQ = hashMapOf<Int, Int>()

        repeat(nextInt()) {
            if (next() == "I") {
                val input = nextInt()
                maxQ.add(input)
                minQ.add(input)
            } else {
                if (nextInt() == -1) { // min
                    poll(minQ, removeFromMinQ, removeFromMaxQ)
                } else {
                    poll(maxQ, removeFromMaxQ, removeFromMinQ)
                }
            }
        }

        val max = poll(maxQ, removeFromMaxQ, removeFromMinQ)
        val min = poll(minQ, removeFromMinQ, removeFromMaxQ)
        if (max == null) {
            println("EMPTY")
        } else if (min == null) {
            println("$max $max")
        } else {
            println("$max $min")
        }
    }
}

fun poll(q: PriorityQueue<Int>, removeFromMyQ: HashMap<Int, Int>, removeFromOtherQ: HashMap<Int, Int>): Int? {
    while (q.isNotEmpty()) {
        val poll = q.poll()
        if (removeFromOtherQ.containsKey(poll) && removeFromOtherQ[poll] != 0) { // 다른 큐에서 제거된 값인 경우
            removeFromOtherQ[poll] = removeFromOtherQ[poll]!! - 1
        } else { // 제거할 수 있는 값인 경우
            removeFromMyQ[poll] = if (removeFromMyQ.containsKey(poll)) { removeFromMyQ[poll]!! + 1 } else { 1 }
            return poll
        }
    }
    return null
}