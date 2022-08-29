package boj.gold

import java.util.*

/**
 * G5 #DFS
 *
 * 제거함에 따라 리프 노드가 생길 수 있으므로 단순히 뺄셈을 해선 안된다
 * 트리 역시 그래프이므로 인접리스트 또는 인접행렬로 나타낼 수 있다
 */

fun main() = with(Scanner(System.`in`)) {
    val nNode = nextInt()
    val matrix = Array(nNode) { Array(nNode) { 0 } }
    val parents = Array(nNode) { 0 }
    var root = -1
    repeat(nNode) { child ->
        parents[child] = nextInt()
        if (parents[child] == -1) {
            root = child
        } else {
            matrix[parents[child]][child] = 1
        }
    }

    val leaf = Array(nNode) { 0 }
    val target = nextInt()
    if (target == root) {
        print(0)
        return
    }
    removeDfs(matrix, leaf, parents[target], target)
    print(countDfs(matrix, leaf, root))
}

fun removeDfs(matrix: Array<Array<Int>>, leaf: Array<Int>, parent: Int, target: Int) {
    for (i in 0 until matrix[target].size) {
        if (matrix[target][i] > 0) {
            removeDfs(matrix, leaf, target, i)
            matrix[target][i] = 0
        }
    }
    matrix[parent][target] = 0
}

fun countDfs(matrix: Array<Array<Int>>, leaf: Array<Int>, start: Int): Int {
    var ret = 0
    for (i in 0 until matrix[start].size) {
        if (matrix[start][i] > 0) {
            ret += countDfs(matrix, leaf, i)
        }
    }

    leaf[start] = if (ret == 0) 1 else ret
    return leaf[start]
}