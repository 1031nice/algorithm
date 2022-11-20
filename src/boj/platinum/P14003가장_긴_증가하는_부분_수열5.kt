package boj.platinum

import java.util.*

/**
 * P5 #LIS
 */
fun main() = with(Scanner(System.`in`)) {
    val size = nextInt()
    val nums = arrayListOf<Int>()
    repeat(size) {
        nums.add(nextInt())
    }

    val tails = arrayListOf<Int>()
    val trace = Array(size) { -1 }

    for (i in nums.indices) {
        var index = Collections.binarySearch(tails, i) { o1, o2 ->
            nums[o1].compareTo(nums[o2])
        }
        if (index < 0) {
            index = -index - 1
        }

        if (index == tails.size) {
            tails.add(i)
        } else {
            tails[index] = i
        }

        if (index != 0) {
            trace[i] = tails[index - 1]
        }
    }

    val ret = arrayListOf<Int>()
    ret.add(nums[tails[tails.lastIndex]])
    var next = trace[tails[tails.lastIndex]]
    while (next != -1) {
        ret.add(nums[next])
        next = trace[next]
    }

    println(ret.size)
    for (i in ret.lastIndex downTo 0) {
        print("${ret[i]} ")
    }
}
