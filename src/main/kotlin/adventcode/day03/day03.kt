package adventcode.day03

import java.math.BigInteger
import adventcode.product

const val numberPattern = """\d+"""
const val mulPattern = """mul\($numberPattern,$numberPattern\)"""

fun day03Part1(input: String): Int {
    val matches = mulPattern.toRegex().findAll(input).map { it.value }
    val operands = matches.map { numberPattern.toRegex().findAll(it).map { it.value.toInt() } }
    return operands.sumOf { it.product() }
}

fun day03Part2(input: String): BigInteger {
    val dontPattern = "don't\\(\\)"
    val doPattern = "do\\(\\)"
    val combinedRe = "$mulPattern|$dontPattern|$doPattern".toRegex()
    val foundMatches = combinedRe.findAll(input).toList()

    var inDoBlock = true
    val processInstructions = foundMatches.map { it.value }.mapNotNull {
        val isDont = it.matches(dontPattern.toRegex())
        val isDo = it.matches(doPattern.toRegex())
        inDoBlock = if (isDont) false else if (isDo) true else inDoBlock
        if (inDoBlock && !isDo) it else null
    }

    return processInstructions.sumOf {
        numberPattern.toRegex().findAll(it).map { it.value.toBigInteger() }.product()
    }
}
