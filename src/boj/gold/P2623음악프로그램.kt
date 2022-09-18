package boj.gold

import java.util.*

var cnt2623 = 0

/**
 * G3 #TopologicalSort
 *
 * 위상정렬은 DFS를 통해 풀 수도 있고, 아래 풀이와 같이 DFS를 이용하지 않고도 풀이가 가능하다
 *
 * 생각해볼 점 1. O(N+M)이 가능하다고 하는데 이 풀이는 O(NM)
 *      어떻게 해야 ingoing edge가 0인 노드를 바로 찾을 수 있을까
 *      PQ를 생각했지만 PQ에 이미 들어가 있는 객체의 값을 수정한다고 그값이 바로 반영되는 것은 아니지 않나?
 *      아니면 값이 수정될 때마다 PQ poll -> add를 통해 PQ안에서 재정렬이 이루어지도록 해주면 되려나?
 * 생각해볼 점 2. DFS 풀이인 경우에도 위상정렬이 불가능한 경우를 찾을 수 있는지, 어떻게 찾는지 확인
 */

fun main() = with(Scanner(System.`in`)) {
    val nNode = nextInt()
    val matrix = Array(nNode) { arrayListOf<Int>() }
    val ingoingEdges = Array(nNode) { 0 }

    repeat(nextInt()) {
        val len = nextInt()
        var now = nextInt() - 1
        for (i in 1 until len) {
            val next = nextInt() - 1
            matrix[now].add(next)
            ingoingEdges[next]++
            now = next
        }
    }

    val visited = Array(nNode) { false }
    val result = LinkedList<Int>()
    while (true) {
        if (cnt2623 == nNode) {
            break
        }

        val (min, minIndex) = getMinToMinIndex(ingoingEdges, visited)
        if (min != 0) {
            println(0)
            return
        }

        matrix[minIndex].forEach { ingoingEdges[it]-- }
        visited[minIndex] = true
        cnt2623++

        result.addLast(minIndex + 1)
    }
    result.forEach { println("$it") }
}

// O(N)
private fun getMinToMinIndex(ingoingEdges: Array<Int>, visited: Array<Boolean>): Pair<Int, Int> {
    var min = 1_001
    var minIndex = -1
    for ((i, v) in ingoingEdges.withIndex()) {
        if (visited[i]) {
            continue
        }
        if (min > v) {
            min = v
            minIndex = i
        }
    }
    return Pair(min, minIndex)
}