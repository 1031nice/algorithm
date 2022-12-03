package boj.gold

import java.util.*
import kotlin.math.abs
import kotlin.math.min

/**
 * G5 #BruteForce
 * 정확한 번호를 누르는 게 최선이 아닐 수 있다는건 파악했고,
 * 가장 그 정확한 번호랑 그 번호로부터 가까운 위 아래랑 세개만 검사하면 되지 않을까 했으나
 * 이렇게 3개만 봐서는 답을 얻을 수 없는 반례가 존재했다.
 * ex) 가장 가까운 세개만 본다는 접근으로 문제를 풀면
 * 1666이 주어지고 0, 1, 9를 못누르는 경우
 * 2222를 선택해야 하는데, 두번째부터 6을 고를 수 있으니 2666을 고르려고 함
 *
 * 결론, 다봐야 한다.
 * (naive하게 구현하면 시간 초과가 날 수 있는데 간단하게 minClick이라는 장치를 추가하여 해결했다)
 *
 * 또한 숫자가 4자리인 경우 4자리만 검사하면 답을 구할 수 있을 것이라고 생각했고,
 * 4자리가 완성 되어야만 계산을 시작했는데
 * 숫자가 4자리여도 3자리에서 출발하는 게 가장 적은 클릭인 케이스도 가능하고,
 * 5자리에서 출발하는 게 가장 적은 클릭인 케이스도 가능했다.
 */

fun main() = with(Scanner(System.`in`)) {
    val target = next()
    val buttons = Array(10) { true }
    repeat(nextInt()) {
        buttons[nextInt()] = false
    }

    val min = min(abs(target.toInt() - 100), func(buttons, target, "", 0, 0))
    print(min)
}

var minClick = 500_000

fun func(buttons: Array<Boolean>, target: String, nowString: String, index: Int, click: Int): Int {
    if (index > target.length + 1 || click >= minClick) {
        return 500_000
    }

    var ret = if (nowString == target) {
        click
    } else if (nowString.isNotBlank()) {
        click + abs(target.toInt() - nowString.toInt())
    } else {
        500_000
    }
    minClick = min(minClick, ret)

    for (i in 0 until 10) {
        if (buttons[i]) {
            ret = min(ret, func(buttons, target, nowString + i, index + 1, click + 1))
        }
    }


    // 가장 가까운 세 개의 숫자만 검사하려고 시도
    /*var nextNum = -1
    for (i in nowDigit + 1 until nowDigit + 1 + 10) { // 위와 아래는 연결되어 있다
        val newI = i % 10
        if (buttons[newI]) {
            nextNum = newI
            break
        }
    }
    ret = min(ret, func(buttons, target, nowString + nextNum, index + 1, click + 1))

    var prevNum = -1
    for (i in nowDigit - 1 downTo nowDigit - 10) {
        val newI = (i + 10) % 10
        if (buttons[newI]) {
            prevNum = newI
            break
        }
    }
    ret = min(ret, func(buttons, target, nowString + prevNum, index + 1, click + 1))*/

    return ret
}