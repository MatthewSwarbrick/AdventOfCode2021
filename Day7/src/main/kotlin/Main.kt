import kotlin.math.abs

fun main() {
    solvePart1()
    solvePart2()
}

private fun solvePart1() {
    val lowestPosition = puzzle.minOrNull()!!
    val highestPosition = puzzle.maxOrNull()!!


    val costs = (lowestPosition until highestPosition + 1).associateWith { position ->
        puzzle.sumOf {
            abs(it - position)
        }
    }

    val answer = costs.minByOrNull { it.value }!!.value
    println("Solution to part1: $answer")
}

private fun solvePart2() {
    val input = listOf(
        16,1,2,0,4,2,7,1,2,14
    )
    val answer = 0

    println("Solution to part2: $answer")
}
