package leetcode.medium

import kotlin.math.min

/**
 * 같은 숫자 카드를 얻기 위해 집어야 하는 연속된 숫자 카드들의 길이 최솟값 반환
 * 만약 카드뭉치 속에 같은 숫자 카드가 존재하지 않는다면 -1 반환
 * cards: [3, 4, 2, 3, 4, 7] => 4 (숫자카드 3의 쌍을 얻기 위해 [3, 4, 2, 3] 또는 숫자카드 4의 쌍을 얻기 위해 [4, 2, 3, 4])
 * cards: [1, 0, 5, 3] => -1
 *
 * ===
 *
 * 연속된 숫자 카드들의 길이가 최소가 되어야 하므로 항상 가장 마지막에 등장한 카드까지의 거리만 계산한 뒤 그 중 가장 작은 값을 반환하면 된다
 */

fun minimumCardPickup(cards: IntArray): Int {
    var minLength = 100_001
    // 가장 마지막에 등장한 카드의 위치를 기억하기 위해 Map 사용
    val cardToIndex = hashMapOf<Int, Int>()

    for ((index, card) in cards.withIndex()) {
        if (cardToIndex.containsKey(card)) {
            minLength = min(minLength, index - cardToIndex[card]!! + 1)
        }
        // Map은 언제나 마지막에 등장한 카드의 위치를 저장하고 있도록 덮어쓰기
        cardToIndex[card] = index
    }

    return if (minLength == 100_001) -1 else minLength
}