fun main() {
    solvePart1()
    solvePart2()
}

private fun solvePart1() {
    val (numbers, boards) = puzzle.toBingoBoards()

    val (winningNumber, winningBoard) = play(numbers, boards)
    val unmarkedScore = winningBoard.toUnmarkedScore()

    val answer = winningNumber * unmarkedScore
    println("Solution to part1: $answer")
}

private fun solvePart2() {
    val input = listOf(
        "7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1",
        "",
        "22 13 17 11  0",
        " 8  2 23  4 24",
        "21  9 14 16  7",
        " 6 10  3 18  5",
        " 1 12 20 15 19",
        "",
        " 3 15  0  2 22",
        " 9 18 13 17  5",
        "19  8  7 25 23",
        "20 11 10 24  4",
        "14 21 16 12  6",
        "",
        "14 21 17 24  4",
        "10 16 15  9 19",
        "18  8 23 26 20",
        "22 11 13  6  5",
        " 2  0 12  3  7",
        ""
    )

    val answer = 0
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

private fun play(
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
        for (entry in board.indices) {
            if (board.checkColumn(entry) || board.checkRow(entry)) {
                return board
            }
        }
    }
    return null
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