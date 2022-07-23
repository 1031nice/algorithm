package leetcode.medium

/**
 * Return the lexicographically smallest string with length equal to n and numeric value equal to k.
 * n : 3, k : 27 => output: "aay"
 *
 * ===
 *
 * lexicographically smallest가 되기 위해서는 가장 왼쪽에 등장하는 알파벳이 작아야 한다
 * 길이가 n으로 정해져 있으므로 무작정 a로 채울 수는 없다
 * => 어떨 때 a로 채울 수 있을까
 * => 이는 k가 결정, 지금 자리를 a로 채웠을 때 이후 오른쪽 자리로 k를 모두 표현할 수 있다면 지금 자리를 a로 채울 수 있다
 * => k를 표현할 수 있는지 여부는 k와 한 자리에서 표현할 수 있는 값 x 남은 자릿수를 비교함으로써 알 수 있다
 *
 */

fun getSmallestString(n: Int, k: Int): String {
    val MAX_COVER = 26
    var left = k
    val sb = StringBuilder()

    for (i in 1..n) {
        val minCover = left - (n - i) * MAX_COVER
        left -= if (minCover > 0) {
            sb.append('a' + minCover - 1)
            minCover
        } else {
            sb.append('a')
            1
        }
    }

    return sb.toString()
}