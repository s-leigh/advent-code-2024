package adventcode

fun day04Part1(input: String): Int {
    val inputLines = input.asLines()
    val rows = inputLines.map(String::toList)
    val columns = inputLines.columns()
    val topLeftToBottomRight = columns.indices.map { col ->
        diagonalFrom(0, col, 1, rows)
    } + (1 until rows.size).map { row ->
        diagonalFrom(row, 0, 1, rows)
    }
    val bottomLeftToTopRight = columns.indices.map { col ->
        diagonalFrom(rows.size - 1, col, -1, rows)
    } + (rows.size - 2 downTo 0).map { row ->
        diagonalFrom(row, 0, -1, rows)
    }

    val allLines = listOf(rows, columns, topLeftToBottomRight, bottomLeftToTopRight).flatMap {
        it.map { it.joinToString("") }
    }
    val matches = allLines.map { line -> Regex("""(?=(XMAS))|(?=(SAMX))""").findAll(line) }
    return matches.sumOf { it.toList().size }

}
