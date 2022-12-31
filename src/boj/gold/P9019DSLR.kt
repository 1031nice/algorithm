package boj.gold

import java.util.*

/**
 * G4 #BFS
 */

fun main() = with(Scanner(System.`in`)){
    val dir = listOf('D', 'S', 'L', 'R')

    repeat(nextInt()) {
        val input = nextInt()
        val target = nextInt()
        val queue = LinkedList<Pair<Int, String>>()
        val visited = Array(10001) { false }

        repeat(dir.size) { queue.add(input to "") }

        while (queue.isNotEmpty()) {
            val (number, command) = queue.poll()
            if (number == target) {
                println(command)
                break
            }

            if (visited[number]) {
                continue
            }
            visited[number] = true

            var newNumber = (number * 2) % 10000
            if (!visited[newNumber]) {
                queue.add(newNumber to command + 'D')
            }
            newNumber = if (number == 0) 9999 else number - 1
            if (!visited[newNumber]) {
                queue.add(newNumber to command + 'S')
            }
            newNumber = leftRotate(number)
            if (!visited[newNumber]) {
                queue.add(newNumber to command + 'L')
            }
            newNumber = rightRotate(number)
            if (!visited[newNumber]) {
                queue.add(newNumber to command + 'R')
            }
        }
    }
}

fun rightRotate(number: Int): Int {
    val highest = number % 10
    val removeLastNumber = number / 10
    return highest * 1000 + removeLastNumber
}

fun leftRotate(number: Int): Int {
    val lowest = number / 1000
    val removeFirstNumber = number - lowest * 1000
    return removeFirstNumber * 10 + lowest
}
