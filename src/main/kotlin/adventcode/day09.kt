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
    val newDisk = blockElements.toMutableList().rearrange()
    return newDisk.checkSum()
}

private fun MutableList<Block>.rearrange(): List<Block> {
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

private fun List<Block>.checkSum(): Long = this.mapIndexed { i, b -> i * b.id.toLong() }.sum()
