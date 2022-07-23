package leetcode.easy

/**
 * command: g()al => output: goal
 * command: (goal) => output: goal
 */

fun interpret(command: String): String {
    var answer = ""
    var isEmpty = false

    for (ch in command) {
        if (ch == '(') {
            isEmpty = true
        } else if(ch == ')') {
            if (isEmpty) {
                answer += 'o'
            }
        } else {
            answer += ch
            isEmpty = false
        }
    }

    return answer
}