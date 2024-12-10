package adventcode

private typealias TopographicalMap = List<List<TopographicalElement>>

private data class TopographicalElement(val value: Int, val coOrdinates: CoOrdinates) {
    override fun toString(): String = "$value|${coOrdinates.x},${coOrdinates.y}"
}

fun day10Part1(input: String): Int {
    val map: TopographicalMap = input.asMatrix()
        .toInts()
        .mapIndexed { y, row -> row.mapIndexed { x, elem -> TopographicalElement(elem, CoOrdinates(x, y)) } }
    val trailHeads = map.findByValue(0)
    return trailHeads.sumOf { trailheadEndpoints(it, map).toSet().size }
}

fun day10Part2(input: String): Int {
    val map: TopographicalMap = input.asMatrix()
        .map { it.map { if (it == ".") -1 else it.toInt() } }
        .mapIndexed { y, row -> row.mapIndexed { x, elem -> TopographicalElement(elem, CoOrdinates(x, y)) } }
    val trailHeads = map.findByValue(0)
    return trailHeads.sumOf { trailheadRating(it, map) }
}

private fun trailheadEndpoints(
    pos: TopographicalElement,
    map: TopographicalMap,
    visited: MutableList<TopographicalElement> = mutableListOf()
): List<TopographicalElement> {
    if (pos.value == 9) return listOf(pos)
    visited.add(pos)
    val neighbours = pos.getNeighbours(map).filter { it.value == pos.value + 1 }.filterNot { visited.contains(it) }
    if (neighbours.isEmpty()) return emptyList()
    return neighbours.flatMap { trailheadEndpoints(it, map, visited) }
}

private fun trailheadRating(
    pos: TopographicalElement,
    map: TopographicalMap,
    visited: List<TopographicalElement> = listOf()
): Int {
    if (pos.value == 9) return 1
    val newVisited = visited.toMutableList()
    newVisited.add(pos)
    val neighbours = pos.getNeighbours(map).filter { it.value == pos.value + 1 }.filterNot { newVisited.contains(it) }
    if (neighbours.isEmpty()) return 0
    return neighbours.sumOf { trailheadRating(it, map, newVisited) }
}

private fun TopographicalMap.get(coOrdinates: CoOrdinates) = this[coOrdinates.y][coOrdinates.x]

private fun TopographicalElement.getNeighbours(map: TopographicalMap) = with(this.coOrdinates) {
    listOf(
        CoOrdinates(this.x - 1, this.y),
        CoOrdinates(this.x + 1, this.y),
        CoOrdinates(this.x, this.y - 1),
        CoOrdinates(this.x, this.y + 1),
    )
        .filterNot { it.outOfBounds(map) }
        .map { map.get(it) }
}

private fun TopographicalMap.findByValue(value: Int) = this.flatMap { it.filter { it.value == value } }

private fun CoOrdinates.outOfBounds(map: TopographicalMap) = this.x < 0 || this.y < 0 ||
        this.x > map[0].lastIndex || this.y > map.lastIndex
