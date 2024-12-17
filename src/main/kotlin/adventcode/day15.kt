package adventcode

import adventcode.CardinalDirection.*

fun day15Part1(input: String): Int {
    val raw = input.split("\n\n")
    val rows = raw[0].asMatrix().map{it.toMutableList()}.toMutableList()
//    val columns = rows.transposed().map{it.toMutableList()}.toMutableList()
//    val rows = raw[0] asLinesSplitBy ""
//    val columns = raw[0].columns().map{it.map{it.toString()}}
    val instructions = parseInstructions(raw[1].lines().single())
    val startingY = rows.indexOfFirst{ it.contains("@") }
    val startingX = rows[startingY].indexOf("@")
    rows[startingY][startingX] = "."
//    columns[startingX][startingY] = "."
    var currPos = Coordinates(startingX, startingY)
    instructions.forEach{dir ->
        val relevantPos = if (dir.isVertical) currPos.y else currPos.x
        val rowOrColumn = if (dir.isVertical) rows.getColumn(currPos.x) else rows.getRow(currPos.y)
//        val relevantLine = if (dir.isVertical) columns[relevantPos] else rows[relevantPos]
        if (isPossible(dir, rowOrColumn, relevantPos)) {
            if (mustMoveBox(dir, rowOrColumn, relevantPos)) {
                val res = moveBoxes(dir, rowOrColumn, relevantPos)
                if (dir.isVertical) {
                    res.forEachIndexed{i, updatedElem -> rows[i][relevantPos] = updatedElem}
                } else rows[currPos.y] = res
            }
            currPos = when(dir) {
                NORTH -> Coordinates(currPos.x, currPos.y - 1)
                SOUTH -> Coordinates(currPos.x, currPos.y + 1)
                EAST -> Coordinates(currPos.x + 1, currPos.y)
                WEST -> Coordinates(currPos.x - 1, currPos.y)
            }
        }
        printMatrix(rows, currPos)
    }

    return -1
}

private fun List<List<String>>.getRow(i: Int) = this[i].toMutableList()
private fun List<List<String>>.getColumn(i: Int) = this.transposed()[i].toMutableList()

private fun printMatrix(rows: List<List<String>>, currPos: Coordinates) {
    rows.forEachIndexed{i, r ->
        if (i != currPos.y) println(r.joinToString(""))
        else {
            val ur = r.toMutableList()
            ur[currPos.x] = "@"
            println(ur.joinToString(""))
        }
    }
    println("currpos: $currPos\n")
}

private tailrec fun isPossible(direction: CardinalDirection, line: List<String>, currI: Int): Boolean {
    val nextSpaceI = if (direction == SOUTH || direction == EAST) currI+1 else currI-1
    if (line[nextSpaceI] == ".") return true
    if (line[nextSpaceI] == "#") return false
    return isPossible(direction, line, nextSpaceI)
}

private fun mustMoveBox(direction: CardinalDirection, line: List<String>, fromI: Int): Boolean{
    val nextSpaceI = if (direction == SOUTH || direction == EAST) fromI+1 else fromI-1
    return line[nextSpaceI] == "O"
}

private fun moveBoxes(direction: CardinalDirection, line: MutableList<String>, fromI: Int): MutableList<String> {
//    val nextSpaceI = if (direction == SOUTH || direction == EAST) fromI+1 else fromI-1
//    if (line[nextSpaceI] == ".") return line
    val isSouthOrEast = direction == SOUTH || direction == EAST
    val sublists = Pair(line.subList(0, fromI+1), line.subList(fromI+1, line.size))
    val relevantSublist = if (isSouthOrEast) sublists.second else sublists.first
    val split = relevantSublist.joinToString("").split(".").map{it.split("")}
    val numBoxesToMove = split[0].count{it == "O"}
    (1 .. numBoxesToMove).forEach{relevantSublist[it] = "O"}
//    val space = findNextSpace(line, fromI, direction)
    relevantSublist[0] = "."

    return sublists.toList().flatten().toMutableList()
}

private fun findNextSpace(line: List<String>, fromI: Int, direction: CardinalDirection): Int {
    return if (direction == SOUTH || direction == EAST) line.indexOfFirstIndexed{i, it -> i > fromI && it == "."} else line.indexOfFirstIndexed{i, it -> i < fromI && it == "."}
}

private fun parseInstructions(input: String) = input.toCharArray().map{
    when(it) {
        '<' -> WEST
        '>' -> EAST
        '^' -> NORTH
        'v' -> SOUTH
        else -> throw Exception("Unexpected instruction char $it")
    }
}
