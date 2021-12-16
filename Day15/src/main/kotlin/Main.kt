fun main() {
    solvePart1()
    solvePart2()
}

private var maxX: Int = 0
private var maxY: Int = 0
private var currentLowestRisk = 1000

private fun solvePart1() {
    val input = listOf(
        "1163751742",
        "1381373672",
        "2136511328",
        "3694931569",
        "7463417111",
        "1319128137",
        "1359912421",
        "3125421639",
        "1293138521",
        "2311944581"
    )

    val answer = puzzle
        .toPoints()
        .toPaths()
        .toLowestRisk()

    println("Solution to part1: $answer")
}

private fun solvePart2() {
    val input = listOf(
        "1163751742",
        "1381373672",
        "2136511328",
        "3694931569",
        "7463417111",
        "1319128137",
        "1359912421",
        "3125421639",
        "1293138521",
        "2311944581"
    )

    val answer = 0
    println("Solution to part2: $answer")
}

private fun List<String>.toPoints(): List<Point> {
    maxX = this[0].lastIndex
    maxY = this.lastIndex

    return this.mapIndexed { y, row ->
        row.mapIndexed { x, c ->
            Point(x, y, c.digitToInt())
        }
    }.flatten()
}

private fun List<Point>.toPaths(): Set<List<Point>> {
    val startingPoint = this.first { it.isStartPoint() }
    val startingPath = listOf(startingPoint)
    return startingPath.toPaths(this)
}

private fun List<Point>.toPaths(allPoints: List<Point>): Set<List<Point>> =
    this.last()
        .toNeighbours(allPoints)
        .map { this.plus(it) }
        .flatMap {
            val lastPoint = it.last()
            val risk = it.toRisk()
            if(risk > currentLowestRisk || this.contains(lastPoint)) {
                listOf()
            } else if(lastPoint.isEndPoint())  {
                if(risk < currentLowestRisk) {
                    currentLowestRisk = risk
                }
                setOf(it)
            } else {
                it.toPaths(allPoints)
            }
        }.toSet()

private fun List<Point>.toRisk() = this.sumOf { it.risk }

private fun Set<List<Point>>.toLowestRisk() =
    this.minOf { it.filter { point -> !point.isStartPoint() }.toRisk() }

private fun Point.isEndPoint() = this.x == maxX && this.y == maxY
private fun Point.isStartPoint() = this.x == 0 && this.y == 0

private fun Point.toNeighbours(allPoints: List<Point>): List<Point> {
    val top = allPoints.find { it.x == this.x && it.y == this.y - 1 }
    val left = allPoints.find { it.x == this.x - 1 && it.y == this.y }
    val right = allPoints.find { it.x == this.x + 1 && it.y == this.y }
    val bottom = allPoints.find { it.x == this.x && it.y == this.y + 1 }

    return listOfNotNull(
        top, left, right, bottom
    )
}

data class Point(val x: Int, val y: Int, val risk: Int)