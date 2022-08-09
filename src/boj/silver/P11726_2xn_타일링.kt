package boj.silver

import java.util.*

fun main() = with(Scanner(System.`in`)) {
    val num = nextInt()
    if (num <= 2) {
        print(num)
        return
    }

    var n1 = 1
    var n2 = 2
    repeat(num - 2) {
        val temp = n1
        n1 = n2
        n2 = (n2 + temp) % 10_007
    }

    print(n2)
}