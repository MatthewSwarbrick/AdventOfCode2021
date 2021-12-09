fun main() {
    solvePart1()
    solvePart2()
}

private fun solvePart1() {
    val answer = puzzle
        .toAdjacents()
        .toLowPoints()
        .toRiskLevel()

    println("Solution to part1: $answer")
}

private fun solvePart2() {
    val input = listOf(
        "2199943210",
        "3987894921",
        "9856789892",
        "8767896789",
        "9899965678"
    )

    val answer = 0
    println("Solution to part2: $answer")
}

private fun List<String>.toAdjacents(): MutableList<Adjacent> {
    val xLength = this[0].length
    val yLength = this.count()

    val adjacents = mutableListOf<Adjacent>()

    for(x in 0 until xLength) {
        for(y in 0 until yLength) {
            val heightPoint = this[y][x].digitToInt()
            val top = if(y > 0) { this[y - 1][x] } else { null } ?.digitToInt() ?: 10
            val left = if(x > 0) { this [y][x - 1] } else { null } ?.digitToInt() ?: 10
            val bottom = if(y < yLength - 1) { this[y + 1 ][x] } else { null } ?.digitToInt() ?: 10
            val right = if(x < xLength - 1) { this[y][x + 1] } else { null } ?.digitToInt() ?: 10

            adjacents.add(Adjacent(heightPoint, top, left, bottom, right))
        }
    }

    return adjacents
}

private fun List<Adjacent>.toLowPoints() =
    this.filter {
        it.heightPoint < it.top &&
        it.heightPoint < it.left &&
        it.heightPoint < it.bottom &&
        it.heightPoint < it.right
    }
    .map { it.heightPoint }

private fun List<Int>.toRiskLevel() =
    this.sumOf { it + 1 }

data class Adjacent(val heightPoint: Int, val top: Int, val left: Int, val bottom: Int, val right: Int)