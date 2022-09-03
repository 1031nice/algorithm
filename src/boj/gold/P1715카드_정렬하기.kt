package boj.gold

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

/**
 * G4 #Greedy #PriorityQueue
 *
 * 모든 원소가 더해져야 하는 것은 같다
 * 하지만 더하는 순서에 따라 누적되는 값이 달라진다
 *
 * 1, 2, 3이 있을 때 <<1,2>, 3> <<2,3>, 1> <<1,3>, 2> 어떤 순서로 하든 1, 2, 3이 결국 모두 더해지지만,
 * 첫 비교에 각각 1+2, 2+3, 1+3이 누적된다
 *
 * 누적되는 값을 최소화하면 되는데,
 * 누적되는 값은 결국 두 원소의 합이므로 가장 작은 두 원소를 고르면 된다
 */

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val pq = PriorityQueue<Int>()
    repeat(readLine().toInt()) {
        pq.add(readLine().toInt())
    }

    var sum = 0
    while (pq.size != 1) {
        val sumOfTwo = pq.poll() + pq.poll()
        sum += sumOfTwo
        pq.add(sumOfTwo)
    }

    print(sum)
}