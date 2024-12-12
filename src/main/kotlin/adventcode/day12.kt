package adventcode

import adventcode.CardinalDirection.*
import adventcode.DiagonalDirection.*

private data class Plant(val coords: Coordinates, val name: Char)
private data class Neighbour(val orthoganal: Map<CardinalDirection, Plant>, val diagonal: Map<DiagonalDirection, Plant>)
private typealias Neighbours = Map<Plant, Neighbour>

fun day12Part1(input: String): Int {
    val matrix = input.asMatrix()
    val maxCoords = Coordinates(matrix[0].lastIndex, matrix.lastIndex)
    val plants = matrix.flatMapIndexed { y, l -> l.mapIndexed { x, c -> Plant(Coordinates(x, y), c.single()) } }
    val neighbours: Neighbours = plants.fold(mutableMapOf<Plant, Neighbour>()) { acc, plant ->
        val (orthoganalCoords, diagonalCoords) = with(plant) {
            Pair(
                mapOf(
                    WEST to Coordinates(coords.x - 1, coords.y),
                    NORTH to Coordinates(coords.x, coords.y - 1),
                    EAST to Coordinates(coords.x + 1, coords.y),
                    SOUTH to Coordinates(coords.x, coords.y + 1)
                ).filter { it.value.inBounds(maxCoords) },
                mapOf(
                    NORTHWEST to Coordinates(coords.x - 1, coords.y - 1),
                    NORTHEAST to Coordinates(coords.x + 1, coords.y - 1),
                    SOUTHEAST to Coordinates(coords.x + 1, coords.y + 1),
                    SOUTHWEST to Coordinates(coords.x - 1, coords.y + 1)
                ).filter { it.value.inBounds(maxCoords) })
        }
//        val neighbours = orthoganalCoords.map { Plant(it.value, matrix[it.value.y][it.value.x].single()) }
        val orthoganalNeighbours = orthoganalCoords.keys.associate{it to Plant(orthoganalCoords[it]!!, matrix[orthoganalCoords[it]!!.y][orthoganalCoords[it]!!.x].single())}
        val diagonalNeighbours = diagonalCoords.keys.associate{it to Plant(diagonalCoords[it]!!, matrix[diagonalCoords[it]!!.y][diagonalCoords[it]!!.x].single())}
        acc[plant] = Neighbour(
            orthoganalNeighbours, diagonalNeighbours
        )
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

fun day12Part2(input: String): Int {
    val matrix = input.asMatrix()
    val maxCoords = Coordinates(matrix[0].lastIndex, matrix.lastIndex)
    val plants = matrix.flatMapIndexed { y, l -> l.mapIndexed { x, c -> Plant(Coordinates(x, y), c.single()) } }
    val neighbours: Neighbours = plants.fold(mutableMapOf<Plant, Neighbour>()) { acc, plant ->
        val (orthoganalCoords, diagonalCoords) = with(plant) {
            Pair(
                mapOf(
                    WEST to Coordinates(coords.x - 1, coords.y),
                    NORTH to Coordinates(coords.x, coords.y - 1),
                    EAST to Coordinates(coords.x + 1, coords.y),
                    SOUTH to Coordinates(coords.x, coords.y + 1)
                ).filter { it.value.inBounds(maxCoords) },
                mapOf(
                    NORTHWEST to Coordinates(coords.x - 1, coords.y - 1),
                    NORTHEAST to Coordinates(coords.x + 1, coords.y - 1),
                    SOUTHEAST to Coordinates(coords.x + 1, coords.y + 1),
                    SOUTHWEST to Coordinates(coords.x - 1, coords.y + 1)
                ).filter { it.value.inBounds(maxCoords) })
        }
//        val neighbours = orthoganalCoords.map { Plant(it.value, matrix[it.value.y][it.value.x].single()) }
        val orthoganalNeighbours = orthoganalCoords.keys.associate{it to Plant(orthoganalCoords[it]!!, matrix[orthoganalCoords[it]!!.y][orthoganalCoords[it]!!.x].single())}
        val diagonalNeighbours = diagonalCoords.keys.associate{it to Plant(diagonalCoords[it]!!, matrix[diagonalCoords[it]!!.y][diagonalCoords[it]!!.x].single())}
        acc[plant] = Neighbour(
            orthoganalNeighbours, diagonalNeighbours
        )
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
        val corners = curr.map{it.isCorner(neighbours[it]!!)}
        acc + (curr.size * corners.sum())
    }
}

private fun Plant.isCorner(neighbours: Neighbour): Int {
    if (setOf(neighbours.orthoganal.values, neighbours.diagonal.values).flatten().all{it.name == this.name}) return 0
    var cornerNum = 0
    if (neighbours.orthoganal.size == 2) cornerNum++ // corner of map
//    if (neighbours.orthoganal.size == 3) {
//
//    }
    val extCornerCheckPairs = setOf(NORTH, NORTH, SOUTH, SOUTH) zip listOf(EAST, WEST, EAST, WEST)
    val associatedDiagonals = mapOf((NORTH to EAST) to NORTHEAST, (NORTH to WEST) to NORTHWEST, (SOUTH to EAST) to SOUTHEAST, (SOUTH to WEST) to SOUTHWEST)
    extCornerCheckPairs.forEach{
        val neighbourPlants = setOf(neighbours.orthoganal[it.first], neighbours.orthoganal[it.second])
        if (neighbourPlants.none{it?.name == this.name}) cornerNum++
        if (neighbourPlants.all{it?.name == this.name}) {
            if (neighbours.diagonal[associatedDiagonals[it]]?.name != this.name) cornerNum++
        }
    }
    return cornerNum
}

private fun area(
    plant: Plant, relationships: Neighbours, visited: MutableList<Plant> = mutableListOf()
): List<Plant> {
    visited.add(plant)
    val neighbours = relationships[plant]!!
    val possibleNext = neighbours.orthoganal.values.filter { !visited.contains(it) && it.name == plant.name }
    if (possibleNext.isEmpty()) return visited
    return possibleNext.flatMap { area(it, relationships, visited) }
}

private fun edges(plant: Plant, relationships: Neighbours): Int {
    val neighbours = relationships[plant]!!
    val numForeignNeighbours = neighbours.orthoganal.values.filterNot { it.name == plant.name }.size
    val edgeOfMapDelta = 4 - neighbours.orthoganal.size
    return numForeignNeighbours + edgeOfMapDelta
}
