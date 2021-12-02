fun main() {
    solvePart1()
    solvePart2()
}

private fun solvePart1() {
    val totalIncreases = reduceArrayToNumberOfIncreases(puzzle)

    println("Solution to puzzle1: $totalIncreases")
}

private fun solvePart2() {
    val reducedInput = puzzle.toSlidingWindow()
    val totalIncreases = reduceArrayToNumberOfIncreases(reducedInput)

    println("Solution to puzzle2: $totalIncreases")
}

private fun reduceArrayToNumberOfIncreases(input: Array<Int>) = input.reduceIndexed { index, acc, curr ->
    if (curr > input[index - 1]) {
        acc + 1
    } else {
        acc
    }
} - input[0]

private fun Array<Int>.toSlidingWindow(windowSize: Int = 3): Array<Int> {
    val newArray = mutableListOf<Int>()
    for (i in 0 until this.size) {
        newArray.add(this
            .drop(i)
            .take(windowSize).sum())
    }
    return newArray.toTypedArray()
}