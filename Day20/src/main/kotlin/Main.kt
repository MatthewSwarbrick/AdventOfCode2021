fun main() {
    solvePart1()
    solvePart2()
}

private fun solvePart1() {
    val answer = puzzle
        .toImage()
        .enhance(2)
        .toLitPixelCount()

    println("Solution to part1: $answer")
}

private fun solvePart2() {
    val answer = puzzle
        .toImage()
        .enhance(50)
        .toLitPixelCount()

    println("Solution to part2: $answer")
}

private fun List<String>.toImage(): Pair<List<List<Char>>, String> {
    val algorithm = this[0]
    val image = this
        .drop(1)
        .filter { it.isNotBlank() }
        .map { row ->
            row.map { it }
        }

    return image to algorithm
}

private fun Pair<List<List<Char>>, String>.enhance(times: Int): Pair<List<List<Char>>, String> {
    val timesToEnhanceLeft = times - 1
    val (image, algorithm) = this

    val defaultChar = if(times % 2 == 0) { algorithm.last() } else { algorithm.first() }

    val newImage = image
        .toPaddedImage(defaultChar)
        .toOutputImage(algorithm, defaultChar)

    return if(timesToEnhanceLeft >= 1) {
        (newImage to algorithm).enhance(timesToEnhanceLeft)
    } else {
        newImage to algorithm
    }
}

private fun Pair<List<List<Char>>, String>.toLitPixelCount(): Long =
    this.first.sumOf { it.count { pixel -> pixel.isLit() }.toLong() }

private fun List<List<Char>>.toPaddedImage(defaultChar: Char): List<List<Char>> {
    val paddingRow = listOf("$defaultChar".repeat(this[0].size).map { it })
    return paddingRow
        .plus(this)
        .plus(paddingRow)
        .map { row ->
            listOf(defaultChar, *row.toTypedArray(), defaultChar)
        }
}

private fun List<List<Char>>.toOutputImage(algorithm: String, defaultChar: Char): List<List<Char>> =
    this.mapIndexed { y, row ->
        row.mapIndexed { x, _ ->
            val algorithmIndex = this.toGrid(x, y, defaultChar)
                .toBinary()
                .toInt(2)

            algorithm[algorithmIndex]
        }
    }

private fun List<List<Char>>.toGrid(x: Int, y: Int, defaultChar: Char): List<List<Char>> {
    val topLeft = if(y - 1 < 0) { defaultChar } else if(x - 1 < 0) { defaultChar } else { this[y-1][x-1] }
    val top = if(y - 1 < 0) { defaultChar } else { this[y-1][x] }
    val topRight = if(y - 1 < 0) { defaultChar } else if(x + 1 > this[0].lastIndex) { defaultChar } else { this[y-1][x+1] }
    val left = if(x - 1 < 0) { defaultChar } else { this[y][x-1] }
    val center = this[y][x]
    val right = if(x + 1 > this[0].lastIndex) { defaultChar } else { this[y][x+1] }
    val bottomLeft = if(y + 1 > this.lastIndex) { defaultChar } else if(x - 1 < 0) { defaultChar } else { this[y+1][x-1] }
    val bottom = if(y + 1 > this.lastIndex) { defaultChar } else { this[y+1][x] }
    val bottomRight = if(y + 1 > this.lastIndex) { defaultChar } else if(x + 1 > this[0].lastIndex) { defaultChar } else { this[y+1][x+1] }

    return listOf(
        listOf(topLeft, top, topRight),
        listOf(left, center, right),
        listOf(bottomLeft, bottom, bottomRight)
    )
}

private fun List<List<Char>>.toBinary(): String =
    this.joinToString("") { row ->
        row.joinToString(""){
            when(it) {
                '.' -> "0"
                '#' -> "1"
                else -> ""
            }
        }
    }

private fun Char.isLit(): Boolean = this == '#'