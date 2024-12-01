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
