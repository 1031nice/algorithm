package programmers.level1

fun solution(N: Int, stages: IntArray): IntArray {
    val failArr = IntArray(N+1 + 1) { 0 } // 특정 스테이지에서 실패한 사람의 수
    stages.forEach { failArr[it]++ }

    val pairs = arrayListOf<Pair<Int, Double>>()
    var left = stages.size
    (1..N).forEach {
        if (left == 0) { // it 스테이지에 도달한 사람이 없다면
            pairs.add(it to 0.0)
        } else {
            val fail = failArr[it] // it 스테이지에서 실패한 사람의 수
            pairs.add(it to fail.toDouble() / left)
            left -= fail
        }
    }

    pairs.sortWith { p1, p2 ->
        if (p1.second.compareTo(p2.second) == 0) { // 실패율이 같으면
            p1.first.compareTo(p2.first) // 번호의 오름차순
        } else {
            p2.second.compareTo(p1.second) // 시래율 내림차순
        }
    }

    return pairs.map { it.first }.toIntArray()
}