package adventcode

import kotlin.math.max

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
        pairs.flatMap { antinodes(Pair(it.first.coOrdinates, it.second.coOrdinates), maxCoords) }
    }
    return allAntinodes.toSet().size
}

fun day08Part2(input: String): Int {
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
        pairs.flatMap { extendedAntinodes(Pair(it.first.coOrdinates, it.second.coOrdinates), maxCoords) }
    }
    return allAntinodes.toSet().size
}

private fun <T> Pair<T, T>.either(predicate: (T)-> Boolean) = predicate(this.first) || predicate(this.second)
private fun <T> Pair<T, T>.both(predicate: (T)-> Boolean) = predicate(this.first) && predicate(this.second)
private fun CoOrdinates.inBounds(maxCoords: CoOrdinates) = this.x <= maxCoords.x && this.y <= maxCoords.y && this.x >= 0 && this.y >= 0

private fun extendedAntinodes(coOrdinates: Pair<CoOrdinates, CoOrdinates>, maxCoords: CoOrdinates): List<CoOrdinates> {
    val  list: MutableList<CoOrdinates> = mutableListOf<CoOrdinates>()
    var curr1 = coOrdinates.first
    var curr2 = coOrdinates.second
    while (curr1.inBounds(maxCoords)) {
        val antinodes = antinodes(Pair(curr1, curr2), maxCoords)
        if (antinodes.isEmpty()) break
        list.addAll(antinodes)
        curr1 = antinodes[0]
    }
    while (curr2.inBounds(maxCoords)) {
        val antinodes = antinodes(Pair(curr1, curr2), maxCoords)
        if (antinodes.isEmpty()) break
        list.addAll(antinodes)
        curr2 = antinodes[0]
    }
    return list
}

private fun antinodes(coOrdinates: Pair<CoOrdinates, CoOrdinates>, maxCoords: CoOrdinates): List<CoOrdinates> {
    val (a1, a2) = coOrdinates
    val xDiff = a1.x - a2.x
    val yDiff = a1.y - a2.y
    val c1 = CoOrdinates(a1.x + xDiff, a1.y + yDiff)
    val c2 = CoOrdinates(a2.x - xDiff, a2.y - yDiff)
    return listOf(c1, c2).filter { it.inBounds(maxCoords) }
}

private fun List<Pair<Antenna, Antenna>>.unique(): Set<Pair<Antenna, Antenna>> =
    this.fold(mutableSetOf<Pair<Antenna, Antenna>>()) { acc, curr ->
        if (curr.first != curr.second && !acc.contains(curr) && !acc.contains(Pair(curr.second, curr.first))) acc.add(
            curr
        )
        acc
    }.toSet()

