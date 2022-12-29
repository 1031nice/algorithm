package boj.gold

import java.util.*

/**
 * G5
 */

fun main() = with(Scanner(System.`in`)) {
    repeat(nextInt()) {
        val commands = next()
        val length = nextInt()
        nextLine() // clear buffer
        val tempArray = nextLine()
        val array = tempArray.substring(1, tempArray.lastIndex).split(",")

        var isFront = true
        var start = 0
        var end = length

        for (ch in commands) {
            if (ch == 'R') {
                isFront = isFront xor true
            } else {
                if (isFront) {
                    start++
                } else {
                    end--
                }
            }
        }

        if (start > end) {
            println("error")
            return@repeat
        }

        var answer = array.subList(start, end)
        if (!isFront) {
            answer = answer.reversed()
        }
        println(answer.joinToString(",", "[", "]"))
    }
}