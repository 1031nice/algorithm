package boj.silver

import java.util.*

/**
 * S2 #DivideAndConquer
 *
 * 어떤 값이 답일지는 모르겠으나 1부터 {가지고 있는 랜선길이의 합 / 원하는 랜선의 개수}까지 검사하면 그 중에 답이 있다고 확신
 * 랜선의 길이는 정수 최댓값까지 가능하므로 1씩 검사하면 반복문 21억회
 * 이진 탐색으로 탐색 공간 줄이기 => 어떤 값을 검사했을 때 N개 이상의 랜선이 만들어지면 그 값보다 더 작은 값은 검사할 필요 없음(최대 길이를 원하므로)
 * 조건에 맞는 값을 찾는 것이 아닌 최댓값을 찾는 것이므로 start > end일 때까지 반복
 *
 * N개 이상의 랜선이 만들어져도 된다는 힌트를 캐치 못해서 틀리고
 * 오버플로우 처음부터 신경쓴다고 썼는데 틀리고
 */

var answer = 0L

fun main() = with(Scanner(System.`in`)) {
    val k = nextInt()
    val n = nextInt()
    val wires = Array(k) { 0 }
    var sum = 0L
    (0 until k).forEach {
        wires[it] = nextInt()
        sum += wires[it]
    }

    val max = sum / n
    divideAndConquer(wires, 1, max, n)
    print(answer)
}

fun divideAndConquer(wires: Array<Int>, start: Long, end: Long, n: Int) {
    if (start > end) {
        return
    }

    val mid = (start + end) / 2
    val count = wires.sumOf { it / mid.toInt() }

    if (count >= n) {
        answer = mid
        divideAndConquer(wires, mid + 1, end, n)
    } else {
        divideAndConquer(wires, start, mid - 1, n)
    }
}
