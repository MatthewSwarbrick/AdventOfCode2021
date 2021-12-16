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
    val input = listOf(
        "NNCB",
        "",
        "CH -> B",
        "HH -> N",
        "CB -> H",
        "NH -> C",
        "HB -> C",
        "HC -> B",
        "HN -> C",
        "NN -> C",
        "BH -> H",
        "NC -> B",
        "NB -> B",
        "BN -> B",
        "BB -> N",
        "BC -> B",
        "CC -> N",
        "CN -> C"
    )

    val answer = input
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

private fun Pair<String, Map<String, String>>.toPolymer(steps: Int = 10): Map<Char, Long> {
    val (template, rules) = this
    var polymer = template

    val letterCounts = mutableMapOf<Char, Long>()

    val remainingSteps = polymer.mapIndexed { index, _ -> index to steps}.toMap().toMutableMap()

    do {

        val maxPolymerSize = 2_048_000
        val polymerSize = polymer.length.coerceAtMost(maxPolymerSize)
        if(polymer.length <= 1) {
            val lastLetter = polymer[0]
            val existingCount = letterCounts[lastLetter] ?: 0
            letterCounts[lastLetter] = existingCount + 1
            break
        }

        val lastIndex = polymer.lastIndex
        val remainingStepsForLastIndex = remainingSteps[lastIndex]!!

        if(remainingStepsForLastIndex <= 0) {
            val lastLetter = polymer[lastIndex]
            val existingCount = letterCounts[lastLetter] ?: 0
            letterCounts[lastLetter] = existingCount + 1
            polymer = polymer.substring(0, lastIndex)
            continue
        }

        val existingPolymer = polymer.substring(0, polymer.length - polymerSize)
        val endPair = polymer.substring(polymer.length - polymerSize, polymer.length)
        val newEnd = endPair.toPolymer(rules)
        polymer = "$existingPolymer$newEnd"

        (existingPolymer.length + 1 .. polymer.length).map { index ->
            remainingSteps[index] = remainingStepsForLastIndex - 1
        }
    }
    while (remainingSteps.values.any { it > 0 })

    return letterCounts
}

private fun String.toPolymer(rules: Map<String, String>): String {
    val templateParts = this.toParts()
    return templateParts.mapIndexed { index, part ->
        val newLetter = rules[part]
        if(index < templateParts.lastIndex) {
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

private fun Map<Char, Long>.toAnswer(): Long {
    val most =  this.maxByOrNull { it.value }?.value ?: 0
    val least = this.minByOrNull { it.value }?.value ?: 0
    return most - least
}