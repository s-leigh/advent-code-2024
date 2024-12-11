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
    val newDisk = blockElements.toMutableList().rearrangeBlocks()
    return newDisk.checkSum()
}

fun day09Part2(input: String): Long {
    val blocks = input.split("").filterNotEmpty().mapIndexed { i, n ->
        val isFreeSpace = i % 2 == 1
        val blockId = if (isFreeSpace) -1 else i / 2
        Block(blockId, n.toInt())
    }.filter { it.size > 0 }
//    println("start: $blocks")
    val newDisk = blocks.toMutableList().rearrangeFiles()
//    println("end result: $newDisk")
    return newDisk.checkSum()
}

private fun MutableList<Block>.rearrangeFiles(): List<Block> {
    val new = this.toList().toMutableList()
    for (i in new.lastIndex downTo 0) {
        val b = new[i]
        if (b.id < 0) continue
        val nextEmptyI = new.nextEmpty(b.size)
        if (nextEmptyI != null && nextEmptyI < i) {
            new[i] = Block(-1, b.size)
            val space = new[nextEmptyI]
            val leftover = space.size - b.size
            new.removeAt(nextEmptyI)
            new.add(nextEmptyI, b)
            if (leftover > 0) {
                new.add(nextEmptyI+1, Block(-1, leftover))
            }
        }
    }

    return new
}

private fun List<Block>.nextEmpty(minSize: Int) = this.mapIndexed{i, b -> if (b.id == -1 && b.size >= minSize) i else null}
    .filterNotNull()
    .firstOrNull()

private fun MutableList<Block>.rearrangeBlocks(): List<Block> {
    val fillers: MutableList<Block> = this.filter { it.id >= 0 }.toMutableList()
    val numElements = fillers.size
    this.forEachIndexed { i, b ->
        if (i > numElements) return@forEachIndexed
        if (b.id == -1) {
            this[i] = fillers.last()
            fillers.removeLast()
        }
    }
    return this.subList(0, numElements)
}

private fun List<Block>.checkSum(): Long = this.flatMap {
    Block(if (it.id == -1) 0 else it.id, 1).repeat(it.size)
}.mapIndexed { i, b -> i * b.id.toLong() }.sum()
