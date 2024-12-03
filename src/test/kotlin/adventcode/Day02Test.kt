package adventcode

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Day02Test: StringSpec ({
    val input = this::class.java.classLoader.getResource("day-02-input.txt")!!.readText()
    val sampleInput1 = """7 6 4 2 1
1 2 7 8 9
9 7 6 2 1
1 3 2 4 5
8 6 4 4 1
1 3 6 7 9"""

    "Day 02 Part 1 sample input 1" {
        val expected = 2
        day02Part1(sampleInput1) shouldBe expected
    }

    "Day 02 Part 1" {
        val expected = 526
        day02Part1(input) shouldBe expected
    }

    "Day 02 Part 2 sample input 1" {
        val expected = 4
        day02Part2(sampleInput1) shouldBe expected
    }

    "Day 02 Part 2" {
        val expected = 566
        day02Part2(input) shouldBe expected
    }
})
