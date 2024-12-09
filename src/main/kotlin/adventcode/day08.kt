package adventcode

import kotlin.math.max

private data class Antenna(val frequency: Char, val coOrdinates: CoOrdinates)

fun day08Part1(input: String): Int {
    val area = input.asMatrix()
    val maxCoords = CoOrdinates(area[0].size - 1, area.size - 1)
    val antennae = area.flatMapIndexed { y, line ->
        line.mapIndexedNotNull { x, c ->
            if (Regex("""[a-zA-Z0-9]""").matches(c)) Antenna(
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
        val pairs = cartesianProduct<Antenna>(antennae.toSet(), 2).map { Pair(it[0], it[1]) }.removeTwins()
        pairs.map { antinode(Pair(it.first.coOrdinates, it.second.coOrdinates), maxCoords) }
    }.filterNotNull()
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
//    val an = mutableListOf<CoOrdinates>()
//    val pairs = antennaeByFrequency.entries.flatMap { (_, antennae) -> cartesianProduct<Antenna>(antennae.toSet(), 2).map { Pair(it[0], it[1]) }.removeTwins()}
//    pairs.forEach{p -> an.addAll(extendedAntinodes(Pair(p.first.coOrdinates, p.second.coOrdinates), maxCoords))}
    val allAntinodes = antennaeByFrequency.entries.sumOf { (_, antennae) ->
        val pairs = cartesianProduct<Antenna>(antennae.toSet(), 2).map { Pair(it[0], it[1]) }.removeTwins()

//        val pairs = cartesianProduct<Antenna>(antennae.toSet(), 2).map { Pair(it[0], it[1]) }.removeTwins()
//        println(pairs)
//        pairs.forEach{p -> an.addAll(extendedAntinodes(Pair(p.first.coOrdinates, p.second.coOrdinates), maxCoords))}
        pairs.flatMap { extendedAntinodes(Pair(it.first.coOrdinates, it.second.coOrdinates), maxCoords) }.toSet().size + pairs.map{it.first}.toSet().size
    }
//    val a = allAntinodes.toSet()
//    return allAntinodes.toSet().size + (pairs.size/2)
    return allAntinodes
}

private fun <T> Pair<T, T>.either(predicate: (T)-> Boolean) = predicate(this.first) || predicate(this.second)
private fun <T> Pair<T, T>.both(predicate: (T)-> Boolean) = predicate(this.first) && predicate(this.second)
private fun CoOrdinates.inBounds(maxCoords: CoOrdinates) = this.x <= maxCoords.x && this.y <= maxCoords.y && this.x >= 0 && this.y >= 0

private fun extendedAntinodes(coOrdinates: Pair<CoOrdinates, CoOrdinates>, maxCoords: CoOrdinates): List<CoOrdinates> {
    val  list: MutableList<CoOrdinates> = mutableListOf<CoOrdinates>()
    var curr1 = coOrdinates.first
    var curr2 = coOrdinates.second
    while (curr1.inBounds(maxCoords)) {
        list.add(curr1)
        val maybeCurr1 = antinode(Pair(curr1, curr2), maxCoords)
        if (maybeCurr1 == null) break
        curr2 = curr1
        curr1 = maybeCurr1
    }
    curr1 = coOrdinates.first
    curr2 = coOrdinates.second
    while (curr2.inBounds(maxCoords)) {
        list.add(curr2)
        val maybeCurr2 = antinode(Pair(curr2, curr1), maxCoords)
        if (maybeCurr2 == null) break
        curr1 = curr2
        curr2 = maybeCurr2
    }
    return list
}

private fun antinode(coOrdinates: Pair<CoOrdinates, CoOrdinates>, maxCoords: CoOrdinates): CoOrdinates? {
    val (a1, a2) = coOrdinates
    val xDiff = a2.x - a1.x
    val yDiff = a2.y - a1.y
    val c1 = CoOrdinates(a2.x + xDiff, a2.y + yDiff)
    return if(c1.inBounds(maxCoords)) c1 else null
}

private fun List<Pair<Antenna, Antenna>>.removeTwins(): Set<Pair<Antenna, Antenna>> =
    this.fold(mutableSetOf<Pair<Antenna, Antenna>>()) { acc, curr ->
        if (curr.first != curr.second) acc.add(
            curr
        )
        acc
    }.toSet()

