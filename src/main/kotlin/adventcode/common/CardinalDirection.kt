package adventcode

private interface Directional {
    val isHorizontal: Boolean
    val isVertical: Boolean
}

internal enum class CardinalDirection : Directional {
    NORTH {
        override val isHorizontal = false
        override val isVertical = true
    },
    SOUTH {
        override val isHorizontal = false
        override val isVertical = true
    },
    EAST {
        override val isHorizontal = true
        override val isVertical = false
    },
    WEST {
        override val isHorizontal = true
        override val isVertical = false
    },
}