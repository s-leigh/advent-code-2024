package adventcode

import kotlin.math.abs
import kotlin.math.floor
import kotlin.math.log10

fun day11Part1(input: String, blinks: Int): Int {
    val stones = input.split(" ").map { it.toLong() }
    var a = stones
    for (i in 0 until blinks) {
        a = a.flatMap { changeStone(it) }
    }
    return a.size
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

private fun Long.numDigits() = floor(log10(abs(this.toDouble()))).toInt() + 1
