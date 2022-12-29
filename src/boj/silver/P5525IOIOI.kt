package boj.silver

import java.util.*
import kotlin.math.max

/**
 * S1 #String
 */

fun main() = with(Scanner(System.`in`)) {
    val targetLength = 2 * nextInt() + 1
    nextInt() // 사용하지 않는 입력
    val string = next()

    val list = arrayListOf<Int>()
    var lastChar = ' '
    var searchLength = 0
    for (ch in string) {
        if (isProceeding(searchLength)) {
            if (lastChar != ch) { // 진행가능
                searchLength++
            } else { // 진행 불가능
                addSearchLength(list, ch, searchLength)
                searchLength = if (ch == 'I') 1 else 0 // I인 경우 바로 진행
            }
        } else {
            if (ch == 'I') { // 진행
                searchLength++
            }
        }
        lastChar = ch
    }

    // 마지막까지 잘 진행되어서 계산되지 않은 케이스 고려
    addSearchLength(list, string[string.lastIndex], searchLength)

    println(list.sumOf { max((it - targetLength) / 2 + 1, 0) })
}

fun addSearchLength(list: ArrayList<Int>, endChar: Char, searchLength: Int) {
    if (endChar == 'I') { // I가 연속해서 진행 불가능
        list.add(searchLength)
    } else { // O가 연속해서 진행 불가능
        list.add(searchLength - 1) // OO로 끝났으므로 길이를 1 줄인다
    }
}

private fun isProceeding(searchLength: Int): Boolean {
    return searchLength > 0
}