package adventcode

import adventcode.CardinalDirection.*

fun day06Part1(input: String): Int {
    val rows = (input asLinesSplitBy "")
    val columns = input.asLines().columns().map{it.map(Char::toString)}
    val (x, y) = Pair(columns.startingIndex(), rows.startingIndex())
    val uniquePositions = travel(columns, rows, columns[x], NORTH, CoOrdinates(x, y))
    return uniquePositions
}

private fun List<List<String>>.startingIndex() = this.mapIndexed{x, l -> if (l.contains("^")) x else null}
    .filterNotNull().single()

private tailrec fun travel(
    columns: List<List<String>>,
    rows: List<List<String>>,
    currentColumnOrRow: List<String>,
    direction: CardinalDirection,
    coOrdinates: CoOrdinates,
    visited: MutableSet<CoOrdinates> = mutableSetOf(coOrdinates)
): Int {
    visited.add(coOrdinates)
    val travellingHorizontally = direction == EAST || direction == WEST
    val relevantIndex = if (travellingHorizontally) coOrdinates.x else coOrdinates.y
    val nextSquareDelta = if (direction == SOUTH || direction == EAST) 1 else -1
    val nextSquareI = relevantIndex + nextSquareDelta

    if (nextSquareI < 0 || nextSquareI >= currentColumnOrRow.size) return visited.size
    if (currentColumnOrRow[nextSquareI] == "#") {
        val nextCR = if (travellingHorizontally) columns[coOrdinates.x] else rows[coOrdinates.y]
        return travel(columns, rows, nextCR, turnClockwise(direction), coOrdinates, visited)
    }
    val nextSquare = if (travellingHorizontally) CoOrdinates(coOrdinates.x + nextSquareDelta, coOrdinates.y)
        else CoOrdinates(coOrdinates.x, coOrdinates.y + nextSquareDelta)
    return travel(columns, rows, currentColumnOrRow, direction, nextSquare, visited)
}

private fun turnClockwise(initialDirection: CardinalDirection): CardinalDirection = when(initialDirection) {
    NORTH -> EAST
    EAST -> SOUTH
    SOUTH -> WEST
    WEST -> NORTH
}
