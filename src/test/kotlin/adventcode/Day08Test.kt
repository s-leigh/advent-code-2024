package adventcode

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Day08Test : StringSpec({
    val input = this::class.java.classLoader.getResource("./day-08-input.txt")!!.readText()
    val sampleInput1 = """............
........0...
.....0......
.......0....
....0.......
......A.....
............
............
........A...
.........A..
............
............"""
    val sampleInput2 = """T.........
...T......
.T........
..........
..........
..........
..........
..........
..........
.........."""

    "Day 08 Part 1 sample input 1" {
        val expected = 14
        day08Part1(sampleInput1) shouldBe expected
    }

    "Day 08 Part 1" {
        val expected = 318
        day08Part1(input) shouldBe expected
    }

    "Day 08 Part 2 sample input 1" {
        val expected = 34
        day08Part2(sampleInput1) shouldBe expected
    }

    "Day 08 Part 2 sample input 2" {
        val expected = 9
        day08Part2(sampleInput2) shouldBe expected
    }

    "Day 08 Part 2" {
        val expected = 1126
        day08Part2(input) shouldBe expected
    }
})
