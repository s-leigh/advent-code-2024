package adventcode

internal fun diagonalFrom(startRow: Int, startCol: Int, rowStep: Int, matrix: List<List<Char>>): List<Char> =
    generateSequence(startRow to startCol) { (row, col) ->
        val nextRow = row + rowStep
        val nextCol = col + 1
        if (nextRow in matrix.indices && nextCol in matrix.indices)
            nextRow to nextCol
        else null
    }.map { (row, col) -> matrix[row][col] }.toList()

/* Watch out - this only goes in one direction */
internal fun <T> transpose(rows: List<List<T>>): List<List<T>> {
    val maxColumns = rows.maxOfOrNull { it.size } ?: 0
    return (0 until maxColumns).map { colIndex ->
        rows.mapNotNull { row -> row.getOrNull(colIndex) }
    }
}

internal fun <T> cartesianProduct(elements: Set<T>, length: Int = elements.size): List<List<T>> {
    if (length == 0) return listOf(emptyList())
    return elements.flatMap { element ->
        cartesianProduct(elements, length - 1).map { perm ->
            listOf(element) + perm
        }
    }
}
