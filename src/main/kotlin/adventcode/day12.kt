package adventcode

private data class Plant(val coords: Coordinates, val name: Char)
private typealias Neighbours = Map<Plant, List<Plant>>

fun day12Part1(input: String): Int {
    val matrix = input.asMatrix()
    val maxCoords = Coordinates(matrix[0].lastIndex, matrix.lastIndex)
    val plants = matrix.flatMapIndexed { y, l -> l.mapIndexed { x, c -> Plant(Coordinates(x, y), c.single()) } }
    val neighbours: Neighbours = plants.fold(mutableMapOf<Plant, List<Plant>>()) { acc, plant ->
        val neighbours = with(plant) {
            listOf(
                Coordinates(coords.x - 1, coords.y),
                Coordinates(coords.x, coords.y - 1),
                Coordinates(coords.x + 1, coords.y),
                Coordinates(coords.x, coords.y + 1)
            )
        }.filter { it.inBounds(maxCoords) }.map { Plant(it, matrix[it.y][it.x].single()) }
        acc[plant] = neighbours
        acc
    }
    val processedPlants: MutableSet<Plant> = mutableSetOf()
    val areas = neighbours.keys.mapNotNull {
        if (!processedPlants.contains(it)) {
            val area = area(it, neighbours).toSet()
            processedPlants.addAll(area)
            area
        } else null
    }.toSet()

    return areas.fold(0) { acc, curr ->
        val edges = curr.sumOf { edges(it, neighbours) }
        acc + (curr.size * edges)
    }
}

private fun area(
    plant: Plant, relationships: Neighbours, visited: MutableList<Plant> = mutableListOf()
): List<Plant> {
    visited.add(plant)
    val neighbours = relationships[plant]!!
    val possibleNext = neighbours.filter { !visited.contains(it) && it.name == plant.name }
    if (possibleNext.isEmpty()) return visited
    return possibleNext.flatMap { area(it, relationships, visited) }
}

private fun edges(plant: Plant, relationships: Neighbours): Int {
    val neighbours = relationships[plant]!!
    val numForeignNeighbours = neighbours.filterNot { it.name == plant.name }.size
    val edgeOfMapDelta = 4 - neighbours.size
    return numForeignNeighbours + edgeOfMapDelta
}
