import kotlin.math.ceil
import kotlin.math.floor

fun main() {
    solvePart1()
    solvePart2()
}

private fun solvePart1() {
    val input = listOf(
        //larger example - this isn't working - explode removes the very first pair which then corrupts the output
        // it did the following
        //AFTER SPLIT
        //[[[[4,0],[5,0]],[[[4,5],[2,6]],[9,5]]],[7,[[[3,7],[4,3]],[[6,3],[8,8]]]]]
        //
        //AFTER EXPLODE
        //[[[5,4],[[0,[7,6]],[9,5]]],[7,[[[3,7],[4,3]],[[6,3],[8,8]]]]]
        //THIS NEEDS DEBUGGING
        "[[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]]",
        "[7,[[[3,7],[4,3]],[[6,3],[8,8]]]]",
//        "[[2,[[0,8],[3,4]]],[[[6,7],1],[7,[1,6]]]]",
//        "[[[[2,4],7],[6,[0,5]]],[[[6,8],[2,8]],[[2,1],[4,5]]]]",
//        "[7,[5,[[3,8],[1,4]]]]",
//        "[[2,[2,2]],[8,[8,1]]]",
//        "[2,9]",
//        "[1,[[[9,3],9],[[9,0],[0,7]]]]",
//        "[[[5,[7,4]],7],1]",
//        "[[[[4,2],2],6],[8,7]]"

        //happy path explode and split
//        "[[[0,[5,8]],[[1,7],[9,6]]],[[4,[1,2]],[[1,4],2]]]",
//        "[[[5,[2,8]],4],[5,[[9,9],0]]]",
//        "[6,[[[6,2],[5,6]],[[7,6],[4,7]]]]",
//        "[[[6,[0,7]],[0,9]],[4,[9,[9,0]]]]",
//        "[[[7,[6,4]],[3,[1,3]]],[[[5,5],1],9]]",
//        "[[6,[[7,3],[3,2]]],[[[3,8],[5,7]],4]]",
//        "[[[[5,4],[7,7]],8],[[8,3],8]]",
//        "[[9,3],[[9,9],[6,[4,9]]]]",
//        "[[2,[[7,7],7]],[[5,8],[[9,3],[0,2]]]]",
//        "[[[[5,2],5],[8,[3,7]]],[[5,[7,5]],[4,4]]]"
    )
    val answer = input
        .toSnailPairs()
        .toSum()
//        .toMagnitudes()

    answer.map {
        println(it)
        println("---------------------")
    }
    println("Solution to part1: $answer")
}

private fun solvePart2() {
    val answer = 0

    println("Solution to part2: $answer")
}

fun List<String>.toSnailPairs() = this.map { it.toSnailPair() }

fun String.toSnailPair(side: Side? = null): SnailPair {
    val snailPair = this.substring(1, this.lastIndex)
    var leftSnailPair: SnailPair? = null
    var leftNumber: Int? = null
    if (snailPair.startsWith("[")) {
        leftSnailPair = snailPair.toLeftSnailPair()
    } else {
        leftNumber = snailPair.split(",")[0].toInt()
    }

    var rightSnailPair: SnailPair? = null
    var rightNumber: Int? = null
    if (snailPair.endsWith("]")) {
        rightSnailPair = snailPair.toRightSnailPair()
    } else {
        rightNumber = snailPair.split(",").last().toInt()
    }

    return SnailPair(side, leftSnailPair, leftNumber, rightSnailPair, rightNumber)
}

fun List<SnailPair>.toSum(): List<SnailPair> =
    this.fold<SnailPair, List<SnailPair>>(listOf()) { acc, snailPair ->
        if (acc.isEmpty()) {
            acc.plus(snailPair)
        } else {
            acc.plus(
                SnailPair(
                    leftPair = acc.last().copy(side = Side.LEFT),
                    rightPair = snailPair.copy(side = Side.RIGHT)
                ).reduce()
            )
        }
    }
        .drop(1)

fun SnailPair.explode(): SnailPair {
    val pathToLeftMostNestedPair = this.toLeftMostPairPath(4)
    return if (pathToLeftMostNestedPair.isEmpty()) {
        this
    } else {
        pathToLeftMostNestedPair
            .updatePairsAfterExplode()
            .replaceExplodedPairs()
    }
}

