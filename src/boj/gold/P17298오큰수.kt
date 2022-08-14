package boj.gold

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

/**
 * G4 #Stack
 *
 * 가장 간단한 풀이는 수열을 구성하는 수마다 오른쪽에 있는 수를 차례대로 보며 자신보다 큰 수가 나오자마 그 값을 오큰수로 삼는 것 O(N^2)
 * N이 1,000,000이므로 O(N^2)는 불가능
 *
 * O(N^2)을 어떻게 하면 줄일 수 있을까
 * 테스트케이스를 다른 걸 더 해보자
 * A = [2, 6, 9, 5, 8, 14, 11, 4, 3]을 봤더니 A(3), A(5)의 오큰수는 14
 * 그런데 A(3)일 때도 14를 찾았는데, A(5)일 때도 14를 찾고 있다
 * 그럼 좀 극단적으로 생각해서 아래 경우를 살펴보자
 * A = [5, 4, 3, 2, 1, 6]
 * A(1) ~ A(5)의 오큰수는 모두 6인데 매번 6을 찾아가고 있다
 * 이 중복을 줄일 수 있을까?
 *
 * 역으로 6에서 출발한다면 어떨까?
 * 다시 말해, 나의 오큰수가 누구인지를 찾는 것이 아니라 내가 누군가의 오큰수일 수 있는지 검사한다면?
 * (배열의 순회 방향을 끝에서 시작으로 하는 게 아니라,
 * 방향은 시작에서 끝 그대로 두고 오큰수 검사를 지금수와 지금까지 등장한 수들 간 비교
 *
 * 오큰수가 설정되지 않은 수는 내림차순으로 정렬된다
 * 따라서 현재 수가 오큰수가 설정되지 않은 수들의 오큰수가 될 수 있는지 검사할 때
 * 가장 좋은 방향은 가장 마지막에 넣은 수부터 검사하는 것이다(오름차순으로 검사하는 것)
 * 오름차순으로 검사하다가 지금 수보다 큰 값을 만나게되면 그 뒤로는 검사를 안해도 되기 때문이다
 * 따라서 스택이 적절하다
 *
 * 오큰수는 자신의 오른쪽 중 가장 왼쪽에 등장하는 자신보다 큰 수이므로 한 번 오큰수가 설정되면 더 검사하지 않아도 된다
 */

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val size = readLine().toInt()
    val nums = Array(size) { 0 }
    val answer = Array(size) { -1 }
    val stack = Stack<Pair<Int, Int>>()
    val split = readLine().split(" ")
    (0 until size).forEach {
        nums[it] = split[it].toInt()
    }

    stack.push(nums[0] to 0)

    (1 until nums.size).forEach {
        val now = nums[it]
        while (stack.isNotEmpty() && now > stack.peek().first) {
            val (_, index) = stack.pop()
            answer[index] = now
        }
        stack.push(now to it)
    }

    val writer = BufferedWriter(OutputStreamWriter(System.out))
    answer.forEach { writer.write("$it ") }
    writer.flush()
}