import arrow.core.fold

fun main() {
    solvePart1()
    solvePart2()
}

private fun solvePart1() {
    val answer = puzzle
        .toPoints()
        .toAdjacents()
        .toLowPoints()
        .toRiskLevel()

    println("Solution to part1: $answer")
}

private fun solvePart2() {
    val answer = puzzle
        .toPoints()
        .toAdjacents()
        .toBasins()
        .map { it.size }
        .sortedDescending()
        .take(3)
        .fold(1) { acc, size -> acc * size }

    println("Solution to part2: $answer")
}

private fun List<String>.toPoints() =
     this.flatMapIndexed { y, row ->
        row.map { it.digitToInt() }
            .mapIndexed { x, height ->
                Point(x, y, height)
            }
    }

private fun List<Point>.toAdjacents(): List<Adjacent> =
    this.map { point ->
        val top = this.singleOrNull { it.x == point.x && it.y == point.y - 1 }
        val bottom = this.singleOrNull { it.x == point.x && it.y == point.y + 1 }
        val left = this.singleOrNull { it.x == point.x - 1 && it.y == point.y }
        val right = this.singleOrNull { it.x == point.x + 1 && it.y == point.y }
        Adjacent(point, top, left, bottom, right)
    }

private fun List<Adjacent>.toLowPoints() =
    this.filter {
        (it.top == null || it.point.height < it.top.height) &&
        (it.left == null || it.point.height < it.left.height) &&
        (it.bottom == null || it.point.height < it.bottom.height) &&
        (it.right == null || it.point.height < it.right.height)
    }
        .map { it.point }

private fun List<Point>.toRiskLevel() =
    this.sumOf { it.height + 1 }

private fun List<Adjacent>.toBasins(): List<Set<Point>> =
    this.toLowPoints()
    .map {
        it.toBasin(this).plus(it).toSet()
    }

private fun Point.toBasin(adjacents: List<Adjacent>) : List<Point> {
    val adjacent = adjacents.single { it.point == this }
    val basinPoints = adjacent.toBasinPoints()
    return basinPoints
        .plus(basinPoints.flatMap { it.toBasin(adjacents) })
}

private fun Adjacent.toBasinPoints() : List<Point> {
    val points = mutableListOf<Point>()
    if(this.top.isBasinPoint(this.point.height)) {
        points.add(this.top!!)
    }

    if(this.bottom.isBasinPoint(this.point.height)) {
        points.add(this.bottom!!)
    }

    if(this.left.isBasinPoint(this.point.height)) {
        points.add(this.left!!)
    }

    if(this.right.isBasinPoint(this.point.height)) {
        points.add(this.right!!)
    }

    return points
}

private fun Point?.isBasinPoint(adjacentHeight: Int) =
    this != null &&
    this.height < 9 &&
    this.height > adjacentHeight

data class Point(val x: Int, val y: Int, val height: Int)

data class Adjacent(
    val point: Point,
    val top: Point?,
    val left: Point?,
    val bottom: Point?,
    val right: Point?
)