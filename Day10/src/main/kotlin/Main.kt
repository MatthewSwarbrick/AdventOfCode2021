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
    val input = listOf(
        "[({(<(())[]>[[{[]{<()<>>",
        "[(()[<>])]({[<{<<[]>>(",
        "{([(<{}[<>[]}>{[]{[(<()>",
        "(((({<>}<{<{<>}{[]{[]{}",
        "[[<[([]))<([[{}[[()]]]",
        "[{[{({}]{}}([{[{{{}}([]",
        "{<[[]]>}<{[{[{[]{()[[[]",
        "[<(<(<(<{}))><([]([]()",
        "<{([([[(<>()){}]>(<<{{",
        "<{([{{}}[<[[[<>{}]]]>[]]"
    )
    
    val answer = 0
    println("Solution to part2: $answer")
}

private fun List<String>.toFirstIllegalCharacters(): List<Char> =
    this.mapNotNull { line -> line.toFirstIllegalCharacter() }

private fun String.toFirstIllegalCharacter(): Char? {
    var openingChars = listOf(this[0])
    return this.drop(1).mapNotNull { c ->
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