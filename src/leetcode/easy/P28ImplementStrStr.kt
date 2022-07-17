package leetcode.easy

/**
 * 문자열 haystack과 needle이 주어질 때
 * haystack 안에 needle이 존재하면 needle이 처음 등장한 인덱스를 반환
 * 존재하지 않을 경우 -1을 반환
 *
 * haystack: "hello", needle: "ll" => return 2
 * haystack: "aaaaa", needle: "bba" => return -1
 */
fun strStr(haystack: String, needle: String): Int {
    if (needle.length > haystack.length) {
        return -1
    }

    val lastIndex = haystack.length - needle.length
    for (i in 0..lastIndex) {
        var found = true
        for (j in 0..needle.lastIndex) {
            if (haystack[i + j] != needle[j]) {
                found = false
                break
            }
        }
        if (found) {
            return i
        }
    }

    return -1
}