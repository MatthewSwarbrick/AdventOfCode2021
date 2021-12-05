import arrow.core.Tuple4

fun main() {
    solvePart1()
    solvePart2()
}

private fun solvePart1() {
    val input = listOf(
        "0,9 -> 5,9",
        "8,0 -> 0,8",
        "9,4 -> 3,4",
        "2,2 -> 2,1",
        "7,0 -> 7,4",
        "6,4 -> 2,0",
        "0,9 -> 2,9",
        "3,4 -> 1,4",
        "0,0 -> 8,8",
        "5,5 -> 8,2"
    )

    val answer = 0
    println("Solution to part1: $answer")
}

private fun solvePart2() {
    val answer = 0
    println("Solution to part2: $answer")
}

fun String.toCoords(): Tuple4<Int, Int, Int, Int> {
    val parts = this.split("->")
    val (x1, y1) = parts[0].split(",").map { it.trim().toInt() }
    val (x2, y2) = parts[1].split(",").map { it.trim().toInt() }
    return Tuple4(x1, y1, x2, y2)
}