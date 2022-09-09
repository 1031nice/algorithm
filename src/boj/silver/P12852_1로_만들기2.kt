package boj.silver

import java.util.*

/**
 * S1 #DP
 *
 * 최단 경로를 얻는 방법은 재귀함수라면
 * 최단 경로를 가리킬 변수를 하나 만들어 두고
 * 타겟에 도달할 때마다 최단 경로와 현재 만들어진 경로를 비교해서
 * 최단 경로를 업데이트한다면 모든 과정이 끝나고 최단 경로를 얻을 수 있다
 *
 * 하지만 재귀함수 풀이는 메모리 초과 -> 반복문으로 접근
 * 반복문에서는 어떻게 최단 경로를 구성하는 정수들을 구할 수 있을까
 * 재귀함수의 경우 리스트를 인자로 넘기면서 경로에 추가 또는 제거하기가 쉽지만
 * 반복문에서는 반복마다 필요한 리스트의 수가 3배씩 증가
 *
 * 한 번에 얻으려고 하지 말고 어떤 정수 k에서 바로 직전 정수(k까지 최단 경로로 도달한)를 기억하게 한 뒤,
 * 모든 과정을 마치고 타겟 정수에서부터 1이 될 때까지 타고 타고 가면 1부터 타겟 정수까지의 최단 경로 획득 가능
 */

fun main() = with(Scanner(System.`in`)) {
    val num = nextInt()
    val cache = Array(num + 1) { 1_000_000 to 0 } // count to before num
    cache[1] = 0 to -1
    var now = 1
    while (now <= num) {
        for (next in getNext(now)) {
            if (next <= num) {
                cache[next] = if (cache[now].first + 1 < cache[next].first) {
                    cache[now].first + 1 to now
                } else {
                    cache[next]
                }
            }
        }
        now++
    }

    var iter = cache[num]
    println(iter.first)
    print("$num ")
    while (iter.second >= 0) {
        print("${iter.second} ")
        iter = cache[iter.second]
    }
}

fun getNext(num: Int): IntArray {
    return intArrayOf(num + 1, num * 2, num * 3)
}