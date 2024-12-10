package adventcode

internal fun Int.isNegative() = this < 0
internal fun Int.isPositive() = this > 0

internal fun Pair<Int, Int>.difference() = this.second - this.first
internal fun Pair<Long, Long>.product() = this.first * this.second
internal fun <T> Pair<T, T>.either(predicate: (T) -> Boolean) = predicate(this.first) || predicate(this.second)
internal fun <T> Pair<T, T>.both(predicate: (T) -> Boolean) = predicate(this.first) && predicate(this.second)
