package boj.silver

import java.util.*

var count = 0

/**
 * S1 #Recursion
 *
 * 하노이 탑에 대한 배경지식이 없더라도 주어진 조건을 가지고 테스트 케이스를 충분히 가지고 놀다보면 힌트를 얻을 수 있다
 * (규칙 자체만 놓고 보면 재귀가 드러나지 않으나 테스트 케이스를 가지고 놀다보면 재귀적인 구조를 발견할 수 있다)
 */

fun main() = with(Scanner(System.`in`)) {
    val num = nextInt()
    val stringBuilder = StringBuilder()
    func(num, stringBuilder, 1, 3)
    println(count)
    print(stringBuilder.toString())
}

fun func(num: Int, stringBuilder: StringBuilder, from: Int, to: Int) {
    val empty = 6 - from - to
    if (num == 1) {
        stringBuilder.append("$from $to\n")
        count += 1
    } else {
        func(num - 1, stringBuilder, from, empty)
        stringBuilder.append("$from $to\n")
        count += 1
        func(num - 1, stringBuilder, empty, to)
    }
}