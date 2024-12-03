package adventcode

import kotlin.math.abs

fun day01Part1(input: String): Int {
    val (firstVals, secondVals) = firstAndSecondValues(input)
    val differences = firstVals.sorted().zip(secondVals.sorted()) {a, b -> abs(a - b)}
    return differences.sum()
}

fun day01Part2(input: String): Int {
    val (firstVals, secondVals) = firstAndSecondValues(input)
    val secondValFrequency = secondVals.groupingBy{ it }.eachCount()
    val similarityScores: List<Int> =  firstVals.map{fv -> fv * (secondValFrequency[fv] ?: 0)}
    return similarityScores.sum()
}

private fun firstAndSecondValues(input: String): Pair<List<Int>, List<Int>> =
    input.asLines()
        .map{it.split(Regex("\\s\\s\\s")).map(String::toInt)}
        .fold(Pair<MutableList<Int>, MutableList<Int>>(mutableListOf<Int>(), mutableListOf<Int>())) { acc, curr ->
            acc.first.add(curr[0])
            acc.second.add(curr[1])
            acc
        }
