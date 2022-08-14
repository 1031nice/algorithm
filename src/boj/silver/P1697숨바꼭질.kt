package boj.silver

import java.util.*
import kotlin.math.min

/**
 * 이 문제도 못으로 보고 무작정 때린거 보면 망치를 들고 있었나보다
 * 연속해서 DP 문제를 만났어서 그런지 DP겠거니 하고 접근했다가 한참 헤맸다
 *
 * n <- start, f(n)에서 f(n-1), f(n+1), f(n*2)를 계산해갔는데, 범위가 0 ~ 100,000이다보니 stackoverflow
 * n <- target, f(n)에서 f(n-1), f(n+1), f(n/2)를 계산해보려고 했는데, 이 접근 역시 결과는 같았다
 * 단순히 범위를 따라 0부터 1씩 더하며 한 칸, 한 칸 이동하며 걸린 시간은 최단 시간이라는 보장이 없다
 * N에서 2N까지 N초에 걸려 1씩 증가하여 도달할 수도 있지만, N에서 *2를 하여 1초만에 2N에 도달할 수도 있기 때문이다
 *
 * +1을 하든, -1을 하든, *2를 하든 결국 모두 1초이다
 * 최단 시간을 원하는거니까 최단 시간에 집중해보자
 * 모든 범위를 보려고 하지 말고, 최단 시간이라고 확신할 수 있는 곳만 가보자
 *
 * 출발점에서 1초를 써서 갈 수 있는 곳이 유일하게 1초를 써서 갈 수 있는 곳이다
 * 1초를 써서 갈 수 있는 곳에서 1초를 써서 갈 수 있는 곳이 유일하게 2초를 써서 갈 수 있는 곳이다
 * ...
 * BFS 문제구나
 */
fun main() = with(Scanner(System.`in`)) {
    val start = nextInt()
    val target = nextInt()

    if (target <= start) {
        print(start - target)
        return
    }

    val visited = Array(target + 1 + 1) { false } // +1은 index, value 일치시키기 위해서, +1은 target + 1까지가 최대 범위이므로
    val queue = LinkedList<Pair<Int, Int>>()
    queue.add(target to 0)
    while (queue.isNotEmpty()) {
        val (num, length) = queue.pop()
        if (num == start) {
            print(length)
            break
        }

        if (visited[num]) {
            continue
        }

        visited[num] = true
        if (num + 1 < visited.size && !visited[num + 1]) {
            queue.add(num + 1 to length + 1)
        }
        if (num - 1 >= 0 && !visited[num - 1]) {
            queue.add(num - 1 to length + 1)
        }
        if (num % 2 == 0 && !visited[num / 2]) {
            queue.add(num / 2 to length + 1)
        }
    }
}

/**
 * stackoverflow
 * 범위 자체가 커져버리면 캐싱을 해도 base case까지 계산하는 데 아주 큰 depth가 필요하기 때문에 base case에 도달하기 전에(캐시가 활용되기도 전에) stackoverflow 발생 가능
 */
fun func(cache: Array<Int>, start: Int, target: Int, time: Int): Int {
    if (start !in 0..100_000 || start > target + 1) {
        return 100_001
    } else if (cache[start] != 100_001) {
        return cache[start]
    } else if (start == target) {
        return 0
    }

    val min = minOf(
        func(cache, start * 2, target, time + 1),
        func(cache, start - 1, target, time + 1),
        func(cache, start + 1, target, time + 1)
    )
    cache[start] = min(min + 1, cache[start])
    return cache[start]
}