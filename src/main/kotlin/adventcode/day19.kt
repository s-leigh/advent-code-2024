package adventcode

fun day19Part1(input: String): Int {
    val inputLines = input.asLines()
    val towels = inputLines[0].split(",").map { it.trim() }
    val patterns = inputLines.subList(1, inputLines.size)
    return patterns.count { matchOffPattern(it, towels) }
}

private fun matchOffPattern(pattern: String, towels: List<String>): Boolean {
    val re = ("(" + towels.joinToString("|").dropLast(1) + ")+").toRegex()
    return re.matches(pattern)
}
