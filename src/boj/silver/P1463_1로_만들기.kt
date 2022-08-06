package boj.silver

import java.util.*
import kotlin.math.min

/**
 * S3 #DP
 *
 * 재귀함수는 위에서부터 내려왔다가 다시 올라가는 구조
 * 입력이 1_000_000일 경우 stackoverflow
 * 반복문은 아래에서 위로 올라가는 구조
 * 쌓이지 않고 바로바로 계산하므로 터지지 않는다
 *
 * 1_000_000으로 오는 경로는 다양하다
 * 어떤 경로로 왔을 때 최소인지 알 수 없고,
 * 최초로 1_000_000에 도착했다고 해서 최소라는 보장도 없으므로
 * 1_000_000의 모든 경로에 대해 계산이 끝날 때까지 기다려야 한다
 *
 */

fun main() = with(Scanner(System.`in`)) {
    val num = nextInt()
    val cache = Array(num + 1) { 1_000_000 }
    cache[1] = 0
    var start = 1
    while (start <= num) {
        if (start * 3 <= num) {
            cache[start * 3] = min(cache[start] + 1, cache[start * 3])
        }
        if (start * 2 <= num) {
            cache[start * 2] = min(cache[start] + 1, cache[start * 2])
        }
        if (start + 1 <= num) {
            cache[start + 1] = min(cache[start] + 1, cache[start + 1])
        }
        start++
    }

    println(cache[num])
}

// stackoverflow
fun func2(cache: Array<Int>, num: Int, start: Int) {
    if (start > num) {
        return
    }

    if (start * 3 <= num) {
        cache[start * 3] = min(cache[start] + 1, cache[start * 3])
        func2(cache, num, start * 3)
    }
    if (start * 2 <= num) {
        cache[start * 2] = min(cache[start] + 1, cache[start * 2])
        func2(cache, num, start * 2)
    }
    if (start + 1 <= num) {
        cache[start + 1] = min(cache[start] + 1, cache[start + 1])
        func2(cache, num, start + 1)
    }
}

fun func(cache: Array<Int>, num: Int, count: Int): Int {
    if (cache[num] != 1_000_000) {
        return cache[num]
    } else if (num == 1) {
        cache[num] = 0
        return cache[num]
    } else if (num <= 3) {
        cache[num] = 1
        return cache[num]
    } else {
        var min = func(cache, num - 1, count + 1)
        if (num % 2 == 0) {
            min = min(min, func(cache, num / 2, count + 1))
        }
        if (num % 3 == 0) {
            min = min(min, func(cache, num / 3, count + 1))
        }
        cache[num] = min(min + 1, cache[num])
        return cache[num]
    }
}