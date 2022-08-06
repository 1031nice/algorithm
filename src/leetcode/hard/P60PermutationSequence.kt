package leetcode.hard

class P60PermutationSequence {
    var cnt = 0
    var answer = ""

    fun getPermutation(n: Int, k: Int): String {
        val charArray = Array(n) {' '}
        val visited = Array(n) { false }
        getPermutationRecursively(charArray, visited, n, k, 0)
        return answer
    }

    fun getPermutationRecursively(charArray: Array<Char>, visited: Array<Boolean>, n: Int, k: Int, cI: Int) {
        if (answer.isNotBlank()) {
            return
        }

        if (cI == charArray.size) {
            if (++cnt == k) {
                answer = charArray.joinToString(separator = "")
            }
            return
        }

        visited.withIndex().forEach { (vI, v) ->
            if (!v) {
                visited[vI] = true
                charArray[cI] = '0' + (cI+1)
                getPermutationRecursively(charArray, visited, n, k, cI+1)
                charArray[cI] = ' '
                visited[vI] = false
            }
        }
    }
}