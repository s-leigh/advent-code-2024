package adventcode

fun String.asLines() = this.split('\n').filterNot { it == "" }
infix fun List<String>.splitBy(s: String) = this.map { it.split(s) }
infix fun String.asLinesSplitBy(s: String): List<List<String>> = this.asLines() splitBy s
infix fun String.asIntsSplitBy(s: String): List<List<Int>> = (this asLinesSplitBy  s).toInts()

fun List<String>.toInts() = this.map { it.toInt() }
@JvmName("toInts2D") fun List<List<String>>.toInts() = this.map {it.toInts()}

fun Int.isNegative() = this < 0
fun Int.isPositive() = this > 0

fun Pair<Int, Int>.difference() = this.second - this.first
