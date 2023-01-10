package boj.gold

import java.util.*

/**
 * G2 #Stack
 */
fun main() = with(Scanner(System.`in`)) {
    val expression = next()
    val stack = Stack<Char>()

    val operatorToPriority = mapOf(
            '(' to 0,
            '+' to 1,
            '-' to 1,
            '*' to 2,
            '/' to 2,
    )

    val stringBuilder = StringBuilder()
    for (ch in expression) {
        if (ch.isLetter()) {
            stringBuilder.append(ch)
        } else {
            if (stack.isEmpty() || ch == '(') {
                stack.push(ch)
            } else if (ch == ')') {
                var top = stack.pop()
                while (top != '(') {
                    stringBuilder.append(top)
                    top = stack.pop()
                }
            } else {
                while (stack.isNotEmpty()) {
                    val top = stack.peek()
                    if (operatorToPriority[top]!! >= operatorToPriority[ch]!!) {
                        stringBuilder.append(stack.pop())
                    } else {
                        break
                    }
                }
                stack.push(ch)
            }
        }
    }
    while (stack.isNotEmpty()) {
        stringBuilder.append(stack.pop())
    }

    println(stringBuilder)
}