package adventcode

internal fun List<String>.columns() = this.indices.map { colI -> this.map { row -> row[colI] } }
internal infix fun List<String>.splitBy(s: String) = this.map { it.split(s).filterNot { it == "" } }
internal fun List<String>.filterNotEmpty() = this.filterNot { it == "" }

internal fun <T> List<T>.headAndTail() = Pair(this[0], this.drop(1))
internal fun <T> List<T>.middleValue() =
    if (this.size % 2 == 0) throw NotImplementedError("Can't find middle value of even-numbered array") else this[this.size / 2]

internal fun <T> List<Pair<T, T>>.removeTwins(): Set<Pair<T, T>> =
    this.fold(mutableSetOf()) { acc, curr ->
        if (curr.first != curr.second) acc.add(curr)
        acc
    }

internal fun List<String>.toInts() = this.map { it.toInt() }
@JvmName("toInts2D")
internal fun List<List<String>>.toInts() = this.map { it.toInts() }
