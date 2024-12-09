package adventcode

private data class Block(val id: Int, val size: Int) {
    override fun toString() = (if (this.id < 0) "." else this.id.toString()).repeat(this.size)
    fun repeat(times: Int): List<Block> = (0 until times).map { Block(this.id, 1) }
}

fun day09Part1(input: String): Long {
    val blocks = input.split("").filterNotEmpty().mapIndexed { i, n ->
        val isFreeSpace = i % 2 == 1
        val blockId = if (isFreeSpace) -1 else i / 2
        Block(blockId, n.toInt())
    }
    val blockElements: List<Block> = blocks.flatMap { Block(it.id, 1).repeat(it.size) }
    val newDisk = blockElements.rearrange()
    return newDisk.checkSum()
}

private tailrec fun List<Block>.rearrange(
    built: MutableList<Block> = mutableListOf<Block>(),
    fillers: MutableList<Block> = this.filter { it.id >= 0 }.toMutableList()
): List<Block> {
    if (this.isEmpty() || fillers.isEmpty()) return built
    val (head, tail) = this.headAndTail()
    if (head.id < 0) {
        built.add(fillers.last())
        return tail.rearrange(built, fillers.dropLast(1).toMutableList())
    } else {
        built.add(this[0])
        return tail.rearrange(built, fillers.drop(1).toMutableList())
    }
}

private fun List<Block>.checkSum(): Long = this.mapIndexed { i, b -> i * b.id.toLong() }.sum()
