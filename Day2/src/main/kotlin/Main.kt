fun main() {
    solvePart1()
    solvePart2()
}

private fun solvePart1() {
    val depth = puzzle.getDepth()
    val horizontal = puzzle.getHorizontal()
    val answer = horizontal * depth

    println("Depth: $depth")
    println("Horizontal: $horizontal")
    println("Solution to part1: $answer")
}

private fun solvePart2() {
    println("Solution to part2:")
}

fun List<String>.getDepth(): Long {
    val totalDown = this.filter { it.startsWith("down") }.sumOf { it.removePrefix("down").trim().toLong() }
    val totalUp = this.filter { it.startsWith("up") }.sumOf { it.removePrefix("up").trim().toLong() }
    return totalDown - totalUp
}

fun List<String>.getHorizontal() =
    this.filter { it.startsWith("forward") }.sumOf { it.removePrefix("forward").trim().toLong() }