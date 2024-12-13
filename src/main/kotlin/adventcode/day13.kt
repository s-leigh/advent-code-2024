package adventcode

import kotlin.math.abs

private data class Machine(val prize: Coordinates, val buttons: Pair<Coordinates, Coordinates>)

fun day13Part1(input: String): Int {
    fun getMatch(line: String, index: Int): Int =
        Regex("""\d+""").findAll(line).map { it.value }.toList()[index].toInt()

    val machines = input.asLines().windowed(3, 3).map {
        val buttonA = Coordinates(getMatch(it[0], 0), getMatch(it[0], 1))
        val buttonB = Coordinates(getMatch(it[1], 0), getMatch(it[1], 1))
        val prize = Coordinates(getMatch(it[2], 0), getMatch(it[2], 1))
        Machine(prize, Pair(buttonA, buttonB))
    }
    val possible = machines.filter { it.isPossible() }
    return possible.mapNotNull { presses(it) }.sumOf { it.cost() }
}

private fun Machine.isPossible(): Boolean {
    val (a, b) = buttons
    return prize.x % greatestCommonDivisor(a.x, b.x) == 0 && prize.y % greatestCommonDivisor(a.y, b.y) == 0
}

private fun presses(machine: Machine): Pair<Int, Int>? {
    val (x1, y1) = machine.buttons.first
    val (x2, y2) = machine.buttons.second
    val (px, py) = machine.prize
    val denominator = (x1 * y2) - (x2 * y1)
    val presses = (px * y2 - py * x2) / denominator to (py * x1 - px * y1) / denominator
    val calculatedX = presses.first * x1 + presses.second * x2
    val calculatedY = presses.first * y1 + presses.second * y2
    if (calculatedX != machine.prize.x || calculatedY != machine.prize.y) return null
    return presses
}

private fun Pair<Int, Int>.cost() = (this.first * 3) + this.second
