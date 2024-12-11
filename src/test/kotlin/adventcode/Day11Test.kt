package adventcode

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Day11Test: StringSpec ({
    val input = this::class.java.classLoader.getResource("./day-11-input.txt")!!.readText()
    val sampleInput1 = """0 1 10 99 999"""
    val sampleInput2 = "125 17"

    "Day 11 Part 1 sample input 1" {
        val expected = 7
        day11Part1(sampleInput1, 1) shouldBe expected
    }

    "Day 11 Part 1 sample input 2" {
        val expected = 22
        day11Part1(sampleInput2, 6) shouldBe expected
    }

    "Day 11 Part 1 sample input 3" {
        val expected = 55312
        day11Part1(sampleInput2, 25) shouldBe expected
    }

    "Day 11 Part 1" {
        val expected = 216042
        day11Part1(input, 25) shouldBe expected
    }

    "Day 11 Part 2" {
        val expected = 255758646442399
        day11Part1(input, 75) shouldBe expected
    }
})
