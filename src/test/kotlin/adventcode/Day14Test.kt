package adventcode

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Day14Test: StringSpec ({
    val input = this::class.java.classLoader.getResource("./day-14-input.txt")!!.readText()
    val sampleInput1 = """p=0,4 v=3,-3
p=6,3 v=-1,-3
p=10,3 v=-1,2
p=2,0 v=2,-1
p=0,0 v=1,3
p=3,0 v=-2,-2
p=7,6 v=-1,-3
p=3,0 v=-1,-2
p=9,3 v=2,3
p=7,3 v=-1,2
p=2,4 v=2,-3
p=9,5 v=-3,-3"""

    "Day 14 Part 1 sample input 1" {
        val expected = 12
        day14Part1(sampleInput1, Coordinates(11, 7)) shouldBe expected
    }

    "Day 14 Part 1" {
        val expected = 215987200
        day14Part1(input, Coordinates(101, 103)) shouldBe expected
    }

    "Day 14 Part 2" {
        val expected = 8050
        day14Part2(input, Coordinates(101, 103)) shouldBe expected
    }
})
