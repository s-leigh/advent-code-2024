package adventcode

fun day03Part1(input: String): Long {
    return numberPairs(input).sumOf { it.product() }
}

fun day03Part2(input: String): Long {
    val toRemove = Regex("""(don't\(\)).*?(?=do\(\)|$)""", RegexOption.DOT_MATCHES_ALL)
    val toProcess = input.replace(toRemove, "")
    return numberPairs(toProcess).sumOf { it.product() }
}

private fun numberPairs(text: String): Sequence<Pair<Long, Long>> {
    val numbersRe = Regex("""(?<=mul\(|,)\d{1,3},\d{1,3}(?=\))""")
    return numbersRe.findAll(text).map {
        it.value.split(',').map(String::toLong)
    }.map { Pair(it[0], it[1]) }
}
