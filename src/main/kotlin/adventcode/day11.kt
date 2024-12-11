package adventcode

import kotlin.math.floor
import kotlin.math.log10

fun day11Part1(input: String, blinks: Int): Long {
    val stones = input.split(" ").map { it.toLong() }
    val stoneCount: MutableMap<Long, Long> = stones.fold(mutableMapOf()) { map, stone ->
        if (map.containsKey(stone)) map[stone] = map[stone]!! + 1 else map[stone] = 1; map
    }
    for (i in 0 until blinks) {
        val deltas = mutableMapOf<Long, Long>()
        stoneCount.entries.filter { (_, v) -> v > 0 }.forEach { (s, n) ->
            val newStones = changeStone(s)
            newStones.forEach { deltas.setOrAdd(it, 1 * n) }
            deltas.setOrAdd(s, -1 * n)
        }
        deltas.entries.forEach { (s, d) -> stoneCount.setOrAdd(s, d) }
    }
    return stoneCount.values.sum()
}

private fun changeStone(stone: Long): List<Long> = when {
    stone == 0L -> listOf(1L)
    stone.numDigits().isEven() -> with(stone.toString()) {
        listOf(
            this.substring(0 until this.length / 2).toLong(),
            this.substring(this.length / 2 until this.length).toLong()
        )
    }

    else -> listOf(stone * 2024L)
}

private fun MutableMap<Long, Long>.setOrAdd(k: Long, v: Long) {
    if (this.containsKey(k)) this[k] = this[k]!! + v else this[k] = v
}

private fun Long.numDigits() = floor(log10(this.toDouble())).toInt() + 1
