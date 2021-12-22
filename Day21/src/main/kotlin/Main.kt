fun main() {
    solvePart1()
    solvePart2()
}

private fun solvePart1() {
    var (player1Starting, player2Starting) = puzzle.toStartingPositions()

    var player1TotalScore = 0
    var player2TotalScore = 0

    var currentDieValue = 0
    var rollCount = 0

    while(true) {
        val (newDieScoreForPlayer1, newPlayer1Score) = currentDieValue.toNewDieScore()
        rollCount+=3
        player1Starting = player1Starting.toNewPosition(newPlayer1Score)
        player1TotalScore += player1Starting
        currentDieValue = newDieScoreForPlayer1
        if(player1TotalScore >= 1000) {
            break
        }

        val (newDieScoreForPlayer2, newPlayer2Score) = currentDieValue.toNewDieScore()
        rollCount+=3
        player2Starting = player2Starting.toNewPosition(newPlayer2Score)
        player2TotalScore += player2Starting
        currentDieValue = newDieScoreForPlayer2
        if(player2TotalScore >= 1000) {
            break
        }
    }

    val answer = rollCount * player1TotalScore.coerceAtMost(player2TotalScore)
    println("Solution to part1: $answer")
}

private fun solvePart2() {
    val input = listOf(
        "Player 1 starting position: 4",
        "Player 2 starting position: 8"
    )
    val answer = 0
    println("Solution to part2: $answer")
}

private fun List<String>.toStartingPositions(): Pair<Int, Int> {
    val player1Starting = this[0].split(":")[1].trim().toInt()
    val player2Starting = this[1].split(":")[1].trim().toInt()

    return player1Starting to player2Starting
}

private fun Int.toNewDieScore(): Pair<Int, Int> {
    var dieValue = this
    var score = 0
    dieValue += 1
    score += dieValue
    if(dieValue == 100) {
        dieValue = 0
    }

    dieValue += 1
    score += dieValue
    if(dieValue == 100) {
        dieValue = 0
    }

    dieValue += 1
    score += dieValue
    if(dieValue == 100) {
        dieValue = 0
    }

    return dieValue to score
}

private fun Int.toNewPosition(newScore: Int) : Int {
    val newPosition = this + newScore
    return if(newPosition > 10){
        if(newPosition % 10 == 0) {
            10
        } else {
            newPosition % 10
        }
    } else {
        newPosition
    }
}