package adventcode

fun day19Part1(input: String): Int {
    val inputLines = input.asLines()
    val towels = inputLines[0].split(",").map { it.trim() }
    val patterns = inputLines.subList(1, inputLines.size)
    return patterns.count { matchPattern(it, towels) }
}

fun day19Part2(input: String): Long {
    val inputLines = input.asLines()
    val towels = inputLines[0].split(",").map { it.trim() }
    val patterns = inputLines.subList(1, inputLines.size)
    val towelsRegex = towels.map { Regex("^$it") }
    return patterns.sumOf { combinations(it, towelsRegex) }
}

private fun matchPattern(pattern: String, towels: List<String>): Boolean {
    val re = ("(" + towels.joinToString("|").dropLast(1) + ")+").toRegex()
    return re.matches(pattern)
}

private fun combinations(pattern: String, towels: List<Regex>, memo: MutableMap<String, Long> = mutableMapOf()): Long {
    if (pattern.isEmpty()) return 1
    if (pattern in memo) return memo[pattern]!!

    var count = 0L
    towels.forEach { re ->
        re.find(pattern)?.let {
            val remaining = pattern.substring(it.value.length)
            count += combinations(remaining, towels, memo)
        }
    }
    memo[pattern] = count
    return count
}
