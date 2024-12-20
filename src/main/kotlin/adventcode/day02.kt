package adventcode

import kotlin.math.abs

fun day02Part1(input: String): Int {
    val reports = input asIntsSplitBy " "
    return reports.count(List<Int>::isSafe)
}

fun day02Part2(input: String): Int {
    val reports = input asIntsSplitBy " "
    return reports.count { it.isSafeWithTolerance() }
}

private fun List<Int>.isSafe(): Boolean {
    val differences = this.zipWithNext().map(Pair<Int, Int>::difference)
    return (differences.all(Int::isNegative) || differences.all(Int::isPositive))
            && differences.all { d -> abs(d) in 1..3 }
}

private fun List<Int>.isSafeWithTolerance() = if (this.isSafe()) true else this.indices
    .map { toRemove -> this.filterIndexed { i, _ -> i != toRemove } }
    .any { it.isSafe() }
