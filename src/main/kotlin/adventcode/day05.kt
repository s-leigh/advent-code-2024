package adventcode

fun day05Part1(input: String): Int {
    val (rules, pages) = input.asLines().partition { e -> e.contains("|") }
    val parsedRules = rules.map { it.split("|").map(String::toInt) }.map { Pair(it[0], it[1]) }
    val mustComeBefore: MutableMap<Int, MutableList<Int>> = mutableMapOf()
    val mustComeAfter: MutableMap<Int, MutableList<Int>> = mutableMapOf()
    parsedRules.forEach { (b, a) ->
        if (mustComeBefore.containsKey(b)) mustComeBefore[b]!!.add(a) else mustComeBefore[b] = mutableListOf(a)
        if (mustComeAfter.containsKey(a)) mustComeAfter[a]!!.add(b) else mustComeAfter[a] = mutableListOf(b)
    }
    val parsedPages = pages.map { it.split(",").toInts() }
    val inOrder = parsedPages.filter { isInOrder(it, mustComeAfter) }
    return inOrder.sumOf { it[it.size / 2] }
}

private tailrec fun isInOrder(list: List<Int>, mustComeAfterRules: Map<Int, List<Int>>): Boolean {
    if (list.isEmpty()) return true
    val (head, tail) = list.headAndTail()
    if (!mustComeAfterRules.containsKey(head)) return isInOrder(tail, mustComeAfterRules)
    if (tail.any { mustComeAfterRules[head]!!.contains(it) }) return false
    return isInOrder(tail, mustComeAfterRules)
}
