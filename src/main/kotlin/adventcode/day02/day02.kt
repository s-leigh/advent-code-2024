package adventcode.day02

import adventcode.asIntsSplitBy
import adventcode.difference
import adventcode.isNegative
import adventcode.isPositive
import kotlin.math.abs

fun day02Part1(input: String): Int {
    val reports = input asIntsSplitBy " "
    return reports.count(List<Int>::isSafe)
}

fun day02Part2(input: String): Int {
    val reports = input asIntsSplitBy " "
    return reports.count { it.isSafeWithTolerance() }
}

fun List<Int>.isSafe(): Boolean {
    val differences = this.zipWithNext().map(Pair<Int, Int>::difference)
    return (differences.all(Int::isNegative) || differences.all(Int::isPositive))
            && differences.all { d -> abs(d) in 1..3 }
}

fun List<Int>.isSafeWithTolerance() = this.indices
    .map { toRemove -> this.filterIndexed { i, _ -> i != toRemove } }
    .any { it.isSafe() }
