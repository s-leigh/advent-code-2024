package adventcode

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Day10Test : StringSpec({
    val input = this::class.java.classLoader.getResource("./day-10-input.txt")!!.readText()
    val sampleInput1 = """89010123
78121874
87430965
96549874
45678903
32019012
01329801
10456732"""
    val sampleInput2 = """012345
123456
234567
345678
4.6789
56789."""

    "Day 10 Part 1 sample input 1" {
        val expected = 36
        day10Part1(sampleInput1) shouldBe expected
    }

    "Day 10 Part 1" {
        val expected = 557
        day10Part1(input) shouldBe expected
    }

    "Day 10 Part 2 sample input 1" {
        val expected = 81
        day10Part2(sampleInput1) shouldBe expected
    }

    "Day 10 Part 2 sample input 2" {
        val expected = 227
        day10Part2(sampleInput2) shouldBe expected
    }

    "Day 10 Part 2" {
        val expected = 1062
        day10Part2(input) shouldBe expected
    }
})
