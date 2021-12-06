fun main() {
    solvePart1()
    solvePart2()
}

private fun solvePart1() {
    var finalList = puzzle
    (1..80).map {
        finalList = finalList.addDay()
    }

    val answer = finalList.count()

    println("Solution to part1: $answer")
}

private fun solvePart2() {
    var state = puzzle.groupingBy { it }.eachCount().mapValues { it.value.toLong() }
    (1..256).map {
        state = state.toNextDay()
    }

    val answer = state.map { it.value }.sum()

    println("Solution to part2: $answer")
}

private fun List<Int>.addDay(): List<Int> =
    this.flatMap {
        if(it == 0) {
            listOf(6, 8)
        } else {
            listOf(it - 1)
        }
    }

private fun Map<Int, Long>.toNextDay(): Map<Int, Long> {
    val newDays = (0 until 9).map {
        val amountInCurrentDay = this[it]
        if(amountInCurrentDay != null) {
            if(it == 0) {
                listOf(6 to amountInCurrentDay, 8 to amountInCurrentDay)
            } else {
                listOf(it - 1 to amountInCurrentDay)
            }
        } else {
            listOf(it to 0L)
        }
    }
    .flatten()
    .groupBy { it.first }
        .mapValues { c -> c.value.sumOf { it.second } }


    return newDays
}
