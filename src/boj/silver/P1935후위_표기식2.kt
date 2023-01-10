package boj.silver

import java.util.*

/**
 * S3 #Stack
 */
fun main() = with(Scanner(System.`in`)) {
    val operands = nextInt()
    val expression = next()
    val values = Array(26) { 0 }

    repeat(operands) {
        values[it] = nextInt()
    }

    val stack = Stack<Double>()

    for (ch in expression) {
        if (ch.isLetter()) {
            stack.push(values[ch - 'A'].toDouble())
        } else {
            val operand2 = stack.pop()
            val operand1 = stack.pop()
            when (ch) {
                '+' -> stack.push(operand1 + operand2)
                '-' -> stack.push(operand1 - operand2)
                '*' -> stack.push(operand1 * operand2)
                '/' -> stack.push(operand1 / operand2)
            }
        }
    }

    println(String.format("%.2f", stack.pop()))
}