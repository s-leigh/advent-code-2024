package adventcode.day03

import java.math.BigInteger
import adventcode.product

fun day03Part1(input: String): BigInteger {
    return numberPairs(input).sumOf { it.product() }
}

fun day03Part2(input: String): BigInteger {
    val toRemove = Regex("""(don't\(\)).*?(?=do\(\)|$)""", RegexOption.DOT_MATCHES_ALL)
    val toProcess = input.replace(toRemove, "")
    return numberPairs(toProcess).sumOf { it.product() }
}

private fun numberPairs(text: String): Sequence<Pair<BigInteger, BigInteger>> {
    val numbersRe = Regex("""(?<=mul\(|,)\d{1,3},\d{1,3}(?=\))""")
    return numbersRe.findAll(text).map {
        it.value.split(',').map(String::toBigInteger)
    }.map { Pair(it[0], it[1]) }
}
