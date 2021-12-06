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
    val answer = 0

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
