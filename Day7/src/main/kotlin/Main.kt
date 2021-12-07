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
    val lowestPosition = puzzle.minOrNull()!!
    val highestPosition = puzzle.maxOrNull()!!


    val costs = (lowestPosition until highestPosition + 1).associateWith { position ->
        puzzle.sumOf {
            val distance = abs(it - position)
            distance * ((distance / 2f) + 0.5)
        }
    }

    val answer = costs.minByOrNull { it.value }!!.value

    println("Solution to part2: $answer")
}
