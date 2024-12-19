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
        val newDirection = newDirection(prevNode, newNode)
//        val haveChangedDirection = currentDirection != newDirection
        currentDirection = newDirection
        val currentDistance = newNode.distance
        newNode.visited = true
        val neighbours = neighbours(newNode)
            .filter{nodeMap.contains(it.value) }
        neighbours.forEach { (dir, key) ->
            val currNeighbourDist = nodeMap[key]!!.distance
            val wouldChangeDirection = dir != currentDirection
            val newNeighbourDist = currentDistance + 1 + (if (wouldChangeDirection) 1000 else 0)
            if (newNeighbourDist < currNeighbourDist) {
                nodeMap[key]!!.distance = newNeighbourDist
                queue.add(nodeMap[key])
            }
        }
        prevNode = newNode
    }
    return nodeMap.values.single { it.isEnd }.distance// + 1000
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
