package boj.silver

import java.util.*

/**
 * S5 #BruteForce
 *
 * 100만까지 1씩 증가하며 666이 포함되는지 찾으려고 했으나 O(N^2)
 *
 * 규칙을 찾아서 N번째 숫자를 바로 찾으려고 했으나 규칙이 안보임(존재하는지 확신도 못함)
 *
 * 666이 포함된 수 만개를 만드는 방법으로 해결(시간이 많이 필요할 줄 알았으나 막상 계산해보니 충분히 가능한 복잡도(O(N)))
 * 언제 666이 N개가 되는지 계산하지 않고 안전하게 0부터 N까지 순회하며 모든 숫자에 666 끼워넣음
 * 6660 때문에 0일 때도 고려해야 한다는 건 알았으나 following zero를 고려해야 하는지 모르고 헤맴
 * 66600까지 해봤다면 알아챘겠지만 66600까지는 할 필요를 못느껴서 해보지 않았고 결국 following zero를 고려하지 못함
 * (디버깅으로 following zero도 고려해야 함을 발견)
 * 이 문제에서처럼 테스트케이스를 통해 발견하기 어려운 경우도 있을테니
 * 이런 유형의 문제에서 고려해야할 사항 중 하나로 following zero를 아예 외워두는 게 나을듯
 */

fun main() = with(Scanner(System.`in`)) {
    val nums = sortedSetOf<Int>()
    (0..9999).forEach {
        var string = it.toString()
        repeat(5 - string.length) {
            string = "0$string"
        }
        generate666(nums, string)
    }
    val list = nums.toList()
    print(list[nextInt() - 1])
}

fun generate666(nums: SortedSet<Int>, string: String) {
    for (i in 0..string.length) {
        nums.add("${string.substring(0, i)}666${string.substring(i)}".toInt())
    }
}