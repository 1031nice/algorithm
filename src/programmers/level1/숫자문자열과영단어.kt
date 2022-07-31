package programmers.level1

fun solution(s: String): Int {
    val stringToDigit = hashMapOf("zero" to 0, "one" to 1, "two" to 2,
                                  "three" to 3, "four" to 4, "five" to 5,
                                  "six" to 6, "seven" to 7, "eight" to 8, "nine" to 9)

    val stringBuilder = StringBuilder()
    var word = ""
    for (i in s.indices) {
        if (s[i].isDigit()) {
            stringBuilder.append(s[i])
        } else {
            word += s[i]
            if (stringToDigit.contains(word)) {
                stringBuilder.append(stringToDigit[word])
                word = ""
            }
        }
    }

    return stringBuilder.toString().toInt()
}