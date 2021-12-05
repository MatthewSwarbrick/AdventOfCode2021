import arrow.core.Tuple4

fun main() {
    solvePart1()
    solvePart2()
}

private fun solvePart1() {
    val answer = puzzle
        .map { it.toCoords() }
        .flatMap { it.toLinePoints() }
        .groupingBy { it }
        .eachCount()
        .count { it.value >= 2}

    println("Solution to part1: $answer")
}

private fun solvePart2() {
    val answer = puzzle.map { it.toCoords() }
        .flatMap { it.toLinePoints(ignoreDiagonal = false) }
        .groupingBy { it }
        .eachCount()
        .count { it.value >= 2}

    println("Solution to part2: $answer")
}

fun String.toCoords(): Tuple4<Int, Int, Int, Int> {
    val parts = this.split("->")
    val (x1, y1) = parts[0].split(",").map { it.trim().toInt() }
    val (x2, y2) = parts[1].split(",").map { it.trim().toInt() }
    return Tuple4(x1, y1, x2, y2)
}

fun Tuple4<Int, Int, Int, Int>.toLinePoints(ignoreDiagonal: Boolean = true): List<Pair<Int, Int>> {
    val (x1, y1, x2, y2) = this
    val isDiagonal = x1 != x2 && y1 != y2
    if(ignoreDiagonal && isDiagonal) {
        return listOf()
    }

    return if(isDiagonal) {
        val firstX = x1.coerceAtMost(x2)
        val lastX = x1.coerceAtLeast(x2)

        val firstY = y1.coerceAtMost(y2)
        val lastY = y1.coerceAtLeast(y2)

        val reserveYRange = (firstX != x1 && firstY == y1) || (firstX == x1 && firstY != y1)

        val yRange = (firstY..lastY).toList().let {
            if(reserveYRange) {
                it.reversed()
            } else {
                it
            }
        }

        (firstX..lastX).mapIndexed { index, x ->
            x to yRange[index]
        }
    } else {
        (x1.coerceAtMost(x2)..x1.coerceAtLeast(x2)).flatMap { x ->
            (y1.coerceAtMost(y2)..y1.coerceAtLeast(y2)).map { y ->
                x to y
            }
        }
    }
}