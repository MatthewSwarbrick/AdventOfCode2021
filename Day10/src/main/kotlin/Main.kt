fun main() {
    solvePart1()
    solvePart2()
}

const val openSquare = '['
const val closeSquare = ']'
const val openRound = '('
const val closeRound = ')'
const val openCurly = '{'
const val closeCurly = '}'
const val openAngle = '<'
const val closeAngle = '>'

private fun solvePart1() {
    val answer = puzzle
        .toFirstIllegalCharacters()
        .toErrorScore()

    println("Solution to part1: $answer")
}

private fun solvePart2() {
    val answer = puzzle
        .toLineFixes()
        .toScores()
        .sorted()
        .toMiddleScore()

    println("Solution to part2: $answer")
}

private fun List<String>.toFirstIllegalCharacters(): List<Char> =
    this.mapNotNull{  line -> line.toOpenChunks().second }

private fun List<String>.toLineFixes(): List<List<Char>> =
    this.asSequence().map { line -> line.toOpenChunks() }
        .filter { it.second == null }
        .map { it.first }
        .map { it.map { c ->
            c.toClosingCharacter()
        }}
        .map { it.reversed() }.toList()

private fun String.toOpenChunks(): Pair<List<Char>, Char?> {
    var openingChars = listOf(this[0])
    val firstIllegalCharacter = this.drop(1).mapNotNull { c ->
        if (c.isClosingChar()) {
            when (c) {
                closeRound -> if (openingChars.last() == openRound) {
                    openingChars = openingChars.subList(0, openingChars.size - 1)
                    null
                } else {
                    c
                }
                closeCurly -> if (openingChars.last() == openCurly) {
                    openingChars = openingChars.subList(0, openingChars.size - 1)
                    null
                } else {
                    c
                }
                closeAngle -> if (openingChars.last() == openAngle) {
                    openingChars = openingChars.subList(0, openingChars.size - 1)
                    null
                } else {
                    c
                }
                closeSquare -> if (openingChars.last() == openSquare) {
                    openingChars = openingChars.subList(0, openingChars.size - 1)
                    null
                } else {
                    c
                }
                else -> null
            }
        } else {
            openingChars = openingChars.plus(c)
            null
        }
    }.firstOrNull()

    return openingChars to firstIllegalCharacter
}

private fun Char.isClosingChar() =
    this == closeAngle || this == closeCurly || this == closeSquare || this == closeRound

private fun List<Char>.toErrorScore() =
    this.sumOf {
        when (it) {
            closeRound -> 3L
            closeSquare -> 57L
            closeCurly -> 1197L
            closeAngle -> 25137L
            else -> 0L
        }
    }

private fun List<List<Char>>.toScores() = this.map { it.toScore() }

private fun List<Char>.toScore() =
    this.fold(0L) { totalScore, c ->
        (totalScore * 5) + c.toScore()
    }

private fun Char.toClosingCharacter() =
    when(this) {
        openSquare -> closeSquare
        openAngle -> closeAngle
        openRound -> closeRound
        openCurly -> closeCurly
        else -> this
    }

private fun Char.toScore() =
    when(this) {
        closeRound -> 1
        closeSquare -> 2
        closeCurly -> 3
        closeAngle -> 4
        else -> 0
    }

private fun List<Long>.toMiddleScore() =
    this[this.size / 2]