fun main() {
    solvePart1()
    solvePart2()
}

private fun solvePart1() {
    val depth = puzzle.getDepth1()
    val horizontal = puzzle.getHorizontal()
    val answer = horizontal * depth

    println("Depth: $depth")
    println("Horizontal: $horizontal")
    println("Solution to part1: $answer")
}

private fun solvePart2() {
    val depth = puzzle.getDepth2()
    val horizontal = puzzle.getHorizontal()
    val answer = horizontal * depth

    println("Depth: $depth")
    println("Horizontal: $horizontal")
    println("Solution to part2: $answer")
}

fun List<String>.getDepth1(): Long {
    val totalDown = this.filter { it.startsWith("down") }.sumOf { it.removePrefix("down").trim().toLong() }
    val totalUp = this.filter { it.startsWith("up") }.sumOf { it.removePrefix("up").trim().toLong() }
    return totalDown - totalUp
}

fun List<String>.getDepth2(): Long =
    this.reduceIndexed { index, acc, curr ->
        if (index == 1) {
            val initialAim = acc.getAim() + curr.getAim()
            val initialDepth = curr.getDepth(initialAim)
            "$initialAim:$initialDepth"
        } else {
            val (currentAim, currentDepth) = acc.split(":").map { it.toLong() }
            val newAim = curr.getAim() + currentAim
            val newDepth = curr.getDepth(newAim) + currentDepth
            "$newAim:$newDepth"
        }
    }.split(":").map { it.toLong() }.reduce { _, depth -> depth }

fun List<String>.getHorizontal() =
    this.filter { it.startsWith("forward") }.sumOf { it.removePrefix("forward").trim().toLong() }

private fun String.getAim() =
    when {
        this.startsWith("down") -> this.removePrefix("down").trim().toLong()
        this.startsWith("up") -> this.removePrefix("up").trim().toLong() * -1
        else -> 0
    }

private fun String.getDepth(aim: Long) =
    when {
        this.startsWith("forward") -> this.removePrefix("forward").trim().toLong()
        else -> 0
    } * aim