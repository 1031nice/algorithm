package boj.gold

import java.util.*
import kotlin.collections.HashSet

/**
 * G4
 */

fun main() = with(Scanner(System.`in`)) {
    val N = nextInt()
    val M = nextInt()
    val truth = Array(nextInt()) { 0 }
    val parties = Array(M) { hashSetOf<Int>() }
    val visited = Array(M) { false }
    repeat(truth.size) { truth[it] = nextInt() }
    repeat(M) { it2 -> repeat(nextInt()){ parties[it2].add(nextInt()) } }

    val q: Queue<Pair<Int, HashSet<Int>>> = LinkedList()
    for (man in truth) { // 진실을 아는 사람이 속한 파티를 찾아서 큐에 넣는다
        for ((i, party) in parties.withIndex()) {
            if (party.contains(man)) {
                q.add(i to party)
            }
        }
    }

    // 큐에는 과장할 수 없는 파티만 들어간다
    while (q.isNotEmpty()) {
        val (id, party) = q.poll() // 파티를 꺼내서
        if (visited[id]) {
            continue
        }

        visited[id] = true
        for (man in party) { // 해당 파티에 속한 사람들이 속한 파티를 찾아 큐에 넣는다
            for ((i, otherParty) in parties.withIndex()) {
                if (!visited[i] && man in otherParty) {
                    q.add(i to otherParty)
                }
            }
        }
    }

    print(visited.count { !it })
}