fun main() {
    solvePart1()
    solvePart2()
}

private fun solvePart1() {
    val (numbers, boards) = puzzle.toBingoBoards()

    val (winningNumber, winningBoard) = play1(numbers, boards)
    val unmarkedScore = winningBoard.toUnmarkedScore()

    val answer = winningNumber * unmarkedScore
    println("Solution to part1: $answer")
}

private fun solvePart2() {
    val (numbers, boards) = puzzle.toBingoBoards()

    val (lastWinningNumber, lastWinningBoard) = play2(numbers, boards)
    val unmarkedScore = lastWinningBoard.toUnmarkedScore()

    val answer = lastWinningNumber * unmarkedScore
    println("Solution to part2: $answer")
}

private fun List<String>.toBingoBoards(): Pair<List<Int>, List<List<List<Pair<Int, Boolean>>>>> {
    val numbers = this[0].split(',').map { it.toInt() }

    val boards = mutableListOf<List<List<Pair<Int, Boolean>>>>()
    var currentBoard = mutableListOf<List<Pair<Int, Boolean>>>()
    this.drop(2)
        .forEach { row ->
            if (row.isBlank()) {
                boards.add(currentBoard)
                currentBoard = mutableListOf()
            } else {
                currentBoard.add(row.split(" ").filter { it.isNotBlank() }.map { it.trim().toInt() to false })
            }
        }

    return numbers to boards
}

private fun play1(
    numbers: List<Int>,
    initialBoards: List<List<List<Pair<Int, Boolean>>>>
): Pair<Int, List<List<Pair<Int, Boolean>>>> {
    var boards = initialBoards
    return numbers.mapNotNull {
        boards = boards.playNumber(it)
        val winningBoard = boards.getWinner()
        if (winningBoard != null) {
            it to winningBoard
        } else {
            null
        }
    }.first()
}

private fun play2(
    numbers: List<Int>,
    initialBoards: List<List<List<Pair<Int, Boolean>>>>
): Pair<Int, List<List<Pair<Int, Boolean>>>> {
    var boards = initialBoards
    return numbers.mapNotNull {
        if(boards.isEmpty()) {
            null
        } else {
            boards = boards.playNumber(it)
            val winningBoard = boards.getWinner()
            boards = boards.filter { b -> !b.isWinner() }
            if (winningBoard != null) {
                boards = boards.filter { b -> b != winningBoard }
                it to winningBoard
            } else {
                null
            }
        }
    }.last()
}

private fun List<List<List<Pair<Int, Boolean>>>>.playNumber(number: Int) =
    this.map { board ->
        board.map { row ->
            row.map { entry ->
                if (entry.first == number) {
                    entry.first to true
                } else {
                    entry
                }
            }
        }
    }

private fun List<List<List<Pair<Int, Boolean>>>>.getWinner(): List<List<Pair<Int, Boolean>>>? {
    this.map { board ->
        if(board.isWinner()) {
            return board
        }
    }
    return null
}

private fun List<List<Pair<Int, Boolean>>>.isWinner(): Boolean {
    for (entry in this.indices) {
        if (this.checkColumn(entry) || this.checkRow(entry)) {
            return true
        }
    }

    return false
}

private fun List<List<Pair<Int, Boolean>>>.checkColumn(index: Int): Boolean =
    this.all {
        it[index].second
    }

private fun List<List<Pair<Int, Boolean>>>.checkRow(index: Int): Boolean =
    this[index].all { (_, isMarked) ->
        isMarked
    }

private fun List<List<Pair<Int, Boolean>>>.toUnmarkedScore() =
    this.flatMap {
        it.mapNotNull { (number, isMarked) ->
            if(!isMarked) {
                number
            } else {
                null
            }
        }
    }.sum()