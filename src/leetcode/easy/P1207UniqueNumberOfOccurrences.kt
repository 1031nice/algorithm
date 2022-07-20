package leetcode.easy

/**
 * 정수 배열이 주어질 때, 각 정수의 등장 횟수가 유일하다면 true 반환
 *
 * arr: [1,2,2,1,1,3] => true
 * arr: [1,2] => false
 *
 * ===
 *
 * 등장 횟수를 세는 방법
 * 1. 배열 이용(숫자의 범위에 맞는 크기의 배열을 생성한 뒤 값과 인덱스를 대응시켜 저장)
 * 2. Map 이용
 */

// O(N), Map 이용
fun uniqueOccurrences(arr: IntArray): Boolean {
    val valueToOccurrence = mutableMapOf<Int, Int>()

    arr.forEach {
        valueToOccurrence[it] = (valueToOccurrence[it] ?: 0) + 1
    }

    return valueToOccurrence.values.toSet().size == valueToOccurrence.size
}

// O(N), 배열 이용
fun uniqueOccurrences1(arr: IntArray): Boolean {
    val occurrences = Array(2001) { 0 }

    arr.forEach {
        occurrences[it + 1000]++
    }

    return occurrences.count { it != 0 } == occurrences.filter { it != 0 }.toSet().size
}

// O(NlogN)
fun uniqueOccurrences2(arr: IntArray): Boolean {
    val set = mutableSetOf<Int>()
    arr.sort()

    var before = arr[0]
    var count = 1
    for (i in 1..arr.lastIndex) {
        val now = arr[i]
        if (before != now) {
            if (set.contains(count)) {
                return false
            } else {
                set.add(count)
                count = 1
            }
        } else {
            count++
        }
        before = now
    }

    return !set.contains(count)
}