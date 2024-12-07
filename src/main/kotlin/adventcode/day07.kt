package adventcode

import kotlin.math.log

private data class Equation(val result: Long, val operands: List<Long>)
private data class Operation(val name: OperationName, val fn: (Long, Long) -> Long)
private enum class OperationName {
    MUL,
    ADD,
    CONCAT
}

private val operationAssociations: Set<Operation> = setOf(
    Operation(OperationName.MUL, Long::div),
    Operation(OperationName.ADD, Long::minus),
    Operation(OperationName.CONCAT, Long::removeDigits)
)

fun day07Part1(input: String): Long {
    val data: List<Equation> = parseInput(input)
    val permittedOperationAssociations: Set<Operation> =
        operationAssociations.filter { it.name == OperationName.MUL || it.name == OperationName.ADD }.toSet()
    return data.filter { isSuccessful(it, permittedOperationAssociations) }.sumOf { it.result }
}

fun day07Part2(input: String): Long {
    val data: List<Equation> = parseInput(input)
    return data.filter { isSuccessful(it, operationAssociations) }.sumOf { it.result }
}

private fun parseInput(input: String): List<Equation> = input.asLinesSplitBy(":").map {
    Equation(it[0].toLong(), it[1].split(" ").filterNotEmpty().map(String::toLong))
}

/* Go backwards through the operands, to determine which outcomes are impossible */
private fun isSuccessful(eq: Equation, ops: Set<Operation>): Boolean {
    if (eq.operands.size == 1) return eq.operands.single() == eq.result
    if (eq.result == 1L) return false
    return ops.any { op ->
        if (op.name == OperationName.MUL && eq.result % eq.operands.last() != 0L) false
        else if (op.name == OperationName.CONCAT && !eq.result.endsWith(eq.operands.last())) false
        else isSuccessful(
            Equation(
                op.fn.invoke(eq.result, eq.operands.last()),
                eq.operands.dropLast(1)
            ), ops
        )
    }
}

private fun Long.endsWith(other: Long): Boolean = this.toString().endsWith(other.toString())

/* Given a Long of N digits, remove the last N digits from this Long */
private fun Long.removeDigits(other: Long): Long = this.toString().dropLast(
    log(other.toFloat(), 10F).toInt() + 1
).toLong()
