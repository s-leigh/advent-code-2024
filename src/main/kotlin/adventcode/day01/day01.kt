package adventcode.day01

import kotlin.math.abs
import adventcode.asLines



fun day01Part1(input: String): Int {
    val lines = input.asLines()
    val values = lines.map{it.split("\\s\\s\\s".toRegex()).map(String::toInt)}
    val (firstVals, secondVals) = listOf(values.map{it[0]}, values.map{it[1]})
    val differences = firstVals.sorted().zip(secondVals.sorted()) {a, b -> abs(a - b)}
    return differences.sum()
}

fun day01Part2(input: String): Int {
    val lines = input.asLines()
    val values = lines.map{it.split("\\s\\s\\s".toRegex()).map(String::toInt)}
    val (firstVals, secondVals) = listOf(values.map{it[0]}, values.map{it[1]})
    val secondValFrequency: MutableMap<Int, Int> = secondVals.fold(mutableMapOf<Int, Int>()) { acc, curr ->
            if (acc.containsKey(curr)) acc[curr] = acc[curr]!! + 1 else acc[curr] = 1
            return@fold acc
    }
    val similarityScores: List<Int> =  firstVals.map{fv -> fv * (secondValFrequency[fv] ?: 0)}
    return similarityScores.sum()
}