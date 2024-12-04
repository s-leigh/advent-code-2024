package adventcode

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Day04Test : StringSpec({
    val input = this::class.java.classLoader.getResource("./day-04-input.txt")!!.readText()
    val sampleInput1 = """MMMSXXMASM
MSAMXMSMSA
AMXSXMAAMM
MSAMASMSMX
XMASAMXAMM
XXAMMXXAMA
SMSMSASXSS
SAXAMASAAA
MAMMMXMMMM
MXMXAXMASX"""

    "Day 04 Part 1 sample input 1" {
        val expected = 18
        day04Part1(sampleInput1) shouldBe expected
    }

    "Day 04 Part 1" {
        val expected = 2578
        day04Part1(input) shouldBe expected
    }

    "Day 04 Part 2 sample input 1" {
        val expected = 9
        day04Part2(sampleInput1) shouldBe expected
    }

    "Day 04 Part 2" {
        val expected = 1972
        day04Part2(input) shouldBe expected
    }
})
// (?=(M.M.{9}A.{9}S.S))|(?=(M.S.{9}A.{9}M.S))|(?=(S.M.{9}A.{9}S.M))|(?=(S.S.{9}A.{9}M.M))