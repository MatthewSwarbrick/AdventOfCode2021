fun main() {
    solvePart1()
    solvePart2()
}

private fun solvePart1() {
    val answer = puzzle
        .toRules()
        .toPolymer()
        .toLetterGroups()
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

    val answer = 0
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

private fun Pair<String, Map<String, String>>.toPolymer(steps: Int = 10): String {
    val (template, rules) = this

    var polymer = template
    (1..steps).map {
        polymer = polymer.toPolymer(rules)
    }
    return polymer
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

private fun String.toLetterGroups(): Map<Char, Int> =
    this.groupingBy { it }.eachCount()

private fun String.toParts(): List<String> =
    (0 until this.length - 1).map {
        this.substring(it, it + 2)
    }

private fun Map<Char, Int>.toAnswer(): Int {
    val most =  this.maxByOrNull { it.value }?.value ?: 0
    val least = this.minByOrNull { it.value }?.value ?: 0
    return most - least
}