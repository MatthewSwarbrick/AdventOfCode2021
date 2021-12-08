fun main() {
    solvePart1()
    solvePart2()
}

private fun solvePart1() {
    val signalPatterns = puzzle.toSignalPatterns()

    val answer = signalPatterns
            .flatMap { (_, outputs) -> outputs }
            .count { it.length == 2 || it.length == 3 || it.length == 4 || it.length == 7 }

    println("Solution to part1: $answer")
}

private fun solvePart2() {
    val answer = puzzle
        .toSignalPatterns()
        .map { (pattern, output) -> pattern.toNumberKey() to output }
        .map { (key, output) ->
            output
                .map { key[it] }
                .joinToString(separator = "")

        }
        .sumOf { it.toLong() }

    println("Solution to part2: $answer")
}

private fun List<String>.toSignalPatterns(): List<Pair<List<String>, List<String>>> =
    this.map { line ->
        val parts = line.split("|")
        val pattern = parts[0].trim().split(" ").map { it.trim().toAlphaSortedString() }
        val output = parts[1].trim().split(" ").map { it.trim().toAlphaSortedString() }
        pattern to output
    }

private fun List<String>.toNumberKey(): Map<String, Int> {
    val pattern = this
    val one = pattern.single { it.length == 2}
    val seven = pattern.single { it.length == 3 }
    val four = pattern.single { it.length == 4 }
    val eight = pattern.single { it.length == 7 }
    val six = pattern.single { it.length == 6 && !one.all { letter -> it.contains(letter) }}
    val three = pattern.single { it.length == 5 && one.all { letter -> it.contains(letter) }}

    val topRight = one.single { letter -> !one.map { it }.intersect(six.map { it }).contains(letter) }

    val five = pattern.single { it.length == 5 && it.all { letter -> letter != topRight }}
    val two = pattern.single { it.length == 5 && it != three && it != five}
    val nine = pattern.single { letters -> letters.length == 6 && four.toSet() == letters.map { it }.intersect(four.map { it })  }
    val zero = pattern.single { it.length == 6 && it != nine && it != six}

    return mapOf(zero to 0, one to 1, two to 2, three to 3, four to 4, five to 5, six to 6, seven to 7, eight to 8, nine to 9)
}

private fun String.toAlphaSortedString() = this.toSortedSet().joinToString(separator = "")