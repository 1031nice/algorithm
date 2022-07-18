package leetcode.easy

/**
 * 문자열 s가 주어질 때 최대 한 개의 문자를 제거하여 palindrome을 만들 수 있다면 true 반환
 *
 * ===
 *
 * 하나를 제거해도 palindrome이려면 하나가 등장할 때까지도 palindrome이어야 하고,
 * 하나가 등장한 이후부터도 palindrome이어야 한다.
 */

fun validPalindrome(s: String): Boolean {
    (0 until s.length/2).forEach { i ->
        if (s[i] != s[s.lastIndex - i]) {
            return isPalindrome(s.substring(i until s.lastIndex-i))
                    || isPalindrome(s.substring(i+1..s.lastIndex-i))
        }
    }
    return true
}

fun isPalindrome(s: String): Boolean {
    return (0 until s.length/2).none { s[it] != s[s.lastIndex - it] }
}