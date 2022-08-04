package boj.silver

import java.util.*

/**
 * S2 #Greedy
 */

fun main() = with(Scanner(System.`in`)) {
    val expression = next()
    val indexOfMinusList = arrayListOf<Int>()
    expression.withIndex().forEach { if (it.value == '-') indexOfMinusList.add(it.index) }
    val expressionList = arrayListOf<String>()

    if (indexOfMinusList.isEmpty()) {
        print(calc(arrayListOf(expression)))
    } else {
        val first = expression.substring(0, indexOfMinusList.getOrElse(0) { 0 })
        val last = expression.substring(indexOfMinusList.getOrElse(indexOfMinusList.lastIndex) { expression.lastIndex } + 1, expression.length)

        for (i in 0 until indexOfMinusList.size - 1) {
            expressionList.add(expression.substring(indexOfMinusList[i] + 1, indexOfMinusList[i + 1]))
        }

        expressionList.add(0, first)
        expressionList.add(expressionList.size, last)

        print(calc(expressionList))
    }
}

fun calc(expressionList: ArrayList<String>): Int {
    var result = -987654321
    for (expression in expressionList) {
        val nums = expression.split("+")
        val sum = nums.sumOf { it.toInt() }
        if (result == -987654321) {
            result = sum
        } else {
            result -= sum
        }
    }
    return result
}
