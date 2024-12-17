package adventcode

fun day17Part1(input: String): List<Int> {
    val splitInput = with(input.split("\n\n")) { Pair(this[0], this[1]) }
    var (A, B, C) = with(Regex("""\d+""").findAll(splitInput.first).map { it.value.toInt() }.toList()) {
        Triple(
            this[0],
            this[1],
            this[2]
        )
    }
    val program = Regex("""\d+""").findAll(splitInput.second).map { it.value.toInt() }.toList()

    var pointer = 0
    val output = mutableListOf<Int>()

    while (pointer < program.lastIndex) {
        val instruction = program[pointer]
        val next = program[pointer + 1]
        val operand = when {
            next <= 3 -> next
            next == 4 -> A
            next == 5 -> B
            next == 6 -> C
            else -> throw Exception("Unsupported operand: $next")
        }
        var shouldIncrementPointer = true
        when (instruction) {
            0 -> A = (A.toDouble() / (1 shl operand)).toInt()
            1 -> B = B xor next
            2 -> B = operand % 8
            3 -> if (A != 0) {
                pointer = next
                shouldIncrementPointer = false
            }
            4 -> B = B xor C
            5 -> output.add(operand % 8)
            6 -> B = (A.toDouble() / (1 shl operand)).toInt()
            7 -> C = (A.toDouble() / (1 shl operand)).toInt()
        }
        if (shouldIncrementPointer) pointer += 2
    }

    return output
}
