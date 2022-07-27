package programmers.level1

import kotlin.math.abs

fun solution(numbers: IntArray, hand: String): String {
    var leftHandLoc = 3 to 0
    var rightHandLoc = 3 to 2
    val left = intArrayOf (1, 4, 7)
    val right = intArrayOf (3, 6, 9)
    val stringBuilder = StringBuilder()

    for (num in numbers) {
        val padLoc = if (num == 0) { 3 to 1 } else { (num-1) / 3 to (num-1) % 3 }

        var isLeftHand = true
        if (right.contains(num)) {
            isLeftHand = false
        } else if (!left.contains(num)) { // middle
            val leftDist = calcDist(padLoc, leftHandLoc)
            val rightDist = calcDist(padLoc, rightHandLoc)
            if (leftDist > rightDist || (leftDist == rightDist && hand == "right")) {
                isLeftHand = false
            }
        }

        if (isLeftHand) {
            stringBuilder.append("L")
            leftHandLoc = padLoc
        } else {
            stringBuilder.append("R")
            rightHandLoc = padLoc
        }
    }

    return stringBuilder.toString()
}

fun calcDist(loc1: Pair<Int, Int>, loc2: Pair<Int, Int>): Int {
    return abs(loc1.first - loc2.first) + abs(loc1.second - loc2.second)
}