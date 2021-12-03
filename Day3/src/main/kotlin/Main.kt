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
    val oxygen = getOxygen(puzzle, 0)[0].toLong(2)
    val co2 = getCo2(puzzle, 0)[0].toLong(2)
    val answer = oxygen * co2

    println("Solution to part2: $answer")
}

private fun getOxygen(numbers: List<String>, bitIndex: Int) : List<String> {
    if(numbers.count() == 1) {
        return numbers
    }

    val groups = numbers.map { it[bitIndex] }.groupBy { it }
    val totalOnes = groups['1']?.count() ?: 0
    val totalZeros = groups['0']?.count() ?: 0

    return when {
        totalOnes > totalZeros -> getOxygen(numbers.filter { it[bitIndex] == '1' }, bitIndex + 1)
        totalOnes < totalZeros -> getOxygen(numbers.filter { it[bitIndex] == '0' }, bitIndex + 1)
        else -> getOxygen(numbers.filter { it[bitIndex] == '1' }, bitIndex + 1)
    }
}

private fun getCo2(numbers: List<String>, bitIndex: Int) : List<String> {
    if(numbers.count() == 1) {
        return numbers
    }

    val groups = numbers.map { it[bitIndex] }.groupBy { it }
    val totalOnes = groups['1']?.count() ?: 0
    val totalZeros = groups['0']?.count() ?: 0

    return when {
        totalOnes < totalZeros -> getCo2(numbers.filter { it[bitIndex] == '1' }, bitIndex + 1)
        totalOnes > totalZeros -> getCo2(numbers.filter { it[bitIndex] == '0' }, bitIndex + 1)
        else -> getCo2(numbers.filter { it[bitIndex] == '0' }, bitIndex + 1)
    }
}