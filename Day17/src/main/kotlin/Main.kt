fun main() {
    solvePart1()
    solvePart2()
}

private fun solvePart1() {
    val answer = puzzle
        .toTargetArea()
        .toVelocities()
        .toHits()
        .toHighestPoint()

    println("Solution to part1: $answer")
}

private fun solvePart2() {
    val answer = puzzle
        .toTargetArea()
        .toVelocities()
        .toHits()
        .count()

    println("Solution to part2: $answer")
}

private fun String.toTargetArea(): TargetArea {
    val parts = this.split(":")[1].trim().split(",")
    val xParts = parts[0].split("=")[1].split("..")
    val firstX = xParts[0].toInt()
    val lastX = xParts[1].toInt()

    val yParts = parts[1].split("=")[1].split("..")
    val firstY = yParts[0].toInt()
    val lastY = yParts[1].toInt()

    val points = (firstX..lastX).flatMap { x ->
        (firstY..lastY). map { y ->
            Point(x, y)
        }
    }

    return TargetArea(points)
}

private fun TargetArea.toVelocities(): Pair<TargetArea, List<Velocity>> {
    val maxXVelocity = this.points.maxOf { it.x }
    val minXVelocity = this.points.minOf { it.x }.toMinXVelocity()

    val minYVelocity = this.points.minOf { it.y }
    val maxYVelocity = if(minYVelocity < 0) { Math.abs(minYVelocity) - 1 } else minYVelocity

    return this to (minXVelocity .. maxXVelocity).flatMap { x ->
        (minYVelocity .. maxYVelocity).map { y ->
            Velocity(x, y)
        }
    }
}

private fun Pair<TargetArea, List<Velocity>>.toHits(): List<Velocity> {
    val (targetArea, velocities) = this
    return velocities.filter {
        it.isHit(targetArea)
    }
}

private fun List<Velocity>.toHighestPoint(): Int =
    this.map {
            it.toHighestPoint()
        }
        .maxOf { it }

private fun Velocity.toHighestPoint(): Int {
    var highestY = 0
    var currentVelocity = this
    var currentPoint = Point(0, 0)
    while(currentPoint.y >= 0) {
        currentPoint = Point(currentPoint.x + currentVelocity.x, currentPoint.y + currentVelocity.y)
        highestY = highestY.coerceAtLeast(currentPoint.y)
        currentVelocity = Velocity((currentVelocity.x - 1).coerceAtLeast(0), currentVelocity.y - 1)
    }

    return highestY
}

private fun Velocity.isHit(targetArea: TargetArea): Boolean {
    val maxX = targetArea.points.maxOf { it.x }
    val minY = targetArea.points.minOf { it.y }
    var currentPoint = Point(0, 0)
    var currentVelocity = this
    while(currentPoint.x < maxX && currentPoint.y > minY) {
        currentPoint = Point(currentPoint.x + currentVelocity.x, currentPoint.y + currentVelocity.y)

        if(targetArea.points.contains(currentPoint)) {
            return true
        }

        currentVelocity = Velocity((currentVelocity.x - 1).coerceAtLeast(0), currentVelocity.y - 1)
    }

    return false
}

private fun Int.toMinXVelocity(): Int {
    var min = 0
    var step = 1
    while(min < this) {
        min += step
        step++
    }

    return step - 1
}

data class TargetArea(val points: List<Point>)

data class Point(val x: Int, val y : Int)

data class Velocity(val x: Int, val y:  Int)