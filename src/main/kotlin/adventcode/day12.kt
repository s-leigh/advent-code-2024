package adventcode

private data class Plant(val coords: Coordinates, val name: Char)
private data class Relationship(val plantName: Char, val neighbours: List<Coordinates>)

private typealias Relationships = Map<Coordinates, Relationship>
private typealias Edges = Pair<Coordinates, Int>

fun day12Part1(input: String): Int {
    val matrix = input.asMatrix()
    val maxCoords = Coordinates(matrix[0].lastIndex, matrix.lastIndex)
    val map = matrix.flatMapIndexed { y, l -> l.mapIndexed { x, c -> Plant(Coordinates(x, y), c.single()) } }
    println(map)
    val relationships = map.fold(mutableMapOf<Coordinates, Relationship>()) { acc, plant ->
        with(plant.coords) {
            val neighbours = listOf(
                Coordinates(this.x - 1, this.y), Coordinates(this.x, this.y - 1), Coordinates(this.x + 1, this.y),
                Coordinates(this.x, this.y + 1)
            ).filter { it.inBounds(maxCoords) }
            acc[this] = Relationship(plant.name, neighbours)
        }
        acc
    }

    val e = relationships.keys.flatMap { edges(it, relationships) }.toSet()

    return -1
}

private fun edges(
    coords: Coordinates,
    relationships: Relationships,
    edges: MutableList<Pair<Coordinates, Int>> = mutableListOf(),
    nonEdges: MutableList<Coordinates> = mutableListOf()
): List<Pair<Coordinates, Int>> {
    val thisR = relationships[coords]!!
    val neighbours = thisR.neighbours
    val numForeignNeighbours = neighbours.filterNot{relationships[it]!!.plantName == thisR.plantName}.size
    if (numForeignNeighbours > 0 || neighbours.size < 4) edges.add(Pair(coords, numForeignNeighbours + 4 - neighbours.size))
    else nonEdges.add(coords)

    val toBeProcessed = neighbours.filterNot { edges.map{it.first}.contains(it) || nonEdges.contains(it) }
    if (toBeProcessed.isEmpty()) return edges
    return toBeProcessed.flatMap { edges(it, relationships, edges, nonEdges) }
}
