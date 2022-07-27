package boj.silver

import java.util.*

/**
 * S4
 * 단순하게 구현할 경우 시간 초과
 */

fun main() = with(Scanner(System.`in`)) {
    val num = nextInt()
    var cards = arrayListOf<Int>()
    cards.addAll(1..num)

    while(cards.size != 1) {
        val newCards = arrayListOf<Int>()
        if (cards.size % 2 == 1) {
            newCards.add(cards[cards.lastIndex])
        }
        (2..cards.size step 2).forEach {
            newCards.add(cards[it-1])
        }
        cards = newCards
    }

    print(cards[0])
}