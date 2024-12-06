package adventcode

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Day06Test: StringSpec ({
    val input = this::class.java.classLoader.getResource("./day-06-input.txt")!!.readText()
    val sampleInput1 = """....#.....
.........#
..........
..#.......
.......#..
..........
.#..^.....
........#.
#.........
......#..."""

    "Day 06 Part 1 sample input 1" {
        val expected = 41
        day06Part1(sampleInput1) shouldBe expected
    }

    "Day 06 Part 1" {
        val expected = 4758
        day06Part1(input) shouldBe expected
    }

    "Day 06 Part 2 sample input 1" {
        val expected = 6
        day06Part2(sampleInput1) shouldBe expected
    }

    "Day 06 Part 2" {
        val expected = 1670
        day06Part2(input) shouldBe expected
    }
})
