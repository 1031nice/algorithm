package boj.gold

import java.util.*
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * G4 #MST
 */

fun main() = with(Scanner(System.`in`)) {
    val solution = P4386별자리_만들기()

    val nNode = nextInt()
    val nodes = arrayListOf<Pair<Float, Float>>()
    val parents = Array(nNode) { -1 }
    repeat(nNode) {
        nodes.add(nextFloat() to nextFloat())
    }

    val pq = PriorityQueue<Pair<Pair<Int, Int>, Float>> { p1, p2 -> p1.second.compareTo(p2.second) }
    for (i in nodes.indices) {
        for (j in i + 1 until nodes.size) {
            pq.add((i to j) to getDist(nodes[i], nodes[j]))
        }
    }

    var sum = 0.0f

    while (pq.isNotEmpty()) {
        val (point, dist) = pq.poll()
        val (n1, n2) = point
        if (solution.find(parents, n1) == solution.find(parents, n2)) { // cycle check
            continue
        }

        sum += dist
        val size = solution.union(parents, n1, n2)
        if (size == nNode) {
            break
        }
    }

    print("%.2f".format(sum))
}

fun getDist(p1: Pair<Float, Float>, p2: Pair<Float, Float>): Float {
    return sqrt((p1.first - p2.first).pow(2) + (p1.second - p2.second).pow(2))
}

class P4386별자리_만들기 {
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