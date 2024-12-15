package adventcode

private data class Robot(val initialPosition: Coordinates, val velocity: Coordinates)

internal fun day14Part1(input: String, mapSize: Coordinates): Int {
    val seconds = 100
    val robots = parseRobots(input)
    val robotCoordinates = robots.map{positionAfterXSeconds(it, mapSize, seconds)}
    val robotsInQuadrants = robotCoordinates.filterNot{it.directlyInMiddle(mapSize)}
    return robotsInQuadrants.quadrantise(mapSize).map{it.size}.reduce{acc, curr -> acc * curr}
}

internal fun day14Part2(input: String, mapSize: Coordinates): Int {
    val robots = parseRobots(input)
    val limit = mapSize.x * mapSize.y // Robots will repeat positions after this
    val counter = 0
    var result = -1
    for (i in counter..limit) {
        val state = robots.map{positionAfterXSeconds(it, mapSize, i)}
        if (state.size == state.toSet().size) {
            result = i
            break // Robots will not overlap
        }
    }
    return result
}

private fun parseRobots(input: String): List<Robot> = input.asLines().map{l ->
    val nums = Regex("""-?\d+""").findAll(l).map{it.value.toInt()}.toList()
    require(nums.size == 4) {"Wrong num of nums found for line $l: $nums"}
    Robot(Coordinates(nums[0], nums[1]), Coordinates(nums[2], nums[3]))
}

private fun positionAfterXSeconds(robot: Robot, mapSize: Coordinates, seconds: Int): Coordinates {
    val x = (((robot.initialPosition.x + robot.velocity.x * seconds) % mapSize.x) + mapSize.x) % mapSize.x
    val y =  (((robot.initialPosition.y + robot.velocity.y * seconds) % mapSize.y) + mapSize.y) % mapSize.y
    return Coordinates(x, y)
}

private fun Coordinates.directlyInMiddle(mapSize: Coordinates) = this.x == mapSize.x / 2 || this.y == mapSize.y / 2

private fun List<Coordinates>.quadrantise(mapSize: Coordinates): Collection<List<Coordinates>> =
    this.groupBy{
        if (it.x < mapSize.x / 2) {
            if (it.y < mapSize.y / 2) 0 else 4
        } else {
            if (it.y < mapSize.y / 2) 2 else 3
        }
    }.values
