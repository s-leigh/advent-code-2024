package adventcode

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Day01Test: StringSpec ({
    val input = this::class.java.classLoader.getResource("day-01-input.txt")!!.readText()
    val sampleInput1 = """3   4
4   3
2   5
1   3
3   9
3   3"""

    "Day 01 Part 1 sample input 1" {
        val expected = 11
        day01Part1(sampleInput1) shouldBe expected
    }

    "Day 01 Part 1" {
        val expected = 1222801
        day01Part1(input) shouldBe expected
    }

    "Day 01 Part 2 sample input 1" {
        val expected = 31
        day01Part2(sampleInput1) shouldBe expected
    }

    "Day 01 Part 2" {
        val expected = 22545250
        day01Part2(input) shouldBe expected
    }
})
