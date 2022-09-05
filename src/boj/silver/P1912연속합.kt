package boj.silver

import java.util.*
import kotlin.math.max

/**
 * S2
 *
 * #DivideAndConquer
 *
 * 이와 비슷한 문제를 풀어본 적이 있어서 그런지
 * 가운데를 포함하는 연속합의 최댓값을 구할 때
 * two pointers로 오른쪽과 왼쪽을 비교하며
 * 매번 최대를 고르는 greedy 접근이 정답일 거라고 생각.
 * 하지만 고르지 않는 게 최선일 수도 있기 때문에 greedy로 접근해서는 안됨
 * 섣부르게 짐작하지 말고 꼭 확인해봐야 함
 *
 * #DP
 * f(n) = max(f(n-1), N[n]을 반드시 포함하는 연속합의 최댓값)
 * N[n]을 반드시 포함하는 연속합의 최댓값을 구하려고 보니
 * N[n-1]을 반드시 포함하는 연속합의 최댓값을 구하면 쉽게 구할 수 있음
 * N[n-1]을 반드시 포함하는 연속합은 f(n-1)을 계산할 때 계산된 값이므로 저장해두었다가 사용 가능(캐싱)
 */

fun main() = with(Scanner(System.`in`)) {
    val size = nextInt()
    val nums = Array(size) { 0 }
    repeat(size) {
        nums[it] = nextInt()
    }

    var beforeMaxSum = nums[0]
    var beforeMaxSumIncludeLast = nums[0]
    for (i in 1 until size) {
        // S(i) = S(i)(includes i)
        var max = if (beforeMaxSumIncludeLast > 0 && nums[i] > 0) {
            beforeMaxSumIncludeLast + nums[i]
        } else {
            max(beforeMaxSumIncludeLast, nums[i])
        }

        // S(i) = max(S(i)(includes i), S(i-1))
        max = max(max, beforeMaxSum)

        // S(i)는 i+1에서 바라볼 때 beforeMaxSum에 해당
        beforeMaxSum = max

        // i+1에서 바라볼 때 S(i)는 S(i-1) > 0인 경우 S(i-1) + N[i], S(i-1) < 0인 경우 N[i]
        if (beforeMaxSumIncludeLast > 0) {
            beforeMaxSumIncludeLast += nums[i]
        } else {
            beforeMaxSumIncludeLast = nums[i]
        }
    }
    print(beforeMaxSum)
}

fun divideAndConquer(nums: Array<Int>, start: Int, end: Int): Int {
    if (start > end) {
        return -1001
    } else if (start == end) {
        return nums[start]
    }

    val mid = (start + end) / 2
    var init = nums[mid]
    var toLeft = mid - 1
    var toRight = mid + 1
    if ((end - start + 1) % 2 == 0) {
        init += nums[mid + 1]
        toRight++
    }
    var max = init

    var leftSum = 0
    var leftMax =  0
    while (toLeft >= start) {
        leftSum += nums[toLeft--]
        leftMax = max(leftMax, leftSum)
    }

    var rightSum = 0
    var rightMax =  0
    while (toRight <= end) {
        rightSum += nums[toRight++]
        rightMax = max(rightMax, rightSum)
    }

    max += leftMax + rightMax
    return max(max,max(divideAndConquer(nums, start, mid), divideAndConquer(nums, mid + 1, end)))
}