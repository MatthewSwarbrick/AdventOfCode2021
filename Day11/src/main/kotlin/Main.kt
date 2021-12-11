fun main() {
    solvePart1()
    solvePart2()
}

private fun solvePart1() {
    val answer = puzzle
        .toOctopuses()
        .toSteps()
        .toFlashes()

    println("Solution to part1: $answer")
}

private fun solvePart2() {
    val input = listOf(
        "5483143223",
        "2745854711",
        "5264556173",
        "6141336146",
        "6357385478",
        "4167524645",
        "2176841721",
        "6882881134",
        "4846848554",
        "5283751526"
    )

    val answer = 0
    println("Solution to part2: $answer")
}

private fun List<String>.toOctopuses(): List<Octopus> =
    this.flatMapIndexed { y, row ->
        row.mapIndexed { x, energy ->
            Octopus(x, y, energy.digitToInt())
        }
    }

private fun List<Octopus>.toSteps(steps: Int = 100) : Pair<List<Octopus>, Int> =
    (1..steps).fold(this to 0) { acc, _ ->
        val (octopuses, flashes) = acc
        val (octopusesAfterStep, flashesAfterStep) = octopuses.step()
        octopusesAfterStep to flashesAfterStep + flashes
    }

private fun List<Octopus>.step(): Pair<List<Octopus>, Int> {
    var octopuses = this
    for(x in 0..10) {
        for(y in 0..10) {
            octopuses = octopuses.step(x, y)
        }
    }

    return octopuses.resetIsFlashing() to octopuses.count { it.energy == 0 }
}

private fun List<Octopus>.step(x: Int,  y: Int): List<Octopus> {
    var startedFlashing = false
    var octopuses = this.map {
        if(it.x == x && it.y == y) {
            if(it.energy == 9) {
                startedFlashing = true
                Octopus(it.x, it.y, energy = 0, isFlashing = true)
            } else if(!it.isFlashing) {
                Octopus(it.x, it.y, it.energy + 1)
            } else {
                it
            }
        } else {
            it
        }
    }

    if(startedFlashing) {
        val adjacents = octopuses.toAdjacents(x, y)
        adjacents.map {
            octopuses = octopuses.step(it.x, it.y)
        }
    }

    return octopuses
}

private fun Pair<List<Octopus>, Int>.toFlashes() = this.second

private fun List<Octopus>.resetIsFlashing() = this.map { Octopus(it.x, it.y, it.energy) }

private fun List<Octopus>.toAdjacents(x: Int, y: Int): List<Octopus> {
    val topLeft = x - 1 to y - 1
    val top = x to y - 1
    val topRight = x + 1 to y - 1
    val left = x - 1 to y
    val right = x + 1 to y
    val bottomLeft = x - 1 to y + 1
    val bottom = x to y + 1
    val bottomRight = x + 1 to y + 1

    return this.mapNotNull {
        when(it.x to it.y) {
            topLeft -> it
            top -> it
            topRight -> it
            left -> it
            right -> it
            bottomLeft -> it
            bottom -> it
            bottomRight -> it
            else -> null
        }
    }
}

data class Octopus(val x: Int, val y: Int, val energy: Int, val isFlashing: Boolean = false)