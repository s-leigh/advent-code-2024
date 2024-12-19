package adventcode

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Day19Test : StringSpec({
    val input = this::class.java.classLoader.getResource("./day-19-input.txt")!!.readText()
    val sampleInput1 = """r, wr, b, g, bwu, rb, gb, br

brwrr
bggr
gbbr
rrbgbr
ubwu
bwurrg
brgr
bbrgwb
"""

    "Day 19 Part 1 sample input 1" {
        val expected = 6
        day19Part1(sampleInput1) shouldBe expected
    }

    "Day 19 Part 1" {
        val expected = 240
        day19Part1(input) shouldBe expected
    }

    "Day 19 Part 2 sample input 1" {
        val expected = 16L
        day19Part2(sampleInput1) shouldBe expected
    }

    "Day 19 Part 2" {
        val expected = 848076019766013L
        day19Part2(input) shouldBe expected
    }
})
