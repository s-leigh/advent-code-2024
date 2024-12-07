package adventcode

private data class Equation(val result: Long, val operands: List<Long>)
private typealias Operation = (Long, Long) -> Long

fun day07Part1(input: String): Long {
    val data: List<Equation> = input.asLinesSplitBy(":").map {
        Equation(it[0].toLong(), it[1].split(" ").filterNotEmpty().map(String::toLong))
    }
    val availableOperations: Set<Operation> = setOf(Long::plus, Long::times)
    val successfulEquations: List<Equation> = data.filter { e ->
        val ops = cartesianProduct(availableOperations, e.operands.size - 1)
        val success = ops.filter { o -> doEquation(e, o) == e.result }
        success.isNotEmpty()
    }
    return successfulEquations.sumOf { it.result }
}

fun day07Part2(input: String): Long {
    val data: List<Equation> = input.asLinesSplitBy(":").map {
        Equation(it[0].toLong(), it[1].split(" ").filterNotEmpty().map(String::toLong))
    }
    val availableOperations: Set<Operation> = setOf(Long::plus, Long::times, Long::combine)
    val successfulEquations: List<Equation> = data.filter { e ->
        val ops = cartesianProduct(availableOperations, e.operands.size - 1)
        val success = ops.filter { o -> doEquation(e, o) == e.result }
        success.isNotEmpty()
    }
    return successfulEquations.sumOf { it.result }
}

private fun doEquation(equation: Equation, operations: List<Operation>): Long {
    require(operations.size == equation.operands.size - 1) { "There must be the right number of operations for the equation." }
    return equation.operands.reduceIndexed { i, acc, o -> operations[i - 1].invoke(acc, o) }
}

private fun Long.combine(other: Long): Long = (this.toString() + other.toString()).toLong()

