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
    val answer = puzzle
        .toCaves()
        .toPaths(allowSmallCaveTwoVisits = true)
        .count()

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

private fun Map<Cave, Set<Cave>>.toPaths(allowSmallCaveTwoVisits: Boolean = false): List<List<Cave>> {
    val startCave = this.keys.first { it.name == "start" }
    val currentPath = listOf(startCave)
    return startCave.toPaths(currentPath, this, allowSmallCaveTwoVisits)
}

private fun Cave.toPaths(currentPath: List<Cave>, caves: Map<Cave, Set<Cave>>, allowSmallCaveTwoVisits: Boolean = false) : List<List<Cave>> =
    caves[this]?.mapNotNull { cave ->
        if(currentPath.contains(cave) && !cave.canVisitMultiple) {
            val alreadyVisitedSmallCaveTwice = currentPath.filter{ !it.canVisitMultiple }.groupingBy { it }.eachCount().any { it.value > 1}
            if(!allowSmallCaveTwoVisits || cave.name == "start" || alreadyVisitedSmallCaveTwice) {
                null
            } else {
                cave.toPaths(currentPath.plus(cave), caves, allowSmallCaveTwoVisits)
            }
        } else if(cave.name == "end") {
            listOf(currentPath.plus(cave))
        } else {
            cave.toPaths(currentPath.plus(cave), caves, allowSmallCaveTwoVisits)
        }
    }?.flatten() ?: listOf(currentPath)

data class Cave(val name: String, val canVisitMultiple: Boolean)