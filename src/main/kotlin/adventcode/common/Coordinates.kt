package adventcode

internal data class Coordinates(val x: Int, val y: Int) {
    constructor(xy: Pair<Int, Int>) : this(xy.first, xy.second)

    fun inBounds(maxCoords: Coordinates) =
        this.x <= maxCoords.x && this.y <= maxCoords.y && this.x >= 0 && this.y >= 0
}
