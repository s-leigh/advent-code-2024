package adventcode

import java.math.BigInteger

private const val NEWLINE_CHAR = '\n'

fun String.asLines() = this.split(NEWLINE_CHAR).filterNot { it == "" }

infix fun String.asLinesSplitBy(s: String): List<List<String>> = this.asLines() splitBy s
infix fun String.asIntsSplitBy(s: String): List<List<Int>> = (this asLinesSplitBy s).toInts()

fun String.asMatrix() = this.asLines().map{it.split("").filterNot{it == ""}}

fun List<String>.columns() = this.indices.map { colI -> this.map { row -> row[colI] } }
infix fun List<String>.splitBy(s: String) = this.map { it.split(s).filterNot{it == ""} }
fun List<String>.toInts() = this.map { it.toInt() }
fun <T> List<T>.headAndTail() = Pair(this[0], this.drop(1))
fun <T> List<T>.middleValue() =
    if (this.size % 2 == 0) throw NotImplementedError("Can't find middle value of even-numbered array") else this[this.size / 2]

@JvmName("toInts2D")
fun List<List<String>>.toInts() = this.map { it.toInts() }

fun Int.isNegative() = this < 0
fun Int.isPositive() = this > 0

fun Pair<Int, Int>.difference() = this.second - this.first
fun Pair<BigInteger, BigInteger>.product() = this.first * this.second

fun diagonalFrom(startRow: Int, startCol: Int, rowStep: Int, matrix: List<List<Char>>): List<Char> =
    generateSequence(startRow to startCol) { (row, col) ->
        val nextRow = row + rowStep
        val nextCol = col + 1
        if (nextRow in matrix.indices && nextCol in matrix.indices)
            nextRow to nextCol
        else null
    }.map { (row, col) -> matrix[row][col] }.toList()

data class Vector(val coOrdinates: CoOrdinates, val direction: CardinalDirection)

interface Directional {
    val isHorizontal: Boolean
    val isVertical: Boolean
}
enum class CardinalDirection: Directional {
    NORTH{
        override val isHorizontal = false
        override val isVertical = true
    },
    SOUTH{
        override val isHorizontal = false
        override val isVertical = true
    },
    EAST {
        override val isHorizontal = true
        override val isVertical = false
    },
    WEST{
        override val isHorizontal = true
        override val isVertical = false
    },
}

data class CoOrdinates(val x: Int, val y: Int)

/* Watch out - this only goes in one direction */
fun <T> transpose(rows: List<List<T>>): List<List<T>> {
    val maxColumns = rows.maxOfOrNull { it.size } ?: 0
    return (0 until maxColumns).map { colIndex ->
        rows.mapNotNull { row -> row.getOrNull(colIndex) }
    }
}