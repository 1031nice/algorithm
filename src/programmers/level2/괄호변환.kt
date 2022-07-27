package programmers.level2

import java.util.*

/**
 * #Recursion
 */
class Solution {
    fun solution(p: String): String {
        if (p.isEmpty()) {
            return ""
        }

        var opens = 0
        var closes = 0
        var u = ""
        var v = ""
        for((i, ch) in p.withIndex()) {
            if (ch == '(') {
                opens++
            } else {
                closes++
            }

            if (opens == closes) {
                u = p.substring(0, i+1)
                v = p.substring(i+1, p.length)
                break
            }
        }

        return if (isValid(u)) {
            "$u${solution(v)}"
        } else {
            "(${solution(v)})${invert(u.substring(1, u.lastIndex))}"
        }
    }

    fun invert(string: String): String {
        return buildString {
            for (ch in string) {
                if (ch == '(') {
                    append(')')
                } else {
                    append('(')
                }
            }
        }
    }

    fun isValid(string: String): Boolean {
        val stack = Stack<Char>()
        for (ch in string) {
            if (ch == '(') {
                stack.push(ch)
            } else {
                if (stack.isEmpty()) {
                    return false
                }
                stack.pop()
            }
        }

        return stack.isEmpty()
    }
}