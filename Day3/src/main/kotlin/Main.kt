fun main() {
    solvePart1()
    solvePart2()
}

private fun solvePart1() {
    val reportLength = puzzle[0].length

    var finalGamma = ""
    var finalEpsilon = ""
    for (i in 0 until reportLength) {
        val groups = puzzle.map { it[i] }.groupBy { it }
        val gamma = groups.maxByOrNull { it.value.count() }!!.key
        val epsilon = groups.minByOrNull { it.value.count() }!!.key
        finalGamma += gamma
        finalEpsilon += epsilon
    }

    val answer = finalGamma.toLong(2) * finalEpsilon.toLong(2)
    println("Solution to part1: $answer")
}

private fun solvePart2() {
//    val input = listOf(
//        "00100",
//        "11110",
//        "10110",
//        "10111",
//        "10101",
//        "01111",
//        "00111",
//        "11100",
//        "10000",
//        "11001",
//        "00010",
//        "01010"
//    )
    println("Solution to part2:")
}