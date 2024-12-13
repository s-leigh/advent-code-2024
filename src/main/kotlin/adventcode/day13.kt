package adventcode

private data class CoordinatesLong(val x: Long, val y: Long)
private data class Machine(val prize: CoordinatesLong, val buttons: Pair<CoordinatesLong, CoordinatesLong>)

fun day13Part1(input: String): Long {
    return machines(input).filter { it.isPossible() }.sumOf { presses(it).cost() }
}

fun day13Part2(input: String): Long {
    val prizeOffset = 10000000000000
    return machines(input, prizeOffset).filter { it.isPossible() }.sumOf { presses(it).cost() }
}

private fun machines(input: String, prizeOffset: Long = 0): List<Machine> = input.asLines().windowed(3, 3).map {
    val buttonA = CoordinatesLong(getMatch(it[0], 0), getMatch(it[0], 1))
    val buttonB = CoordinatesLong(getMatch(it[1], 0), getMatch(it[1], 1))
    val prize = CoordinatesLong(getMatch(it[2], 0, prizeOffset), getMatch(it[2], 1, prizeOffset))
    Machine(prize, Pair(buttonA, buttonB))
}

private fun getMatch(line: String, index: Int, offset: Long = 0): Long =
    Regex("""\d+""").findAll(line).map { it.value }.toList()[index].toLong() + offset

private fun Machine.isPossible(): Boolean {
    val (a, b) = buttons
    return prize.x % greatestCommonDivisor(a.x, b.x) == 0L && prize.y % greatestCommonDivisor(a.y, b.y) == 0L
}

private fun presses(machine: Machine): Pair<Long, Long> {
    val (x1, y1) = machine.buttons.first
    val (x2, y2) = machine.buttons.second
    val (px, py) = machine.prize
    val denominator = (x1 * y2) - (x2 * y1)
    val presses = (px * y2 - py * x2) / denominator to (py * x1 - px * y1) / denominator
    val (calculatedX, calculatedY) = presses.first * x1 + presses.second * x2 to presses.first * y1 + presses.second * y2
    return if (calculatedX == machine.prize.x && calculatedY == machine.prize.y) presses else Pair(0, 0)
}

private fun Pair<Long, Long>.cost() = (this.first * 3) + this.second
