package boj.gold

import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * G4 #UnionFind
 */

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val solution = P20040사이클_게임()

    var split = readLine().split(" ")
    val nNode = split[0].toInt()
    val nEdge = split[1].toInt()
    val parents = Array(nNode) { -1 }

    (0 until nEdge).forEach {
        split = readLine().split(" ")
        val n1 = split[0].toInt()
        val n2 = split[1].toInt()
        if (solution.find(parents, n1) == solution.find(parents, n2)) {
            print(it + 1)
            return
        }
        solution.union(parents, n1, n2)
    }

    print(0)
}

class P20040사이클_게임 {
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