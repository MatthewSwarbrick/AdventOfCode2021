fun main() {
    solvePart1()
    solvePart2()
}

private fun solvePart1() {
    val answer = puzzle
        .toCaves()
        .toPaths()
        .count()

    println("Solution to part1: $answer")
}

private fun solvePart2() {
    val input = listOf(
        "start-A",
        "start-b",
        "A-c",
        "A-b",
        "b-d",
        "A-end",
        "b-end"
    )

    val answer = 0
    println("Solution to part2: $answer")
}

private fun List<String>.toCaves() : Map<Cave, Set<Cave>> {
    val caveMap = mutableMapOf<Cave, Set<Cave>>()
    this.map {
        val caves = it.split("-")
            .map { part -> part.trim() }
            .map { cave -> Cave(cave, cave.all {c -> c.isUpperCase() }) }

        caves.forEach { cave ->
            val joiningCave = caves.first { c -> c != cave }
            val currentCaves = caveMap[cave]
            if(currentCaves != null) {
                caveMap[cave] = currentCaves.plus(joiningCave)
            } else {
                caveMap[cave] = setOf(joiningCave)
            }
        }
    }
    return caveMap
}

private fun Map<Cave, Set<Cave>>.toPaths(): List<List<Cave>> {
    val startCave = this.keys.first { it.name == "start" }
    val currentPath = listOf(startCave)
    return startCave.toPaths(currentPath, this)
}

private fun Cave.toPaths(currentPath: List<Cave>, caves: Map<Cave, Set<Cave>>) : List<List<Cave>> =
    caves[this]?.mapNotNull { cave ->
        if(currentPath.contains(cave) && !cave.canVisitMultiple) {
            null
        } else if(cave.name == "end") {
            listOf(currentPath.plus(cave))
        } else {
            cave.toPaths(currentPath.plus(cave), caves)
        }
    }?.flatten() ?: listOf(currentPath)

data class Cave(val name: String, val canVisitMultiple: Boolean)