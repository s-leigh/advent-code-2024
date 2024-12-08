package adventcode

private data class Antenna(val frequency: Char, val coOrdinates: CoOrdinates)

fun day08Part1(input: String): Int {
    val area = input.asMatrix()
    val maxCoords = CoOrdinates(area[0].size - 1, area.size - 1)
    val antennae = area.flatMapIndexed { y, line ->
        line.mapIndexedNotNull { x, c ->
            if (c != ".") Antenna(
                c.single(),
                CoOrdinates(x, y)
            ) else null
        }
    }
    val antennaeByFrequency = antennae.fold(mutableMapOf<Char, MutableList<Antenna>>()) { acc, curr ->
        if (acc.containsKey(curr.frequency)) acc[curr.frequency]!!.add(curr) else acc[curr.frequency] =
            mutableListOf(curr)
        acc
    }
    val allAntinodes = antennaeByFrequency.entries.flatMap { (_, antennae) ->
        val pairs = cartesianProduct<Antenna>(antennae.toSet(), 2).map { Pair(it[0], it[1]) }.unique()
        pairs.flatMap { antinodes(it, maxCoords) }
    }
    return allAntinodes.toSet().size
}

private fun antinodes(antennaePair: Pair<Antenna, Antenna>, maxCoords: CoOrdinates): List<CoOrdinates> {
    val (a1, a2) = antennaePair
    val xDiff = a1.coOrdinates.x - a2.coOrdinates.x
    val yDiff = a1.coOrdinates.y - a2.coOrdinates.y
    val c1 = CoOrdinates(a1.coOrdinates.x + xDiff, a1.coOrdinates.y + yDiff)
    val c2 = CoOrdinates(a2.coOrdinates.x - xDiff, a2.coOrdinates.y - yDiff)
    return listOf(c1, c2).filterNot { it.x > maxCoords.x || it.y > maxCoords.y || it.x < 0 || it.y < 0 }
}

private fun List<Pair<Antenna, Antenna>>.unique(): Set<Pair<Antenna, Antenna>> =
    this.fold(mutableSetOf<Pair<Antenna, Antenna>>()) { acc, curr ->
        if (curr.first != curr.second && !acc.contains(curr) && !acc.contains(Pair(curr.second, curr.first))) acc.add(
            curr
        )
        acc
    }.toSet()

