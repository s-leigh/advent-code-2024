package adventcode

import adventcode.CardinalDirection.*

fun day06Part1(input: String): Int {
    val rows = (input asLinesSplitBy "")
    val columns = input.asLines().columns().map{it.map(Char::toString)}
    val startingPosition = Vector(CoOrdinates(columns.startingIndex(), rows.startingIndex()), NORTH)
    val uniquePositions = travel(rows, startingPosition)
    return uniquePositions.size
}

fun day06Part2(input: String): Int {
    val rows = (input asLinesSplitBy "").toMutableList()
    val columns = input.asLines().columns().map{it.map(Char::toString)}.toMutableList()
    val startingPosition = Vector(CoOrdinates(columns.startingIndex(), rows.startingIndex()), NORTH)
    val possibleCoordinates = travel(rows, startingPosition).map{it.coOrdinates}

    return possibleCoordinates.count { coOrd ->
        val newRows = rows.toMutableList()
        val rowToChange = newRows[coOrd.y].toMutableList()
        rowToChange[coOrd.x] = "#"
        newRows[coOrd.y] = rowToChange

        stuckInLoop(newRows, startingPosition)
    }
}

private fun List<List<String>>.startingIndex() = this.mapIndexed{x, l -> if (l.contains("^")) x else null}
    .filterNotNull().single()

private fun turnClockwise(initialDirection: CardinalDirection): CardinalDirection = when(initialDirection) {
    NORTH -> EAST
    EAST -> SOUTH
    SOUTH -> WEST
    WEST -> NORTH
}

private tailrec fun stuckInLoop(
    rows: List<List<String>>,
    vector: Vector,
    stepsTaken: Int = 0
): Boolean {
    if (stepsTaken > rows.size * rows.size) return true
    val next = nextVector(rows, vector)
    return if (next == null) false else stuckInLoop(rows, next, stepsTaken+1)
}

private tailrec fun travel(
    rows: List<List<String>>,
    vector: Vector,
    visited: MutableSet<Vector> = mutableSetOf(vector)
): Set<Vector> {
    if (!visited.map{it.coOrdinates}.contains(vector.coOrdinates)) visited.add(vector)
    val next = nextVector(rows, vector)
    return if (next == null) visited else travel(rows, next, visited)
}

private fun nextVector(rows: List<List<String>>, vector: Vector): Vector? {
    val relevantIndex = if (vector.direction.isHorizontal) vector.coOrdinates.x else vector.coOrdinates.y
    val delta = if (vector.direction == SOUTH || vector.direction == EAST) 1 else -1

    val nextSquareI = relevantIndex + delta
    if (nextSquareI < 0 || nextSquareI >= rows.size) return null // gone off map

    val nextPosition = if (vector.direction.isHorizontal) CoOrdinates(nextSquareI, vector.coOrdinates.y)
        else CoOrdinates(vector.coOrdinates.x, nextSquareI)

    return if (rows[nextPosition.y][nextPosition.x] == "#") Vector(vector.coOrdinates, turnClockwise(vector.direction))
        else Vector(nextPosition, vector.direction)
}
