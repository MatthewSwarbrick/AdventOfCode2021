fun main() {
    solvePart1()
    solvePart2()
}

private fun solvePart1() {
    val answer = puzzle
        .toInstructions()
        .fold()
        .first()
        .distinct()
        .count()

    println("Solution to part1: $answer")
}

private fun solvePart2() {
    val answer = puzzle
        .toInstructions()
        .fold()
        .last()
        .distinct()
        .print()
        .count()

    println("Solution to part2: $answer")
}

private fun List<String>.toInstructions(): Pair<List<Point>, List<String>> {
    val points = this
        .filter { it.isNotBlank() && !it.startsWith("fold") }
        .map {
            val parts = it.split(",")
            val x = parts[0].trim().toInt()
            val y = parts[1].trim().toInt()
            Point(x, y, isDot = true)
        }

    val instructions = this
        .filter { it.startsWith("fold") }

    return points to instructions
}

private fun Pair<List<Point>, List<String>>.fold(): List<List<Point>> {
    var (points, instructions) = this

    return instructions.map { instruction ->
        val instructionParts = instruction.split("=")
        val axis = instructionParts[0].last()
        val value = instructionParts[1].toInt()

        points = when(axis) {
            'x' -> points.foldX(value)
            'y' -> points.foldY(value)
            else -> points
        }

        points
    }
}

private fun List<Point>.foldX(value: Int): List<Point> =
    this.map {
        if(it.x < value) {
            it
        } else {
            val distanceFromFold = it.x - value
            val newX = value - distanceFromFold
            Point(newX, it.y, isDot = true)
        }
    }

private fun List<Point>.foldY(value: Int): List<Point> =
    this.map {
        if(it.y < value) {
            it
        } else {
            val distanceFromFold = it.y - value
            val newY = value - distanceFromFold
            Point(it.x, newY, isDot = true)
        }
    }

private fun List<Point>.print(): List<Point> {
    println()
    val maxX = this.maxOf { it.x }
    val maxY = this.maxOf { it.y }
    for(y in 0..maxY) {
        for(x in 0..maxX) {
            val point = this.find { it.x == x && it.y == y }
            if(point == null) {
                print(" ")
            } else {
                print("#")
            }
        }
        println()
    }
    println()
    return this
}

data class Point(val x: Int, val y: Int, val isDot: Boolean)