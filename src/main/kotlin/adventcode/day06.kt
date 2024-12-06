package adventcode

import adventcode.CardinalDirection.*

private data class Vector(val coOrdinates: CoOrdinates, val direction: CardinalDirection)

fun day06Part1(input: String): Int {
    val rows = (input asLinesSplitBy "")
    val columns = input.asLines().columns().map{it.map(Char::toString)}
    val (x, y) = Pair(columns.startingIndex(), rows.startingIndex())
    val uniquePositions = travel(columns, rows, columns[x], Vector(CoOrdinates(x, y), NORTH))
    return uniquePositions
}

fun day06Part2(input: String): Int {
    val rows = (input asLinesSplitBy "").toMutableList()
    val columns = input.asLines().columns().map{it.map(Char::toString)}.toMutableList()
    val (x, y) = Pair(columns.startingIndex(), rows.startingIndex())
    val possibleCoordinates = columns.indices.flatMap { colIndex ->
        rows.indices.map { rowIndex ->
            CoOrdinates(colIndex, rowIndex)
        }
    }.filter{it.value(columns) != "#" && it.value(columns) != "^"}

    return possibleCoordinates.count { coord ->
        val newColumns = columns.toMutableList()
        val cols2 = newColumns[coord.x].toMutableList()
        cols2[coord.y] = "#"//.removeAt(coord.y)
        newColumns[coord.x] = cols2
        val newRows = rows.toMutableList()
        val rows2 = newRows[coord.y].toMutableList()
        rows2[coord.x] = "#"
        newRows[coord.y] = rows2
        stuckInLoop(newColumns.toList(), newRows.toList(), columns[x], Vector(CoOrdinates(x, y), NORTH))
    }
}

private fun CoOrdinates.value(columns:  List<List<String>>): String {
    val c = columns[this.x]
    return c[this.y]
}

private fun List<List<String>>.startingIndex() = this.mapIndexed{x, l -> if (l.contains("^")) x else null}
    .filterNotNull().single()

private tailrec fun travel(
    columns: List<List<String>>,
    rows: List<List<String>>,
    currentColumnOrRow: List<String>,
    vector: Vector,
    visited: MutableSet<Vector> = mutableSetOf(vector)
): Int {
    if (!visited.map{it.coOrdinates}.contains(vector.coOrdinates)) visited.add(vector)

    val travellingHorizontally = vector.direction == EAST || vector.direction == WEST
    val relevantIndex = if (travellingHorizontally) vector.coOrdinates.x else vector.coOrdinates.y
    val nextSquareDelta = if (vector.direction == SOUTH || vector.direction == EAST) 1 else -1
    val nextSquareI = relevantIndex + nextSquareDelta

    if (nextSquareI < 0 || nextSquareI >= currentColumnOrRow.size) return visited.size
    if (currentColumnOrRow[nextSquareI] == "#") {
        val nextCR = if (travellingHorizontally) columns[vector.coOrdinates.x] else rows[vector.coOrdinates.y]
        val nextVector = Vector(vector.coOrdinates, turnClockwise(vector.direction))
        return travel(columns, rows, nextCR, nextVector, visited)
    }
    val nextSquare = if (travellingHorizontally) CoOrdinates(vector.coOrdinates.x + nextSquareDelta, vector.coOrdinates.y)
        else CoOrdinates(vector.coOrdinates.x, vector.coOrdinates.y + nextSquareDelta)
    return travel(columns, rows, currentColumnOrRow, Vector(nextSquare, vector.direction), visited)
}

private fun turnClockwise(initialDirection: CardinalDirection): CardinalDirection = when(initialDirection) {
    NORTH -> EAST
    EAST -> SOUTH
    SOUTH -> WEST
    WEST -> NORTH
}


private tailrec fun stuckInLoop(
    columns: List<List<String>>,
    rows: List<List<String>>,
    currentColumnOrRow: List<String>,
    vector: Vector,
    visited: MutableSet<Vector> = mutableSetOf(vector)
): Boolean {
    if (visited.size > 1 && visited.contains(vector)) return true
    visited.add(vector)
    val travellingHorizontally = vector.direction == EAST || vector.direction == WEST
    val relevantIndex = if (travellingHorizontally) vector.coOrdinates.x else vector.coOrdinates.y
    val nextSquareDelta = if (vector.direction == SOUTH || vector.direction == EAST) 1 else -1
    val nextSquareI = relevantIndex + nextSquareDelta

    if (nextSquareI < 0 || nextSquareI >= currentColumnOrRow.size) return false
    if (currentColumnOrRow[nextSquareI] == "#") {
        val nextCR = if (travellingHorizontally) columns[vector.coOrdinates.x] else rows[vector.coOrdinates.y]
        val nextVector = Vector(CoOrdinates(vector.coOrdinates.x, vector.coOrdinates.y), turnClockwise(vector.direction))
        return stuckInLoop(columns, rows, nextCR, nextVector, visited)
    }
    val nextSquare = if (travellingHorizontally) CoOrdinates(vector.coOrdinates.x + nextSquareDelta, vector.coOrdinates.y)
    else CoOrdinates(vector.coOrdinates.x, vector.coOrdinates.y + nextSquareDelta)
    return stuckInLoop(columns, rows, currentColumnOrRow, Vector(nextSquare, vector.direction), visited)
}