fun SnailPair.split(): SnailPair {
    val pathToLeftMostNestedPair = this.toLeftMostPairPath()
    return if (pathToLeftMostNestedPair.isEmpty()) {
        this
    } else {
        val pairToSplit = pathToLeftMostNestedPair.last()
        if (pairToSplit.leftNumber != null && pairToSplit.leftNumber >= 10) {
            val newLeftNumber = floor(pairToSplit.leftNumber / 2.0).toInt()
            val newRightNumber = ceil(pairToSplit.leftNumber / 2.0).toInt()
            val newPair = SnailPair(Side.LEFT, leftNumber = newLeftNumber, rightNumber = newRightNumber)
            pathToLeftMostNestedPair.replaceSplitPair(pairToSplit.copy(leftNumber = null, leftPair = newPair))
        } else {
            val newLeftNumber = floor((pairToSplit.rightNumber ?: 0) / 2.0).toInt()
            val newRightNumber = ceil((pairToSplit.rightNumber ?: 0) / 2.0).toInt()
            val newPair = SnailPair(Side.RIGHT, leftNumber = newLeftNumber, rightNumber = newRightNumber)
            pathToLeftMostNestedPair.replaceSplitPair(pairToSplit.copy(rightNumber = null, rightPair = newPair))
        }
    }
}

private fun SnailPair.shouldExplodeFirst(): Boolean {
    val explodePath = this.toLeftMostPairPath(4)
    return explodePath.isNotEmpty()
}

private fun String.toLeftSnailPair(): SnailPair {
    val closingBracketIndex = this.foldIndexed(0) { index, acc, c ->
        var newAcc = acc
        if (c == '[') {
            newAcc = acc + 1
        } else if (c == ']') {
            newAcc = acc - 1
        }

        if (newAcc == 0) {
            return this.substring(0, index + 1).toSnailPair(Side.LEFT)
        } else {
            newAcc
        }
    }

    return this.substring(0, closingBracketIndex + 1).toSnailPair(Side.LEFT)
}

private fun String.toRightSnailPair(): SnailPair {
    val startingBracketIndex = this.reversed().foldIndexed(0) { index, acc, c ->
        var newAcc = acc
        if (c == '[') {
            newAcc = acc - 1
        } else if (c == ']') {
            newAcc = acc + 1
        }

        if (newAcc == 0) {
            return this.substring(this.length - (index + 1), this.length).toSnailPair(Side.RIGHT)
        } else {
            newAcc
        }
    }

    return this.substring(this.length - (startingBracketIndex + 1), this.length).toSnailPair(Side.RIGHT)
}

private fun SnailPair.reduce(): SnailPair {
    var reduced = this
    println()
    println("START")
    println(reduced)
    println()
    do {
        var valueToReduce = reduced
        if(valueToReduce.shouldExplodeFirst()) {
            println("Exploding...")
            valueToReduce = valueToReduce.explode()
            println()
            println("AFTER EXPLODE")
            println(valueToReduce)
            println()
        } else {
            valueToReduce = valueToReduce.split()
            println()
            println("AFTER SPLIT")
            println(valueToReduce)
            println()
        }

        if (reduced != valueToReduce) {
            reduced = valueToReduce
        } else {
            println()
            println("AFTER REDUCE")
            println(reduced)
            println()
            return reduced
        }
    } while (true)
}

private fun List<SnailPair>.updatePairsAfterExplode(): List<SnailPair> {
    val explodingPair = this.last()

    var pairPath = this.take(this.lastIndex).toMutableList()


    pairPath = pairPath.updateFirstLeftNumber(explodingPair).toMutableList()
    pairPath = pairPath.updateFirstRightNumber(explodingPair).toMutableList()

    val pairToExplodeInto = pairPath[pairPath.lastIndex]
    if (explodingPair.side == Side.LEFT) {
        val updatedPair = pairToExplodeInto.copy(leftNumber = 0, leftPair = null)
        pairPath[pairPath.lastIndex] = updatedPair
    } else {
        val updatedPair = pairToExplodeInto.copy(rightNumber = 0, rightPair = null)
        pairPath[pairPath.lastIndex] = updatedPair
    }

    return pairPath
}

private fun List<SnailPair>.updateFirstLeftNumber(explodedPair: SnailPair): List<SnailPair> {
    val path = this.toMutableList()
    var prevLayerSide = explodedPair.side
    var numberFound = false
    var index = this.lastIndex
    while (index >= 0 && !numberFound) {
        val layer = path[index]
        if (prevLayerSide == Side.RIGHT) {
            if (layer.leftNumber != null) {
                path[index] = layer.copy(leftNumber = layer.leftNumber + (explodedPair.leftNumber ?: 0))
                numberFound = true
            } else {
                if (layer.leftPair != null) {
                    val newLeftPairForLayer = layer.leftPair.updateMostRightNumber(explodedPair.leftNumber ?: 0)
                    if (newLeftPairForLayer != layer.leftPair) {
                        path[index] = layer.copy(leftPair = newLeftPairForLayer)
                        numberFound = true
                    }
                }
            }
        }
        prevLayerSide = layer.side
        index--
    }
    return path
}

