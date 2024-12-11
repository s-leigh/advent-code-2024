package adventcode

private data class Block(val id: Int?, val size: Int) {
    override fun toString() = (if (this.id == null) "." else this.id.toString()).repeat(this.size)
    fun repeat(times: Int): List<Block> = (0 until times).map { Block(this.id, 1) }
}

fun day09Part1(input: String): Long {
    val blocks = input.split("").filterNotEmpty().mapIndexed { i, n ->
        val isFreeSpace = i % 2 == 1
        val blockId = if (isFreeSpace) null else i / 2
        Block(blockId, n.toInt())
    }
    val blockElements: List<Block> = blocks.flatMap { Block(it.id, 1).repeat(it.size) }
    val newDisk = blockElements.toMutableList().rearrangeBlocks()
    return newDisk.checkSum()
}

fun day09Part2(input: String): Long {
    val blocks = input.split("").filterNotEmpty().mapIndexed { i, n ->
        val isFreeSpace = i % 2 == 1
        val blockId = if (isFreeSpace) null else i / 2
        Block(blockId, n.toInt())
    }
    val rearranged = rearrangeFiles(blocks.toMutableList())
    return rearranged.checkSum()
}

private fun rearrangeFiles(blocks: MutableList<Block>): List<Block> {
    for (fileI in blocks.lastIndex downTo 0) {
        val file = blocks[fileI]
        if (file.id == null) continue
        val freeSpaceI = blocks.subList(0, fileI).firstFreeSpace(file.size) ?: continue
        val leftoverSpace = blocks[freeSpaceI].size - file.size
        blocks[freeSpaceI] = file
        blocks[fileI] = Block(null, file.size)
        if (leftoverSpace > 0) blocks.add(freeSpaceI + 1, Block(null, leftoverSpace))

    }
    return blocks
}

private fun List<Block>.firstFreeSpace(minSize: Int): Int? =
    this.mapIndexed { i, b -> if (b.id == null && b.size >= minSize) i else null }.filterNotNull().firstOrNull()

private fun MutableList<Block>.rearrangeBlocks(): List<Block> {
    val fillers: MutableList<Block> = this.filter { it.id != null }.toMutableList()
    val numElements = fillers.size
    this.forEachIndexed { i, b ->
        if (i > numElements) return@forEachIndexed
        if (b.id == null) {
            this[i] = fillers.last()
            fillers.removeLast()
        }
    }
    return this.subList(0, numElements)
}

private fun List<Block>.checkSum(): Long = this.flatMap {
    Block(it.id ?: 0, 1).repeat(it.size)
}.mapIndexed { i, b -> i * b.id!!.toLong() }.sum()
