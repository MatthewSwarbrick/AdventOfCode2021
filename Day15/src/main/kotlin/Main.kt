fun main() {
    solvePart1()
    solvePart2()
}

private var maxX: Int = 0
private var maxY: Int = 0

private fun solvePart1() {
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

private fun List<Point>.toPaths(): List<List<Point>> {
    val startingPoint = this.first { it.isStartPoint() }
    val startingPath = listOf(startingPoint)
    val risks = mutableMapOf<Point, Int>()
    return startingPath.toPaths(this, risks)
}

private fun List<Point>.toPaths(allPoints: List<Point>, risks: MutableMap<Point, Int>): List<List<Point>> =
    this.last()
        .toNeighboursDown(allPoints)
        .map { this.plus(it) }
        .flatMap {
            val lastPoint = it.last()
            val risk = it.toRisk()
            val currentLowRisk = risks[lastPoint]
            if(currentLowRisk != null && risk < currentLowRisk){
                risks[lastPoint] = risk
            } else if(currentLowRisk == null) {
                risks[lastPoint] = risk
            }

            if((currentLowRisk != null && risk > currentLowRisk) || this.contains(lastPoint)) {
                listOf()
            } else if(lastPoint.isEndPoint())  {
                listOf(it)
            } else {
                it.toPaths(allPoints, risks)
            }
        }


private fun List<Point>.toDiagonal(allPoints: List<Point>): List<Point> =
    this.flatMap { it.toNeighboursUp(allPoints) }.distinct()


private fun List<String>.toPoints(): List<Point> {
    maxX = this[0].lastIndex
    maxY = this.lastIndex

    return this.mapIndexed { y, row ->
        row.mapIndexed { x, c ->
            Point(x, y, c.digitToInt())
        }
    }.flatten()
}

private fun List<List<Point>>.toLowestRisk() =
    this.minOf { it.filter { point -> !point.isStartPoint() }.toRisk() }

private fun List<Point>.toRisk() = this.sumOf { it.risk }

private fun Point.isEndPoint() = this.x == maxX && this.y == maxY
private fun Point.isStartPoint() = this.x == 0 && this.y == 0

private fun Point.toNeighboursUp(allPoints: List<Point>): List<Point> {
    val top = allPoints.find { it.x == this.x && it.y == this.y - 1 }
    val left = allPoints.find { it.x == this.x - 1 && it.y == this.y }

    return listOfNotNull(
        top, left
    )
}

private fun Point.toNeighboursDown(allPoints: List<Point>): List<Point> {
    val bottom = allPoints.find { it.x == this.x && it.y == this.y + 1 }
    val right = allPoints.find { it.x == this.x + 1 && it.y == this.y }

    return listOfNotNull(
        bottom, right
    )
}

data class Point(val x: Int, val y: Int, val risk: Int)