private fun List<SnailPair>.updateFirstRightNumber(explodedPair: SnailPair): List<SnailPair> {
    val path = this.toMutableList()
    var prevLayerSide = explodedPair.side
    var numberFound = false
    var index = this.lastIndex
    while (index >= 0 && !numberFound) {
        val layer = path[index]
        if (prevLayerSide == Side.LEFT) {
            if (layer.rightNumber != null) {
                path[index] = layer.copy(rightNumber = layer.rightNumber + (explodedPair.rightNumber ?: 0))
                numberFound = true
            } else {
                if (layer.rightPair != null) {
                    val newRightPairForLayer = layer.rightPair.updateMostLeftNumber(explodedPair.rightNumber ?: 0)
                    if (newRightPairForLayer != layer.rightPair) {
                        path[index] = layer.copy(rightPair = newRightPairForLayer)
                        numberFound = true
                    }
                }
            }

        }
        prevLayerSide = layer.side
        index--
    }
    return path
}

private fun SnailPair.updateMostRightNumber(amountToIncreaseBy: Int): SnailPair =
    if (this.rightNumber != null) {
        this.copy(rightNumber = this.rightNumber + amountToIncreaseBy)
    } else if(this.rightPair != null){
        this.copy(rightPair = this.rightPair.updateMostRightNumber(amountToIncreaseBy))
    } else {
        this
    }

private fun SnailPair.updateMostLeftNumber(amountToIncreaseBy: Int): SnailPair =
    if (this.leftNumber != null) {
        this.copy(leftNumber = this.leftNumber + amountToIncreaseBy)
    } else if(this.leftPair != null){
        this.copy(leftPair = this.leftPair.updateMostLeftNumber(amountToIncreaseBy))
    } else {
        this
    }

private fun List<SnailPair>.replaceExplodedPairs(): SnailPair =
    this.reversed().drop(1).fold(this.last()) { acc, snailPair ->
        when (acc.side) {
            Side.LEFT -> snailPair.copy(leftPair = acc)
            Side.RIGHT -> snailPair.copy(rightPair = acc)
            null -> acc
        }
    }

private fun List<SnailPair>.replaceSplitPair(pairToReplace: SnailPair): SnailPair =
    this.reversed().drop(1).fold(pairToReplace) { acc, snailPair ->
        when (acc.side) {
            Side.LEFT -> snailPair.copy(leftPair = acc)
            Side.RIGHT -> snailPair.copy(rightPair = acc)
            null -> acc
        }
    }

private fun SnailPair.toLeftMostPairPath(requiredDepth: Int): List<SnailPair> =
    if (requiredDepth <= 1 && this.leftPair == null && this.rightPair == null) {
        listOf(this)
    } else {
        if (this.leftPair != null && this.leftPair.toDepth() >= 1.coerceAtLeast(requiredDepth)) {
            listOf(this).plus(this.leftPair.toLeftMostPairPath(requiredDepth - 1))
        } else if (this.rightPair != null && this.rightPair.toDepth() >= 1.coerceAtLeast(requiredDepth)) {
            listOf(this).plus(this.rightPair.toLeftMostPairPath(requiredDepth - 1))
        } else {
            listOf()
        }
    }

private fun SnailPair.toLeftMostPairPath(): List<SnailPair> =
    if (this.leftPair != null && this.leftPair.containsSplit()) {
        listOf(this).plus(this.leftPair.toLeftMostPairPath())
    } else if ((this.leftNumber != null && this.leftNumber >= 10) || (this.rightNumber != null && this.rightNumber >= 10)) {
        listOf(this)
    } else {
        if (this.rightPair != null && this.rightPair.containsSplit()) {
            listOf(this).plus(this.rightPair.toLeftMostPairPath())
        } else {
            listOf()
        }
    }

private fun SnailPair.toDepth(): Int {
    val leftDepth = 1 + (this.leftPair?.toDepth() ?: 0)
    val rightDepth = 1 + (this.rightPair?.toDepth() ?: 0)
    return leftDepth.coerceAtLeast(rightDepth)
}

private fun SnailPair.containsSplit(): Boolean =
    if ((this.leftNumber != null && this.leftNumber >= 10) || (this.rightNumber != null && this.rightNumber >= 10)) {
        true
    } else {
        (this.leftPair != null && this.leftPair.containsSplit()) || (this.rightPair != null && this.rightPair.containsSplit())
    }

data class SnailPair(
    val side: Side? = null,
    val leftPair: SnailPair? = null,
    val leftNumber: Int? = null,
    val rightPair: SnailPair? = null,
    val rightNumber: Int? = null
) {
    override fun toString(): String {
        var value = "["
        if (this.leftNumber != null) {
            value += "${this.leftNumber},"
        } else {
            value += this.leftPair?.toString() ?: ""
            value += ","
        }
        if (this.rightNumber != null) {
            value += "${this.rightNumber}]"
        } else {
            value += this.rightPair?.toString() ?: ""
            value += "]"
        }

        return value
    }
}

enum class Side {
    LEFT,
    RIGHT
}