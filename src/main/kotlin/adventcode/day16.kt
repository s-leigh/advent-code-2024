package adventcode

import adventcode.CardinalDirection.*
import java.util.PriorityQueue
import kotlin.math.abs

private data class Node(
    val x: Int,
    val y: Int,
    var visited: Boolean = false,
    val isStart: Boolean = false,
    val isEnd: Boolean = false,
    var distance: Int = if (isStart) 0 else Int.MAX_VALUE
): Comparable<Node> {
    override fun compareTo(other: Node) = this.distance - other.distance
}

fun day16Part1(input: String): Int {
    val nodes = input.asLines().flatMapIndexed { y, l ->
        l.mapIndexed { x, c ->
            when (c) {
                '.' -> Node(x, y)
                'S' -> Node(x, y, isStart = true)
                'E' -> Node(x, y, isEnd = true)
                else -> null
            }
        }
    }.filterNotNull()
    val nodeMap = nodes.associateBy { "${it.x},${it.y}" }.toMutableMap()
    return dijkstraTime(nodeMap)
}

private fun dijkstraTime(nodeMap: MutableMap<String, Node>): Int {
    var prevNode = nodeMap.values.find{it.isStart}!!
    var currentDirection = EAST
    val queue: PriorityQueue<Node> = PriorityQueue()
    queue.add(prevNode)
    while (queue.isNotEmpty()) {
        val newNode = queue.poll()
        currentDirection = newDirection(prevNode, newNode)
        newNode.visited = true
        val neighbours = neighbours(newNode)
            .filter{nodeMap.contains(it.value) && !nodeMap[it.value]!!.visited} // TODO visited doesn't matter
        neighbours.forEach { (dir, key) ->
            val currNeighbourDist = nodeMap[key]!!.distance
            val newNeighbourDist = prevNode.distance + (if (newNode.visited) newNode.distance else 0) + (if (dir != currentDirection) 1000 else 1)
            if (newNeighbourDist < currNeighbourDist) {
                nodeMap[key]!!.distance = newNeighbourDist
                queue.add(nodeMap[key])
            }
        }
        prevNode = newNode
////    currentNode = neighbours.values.map{nodeMap[it]!!}.minBy { it.distance }
//        val nextNode = neighbours.filterNot{nodeMap[it.value]!!.visited}.minBy { nodeMap[it.value]!!.distance }
//        queue.add(nodeMap[nextNode.value])
//        currentNode = nodeMap[nextNode.value]!!
//        currentDirection = nextNode.key
    }
    return nodeMap.values.single { it.isEnd }.distance
//    throw Exception("Unreachable end :(")
//    return nodeMap.values.single{it.isEnd}.distance
}

private fun neighbours(node: Node): Map<CardinalDirection, String> {
    return with(node){mapOf(EAST to "${x+1},$y", WEST to "${x-1},$y", NORTH to "$x,${y-1}", SOUTH to "$x,${y+1}")}
}

private fun newDirection(from: Node, to: Node): CardinalDirection {
    val xDiff =  to.x - from.x
    val yDiff =  to.y - from.y
    return if (xDiff != 0) {
        if (xDiff > 0) EAST else WEST
    } else {
        if (yDiff > 0) SOUTH else NORTH
    }
}

private fun changedDirection(from: Node, to: Node, currDir: CardinalDirection): Boolean {
    val xDiff = abs(from.x - to.x)
    val yDiff = abs(from.y - to.y)
    return (currDir.isHorizontal && yDiff > 0) || (currDir.isVertical && xDiff > 0)
}
