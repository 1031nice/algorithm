package boj.gold

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

/**
 * G4 #MST
 */

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
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
    while (pq.isNotEmpty()) {
        val (edge, weight) = pq.poll()
        val (n1, n2) = edge
        if (find(parents, n1) == find(parents, n2)) { // cycle check
            continue
        }

        sum += weight
        val size = union(parents, n1, n2)
        if (size == nNode) {
            break
        }
    }

    print(sum)
}

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