package adventcode

private const val NEWLINE_CHAR = '\n'

internal fun String.asLines() = this.split(NEWLINE_CHAR).filterNot { it == "" }

internal infix fun String.asLinesSplitBy(s: String): List<List<String>> = (this.asLines() splitBy s)
internal infix fun String.asIntsSplitBy(s: String): List<List<Int>> = (this asLinesSplitBy s).toInts()

internal fun String.asMatrix() = this.asLines().map { it.split("").filterNot { it == "" } }