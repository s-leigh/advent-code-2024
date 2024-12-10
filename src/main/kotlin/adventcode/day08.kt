package adventcode

private data class Antenna(val frequency: Char, val coOrdinates: CoOrdinates)

fun day08Part1(input: String): Int {
    val area = input.asMatrix()
    val maxCoords = CoOrdinates(area[0].size - 1, area.size - 1)
    val antennaeByFrequency = antennaeByFrequency(area)
    val allAntinodes = antennaeByFrequency.entries.flatMap { (_, antennae) ->
        val pairs = cartesianProduct(antennae.toSet(), 2).map { Pair(it[0], it[1]) }.removeTwins()
        pairs.map { antinode(Pair(it.first.coOrdinates, it.second.coOrdinates), maxCoords) }
    }.filterNotNull()
    return allAntinodes.toSet().size
}

fun day08Part2(input: String): Int {
    val area = input.asMatrix()
    val maxCoords = CoOrdinates(area[0].size - 1, area.size - 1)
    val antennaeByFrequency = antennaeByFrequency(area)
    val allAntinodes = antennaeByFrequency.entries.flatMap { (_, antennae) ->
        val pairs = cartesianProduct(antennae.toSet(), 2).map { Pair(it[0], it[1]) }.removeTwins()
        pairs.flatMap { extendedAntinodes(Pair(it.first.coOrdinates, it.second.coOrdinates), maxCoords) }
    }
    val antennaeCoords = antennaeByFrequency.values.flatMap { it.map { it.coOrdinates } }
    return allAntinodes.plus(antennaeCoords).toSet().size
}

private fun antennaeByFrequency(matrix: List<List<String>>): Map<Char, List<Antenna>> {
    val antennae = matrix.flatMapIndexed { y, line ->
        line.mapIndexedNotNull { x, c ->
            if (Regex("""[a-zA-Z0-9]""").matches(c)) Antenna(
                c.single(),
                CoOrdinates(x, y)
            ) else null
        }
    }
    return antennae.fold(mutableMapOf<Char, MutableList<Antenna>>()) { acc, curr ->
        if (acc.containsKey(curr.frequency)) acc[curr.frequency]!!.add(curr) else acc[curr.frequency] =
            mutableListOf(curr)
        acc
    }
}

private fun antinode(coOrdinates: Pair<CoOrdinates, CoOrdinates>, maxCoords: CoOrdinates): CoOrdinates? {
    val (a1, a2) = coOrdinates
    val newAntinode = CoOrdinates(a2.x + a2.x - a1.x, a2.y + a2.y - a1.y)
    return if (newAntinode.inBounds(maxCoords)) newAntinode else null
}

private tailrec fun extendedAntinodes(
    coOrdinates: Pair<CoOrdinates, CoOrdinates>,
    maxCoords: CoOrdinates,
    list: List<CoOrdinates> = listOf()
): List<CoOrdinates> {
    val antinode = antinode(coOrdinates, maxCoords) ?: return list
    return extendedAntinodes(Pair(coOrdinates.second, antinode), maxCoords, list.plus(antinode))
}
