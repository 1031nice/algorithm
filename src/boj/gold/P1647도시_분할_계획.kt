package boj.gold

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.math.max

/**
 * G4 #MST
 *
 * MST를 구성하는 edge 중 단 하나라도 빠지게 되면 더이상 연결 그래프가 아니다
 * MST를 구성하는 edge 중 하나를 제거함으로써 얻어진 두 개의 연결 그래프는 각각 MST이다
 */

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val solution = P1647도시_분할_계획()

    var split = readLine().split(" ")
    val nNode = split[0].toInt()
    val nEdge = split[1].toInt()
    val parents = Array(nNode) { -1 }
    val pq = PriorityQueue<Pair<Pair<Int, Int>, Int>> { p1, p2 -> p1.second.compareTo(p2.second) }

    repeat(nEdge) {
        split = readLine().split(" ")
        pq.add((split[0].toInt() - 1 to split[1].toInt() - 1) to split[2].toInt())
    }

    var sum = 0
    var maxWeight = -1
    while (pq.isNotEmpty()) {
        val (edge, weight) = pq.poll()
        val (n1, n2) = edge
        if (solution.find(parents, n1) == solution.find(parents, n2)) { // cycle check
            continue
        }

        sum += weight
        maxWeight = max(maxWeight, weight)
        val size = solution.union(parents, n1, n2)
        if (size == nNode) {
            break
        }
    }

    print(sum - maxWeight)
}

class P1647도시_분할_계획 {
    fun union(parents: Array<Int>, n1: Int, n2: Int): Int {
        val p1 = find(parents, n1)
        val p2 = find(parents, n2)
        return if (p1 < p2) { // p1 is bigger than p2
            parents[p1] += parents[p2] // increase size
            parents[p2] = p1 // set p1 as p2's parent
            parents[p1] * -1
        } else {
            parents[p2] += parents[p1] // increase size
            parents[p1] = p2 // set p2 as p1's parent
            parents[p2] * -1
        }
    }

    fun find(parents: Array<Int>, index: Int): Int {
        return if (parents[index] < 0) {
            index
        } else {
            parents[index] = find(parents, parents[index])
            parents[index]
        }
    }
}