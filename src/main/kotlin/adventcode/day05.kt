package adventcode

private typealias Rule = Pair<Int, Int>
private typealias Page = List<Int>

fun day05Part1(input: String): Int {
    val (rules, pages) = parsedInput(input)
    val inOrder = pages.filter { it.isInOrder(rules) }
    return inOrder.sumOf { it.middleValue() }
}

fun day05Part2(input: String): Int {
    val (rules, pages) = parsedInput(input)
    val outOfOrder = pages.filterNot { it.isInOrder(rules) }
    val ordered = outOfOrder.map { it.sortedWith { a, b -> if (rules.contains(Pair(a, b))) -1 else 1 } }
    return ordered.sumOf { it.middleValue() }
}

private fun parsedInput(input: String): Pair<List<Rule>, List<Page>> {
    val (rules, pages) = input.asLines().partition { e -> e.contains("|") }
    val parsedRules = rules.map { it.split("|").map(String::toInt) }.map { Pair(it[0], it[1]) }
    val parsedPages = pages.map { it.split(",").toInts() }
    return Pair(parsedRules, parsedPages)
}

private fun List<Int>.isInOrder(rules: List<Pair<Int, Int>>) = this.zipWithNext().all { rules.contains(it) }
