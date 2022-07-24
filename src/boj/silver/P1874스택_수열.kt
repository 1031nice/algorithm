package boj.silver

import java.util.*


/**
 * S3 #Stack
 *
 * push, pop 중 어떤 연산을 해야 하는지 파악해야 한다
 * 현재 수열의 숫자와 스택의 top 숫자의 비교를 통해 판단할 수 있다
 */
fun main() = with(Scanner(System.`in`)) {
    val len = nextInt()
    var number = 1
    val stack = Stack<Int>()
    val sb = StringBuilder()
    var cnt = 0
    var input = -1

    while(cnt < len) {
        if (input == -1) {
            input = nextInt()
            cnt++
        }

        if (stack.isEmpty() || stack.peek() < input) {
            stack.push(number++)
            sb.append("+\n")
        } else if (stack.pop() == input) {
            sb.append("-\n")
            input = -1
        } else {
            print("NO")
            return
        }
    }

    var ret = "NO"
    if (stack.isEmpty()) {
        ret = sb.toString()
    } else if (stack.peek() == input) {
        sb.append("-\n")
        ret = sb.toString()
    }

    print(ret)
}