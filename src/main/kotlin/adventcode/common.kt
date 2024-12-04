package adventcode

import java.math.BigInteger

fun String.asLines() = this.split('\n').filterNot { it == "" }
infix fun List<String>.splitBy(s: String) = this.map { it.split(s) }
infix fun String.asLinesSplitBy(s: String): List<List<String>> = this.asLines() splitBy s
infix fun String.asIntsSplitBy(s: String): List<List<Int>> = (this asLinesSplitBy s).toInts()
fun List<String>.columns() = this.indices.map { colI -> this.map { row -> row[colI] } }

fun List<String>.toInts() = this.map { it.toInt() }
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
