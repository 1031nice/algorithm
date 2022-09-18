package boj.gold

import java.util.*

/**
 * G3
 *
 * 오버플로우는 항상 조심해야 한다
 * 숫자가 좀 크거나 숫자가 작더라도 곱하기가 있거나 많은 덧셈이 있는 경우 각별한 주의가 필요하다
 */

fun main() = with(Scanner(System.`in`)) {
    val target = nextInt()

    val arr1 = Array(nextInt()) { 0 }
    val valueToCount1 = getValueToCountMap(arr1)

    val arr2 = Array(nextInt()) { 0 }
    val valueToCount2 = getValueToCountMap(arr2)

    var sum = 0L
    valueToCount1.forEach { (num, count) ->
        if (valueToCount2.containsKey(target - num)) {
            sum += count * valueToCount2[target - num]!!
        }
    }

    print(sum)
}

private fun Scanner.getValueToCountMap(arr: Array<Int>): HashMap<Int, Long> {
    repeat(arr.size) {
        arr[it] = nextInt()
    }
    val valueToCount1 = hashMapOf<Int, Long>()
    for (i in arr.indices) {
        var sum = 0
        for (j in i until arr.size) {
            sum += arr[j]
            valueToCount1[sum] = valueToCount1.getOrDefault(sum, 0) + 1
        }
    }
    return valueToCount1
}