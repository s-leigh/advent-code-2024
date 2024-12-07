package adventcode

private data class Equation(val result: Long, val operands: List<Long>)
private typealias Operation = (Long, Long) -> Long

fun day07Part1(input: String): Long {
    val data: List<Equation> = parseInput(input)
    val availableOperations: Set<Operation> = setOf(Long::plus, Long::times)
    return successfulEquationsSum(data, availableOperations)
}

fun day07Part2(input: String): Long {
    val data: List<Equation> = parseInput(input)
    val availableOperations: Set<Operation> = setOf(Long::plus, Long::times, Long::combine)
    return successfulEquationsSum(data, availableOperations)
}

private fun parseInput(input: String): List<Equation> = input.asLinesSplitBy(":").map {
    Equation(it[0].toLong(), it[1].split(" ").filterNotEmpty().map(String::toLong))
}

private tailrec fun successfulEquationsSum(
    equations: List<Equation>,
    availableOperations: Set<Operation>,
    successfulSum: Long = 0
): Long {
    if (equations.isEmpty()) return successfulSum
    val (head, tail) = equations.headAndTail()
    val ops = cartesianProduct(availableOperations, head.operands.size - 1)
    ops.forEach { o ->
        if (isSuccessful(head, o)) return successfulEquationsSum(tail, availableOperations, successfulSum + head.result)
    }
    return successfulEquationsSum(tail, availableOperations, successfulSum)
}

private fun isSuccessful(equation: Equation, operations: List<Operation>): Boolean {
    require(operations.size == equation.operands.size - 1) { "There must be the right number of operations for the equation." }
    val res = equation.operands.reduceIndexed { i, acc, o ->
        val res = operations[i - 1].invoke(acc, o)
        if (res > equation.result) return false // can't possibly hit target
        res
    }
    return res == equation.result
}

/* Combine two longs as strings, e.g. 123 + 456 = 123456 */
private fun Long.combine(other: Long): Long {
    var multiplier = 1L
    var temp = other
    while (temp > 0) {
        multiplier *= 10
        temp /= 10
    }
    return this * multiplier + other
}
