fun main() {
    solvePart1()
    solvePart2()
}

private fun solvePart1() {
    val answer = puzzle
        .toRules()
        .toPolymer()
        .toAnswer()

    println("Solution to part1: $answer")
}

private fun solvePart2() {
    val answer = puzzle
        .toRules()
        .toPolymer(steps = 40)
        .toAnswer()

    println("Solution to part2: $answer")
}

private fun List<String>.toRules(): Pair<String, Map<String, String>> {
    val template = this[0]
    val rules = this.filter { it.isNotBlank() }
        .drop(1).associate { rule ->
            val parts = rule.split("->").map { it.trim() }
            parts[0] to parts[1]
        }

    return template to rules
}

private fun Pair<String, Map<String, String>>.toPolymer(steps: Int = 10): Map<String, Long> {
    val (template, rules) = this
    var pairs = mutableMapOf(
        *template.toParts().groupBy { it }.map { it.key to it.value.count().toLong() }.toTypedArray()
    )

    val letterCounts =
        mutableMapOf(*template.groupBy { it }.map { "${it.key}" to it.value.count().toLong() }.toTypedArray())

    (1..steps).forEach { _ ->
        val newPairs = mutableMapOf<String, Long>()
        pairs.forEach { (pair, occurrences) ->
            val newLetter = rules[pair]!!
            if (letterCounts.containsKey(newLetter)) {
                letterCounts[newLetter] = (letterCounts[newLetter] ?: 0) + occurrences
            } else {
                letterCounts[newLetter] = occurrences
            }
            val parts = pair.toPolymer(rules).toParts()
            parts.forEach {
                if (newPairs.containsKey(it)) {
                    newPairs[it] = (newPairs[it] ?: 0) + occurrences
                } else {
                    newPairs[it] = occurrences
                }
            }
        }
        pairs = newPairs
    }

    return letterCounts
}

private fun String.toPolymer(rules: Map<String, String>): String {
    val templateParts = this.toParts()
    return templateParts.mapIndexed { index, part ->
        val newLetter = rules[part]
        if (index < templateParts.lastIndex) {
            "${part[0]}$newLetter"
        } else {
            "${part[0]}$newLetter${part[1]}"
        }
    }.joinToString("")
}

private fun String.toParts(): List<String> =
    (0 until this.length - 1).map {
        this.substring(it, it + 2)
    }

private fun Map<String, Long>.toAnswer(): Long {
    val most = this.maxByOrNull { it.value }?.value ?: 0
    val least = this.minByOrNull { it.value }?.value ?: 0
    return most - least
}