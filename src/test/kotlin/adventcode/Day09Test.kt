package adventcode

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Day09Test : StringSpec({
    val input = this::class.java.classLoader.getResource("./day-09-input.txt")!!.readText()
    val sampleInput1 = """2333133121414131402"""
    val sampleInput2 = "12345"
    val sampleInput3 = "1010101010101010101010"
    val sampleInput4 = "354631466260"

    "Day 09 Part 1 sample input 1" {
        val expected = 1928L
        day09Part1(sampleInput1) shouldBe expected
    }

    "Day 09 Part 1 sample input 2" {
        val expected = 60L
        day09Part1(sampleInput2) shouldBe expected
    }

    "Day 09 Part 1 sample input 3" {
        val expected = 385L
        day09Part1(sampleInput3) shouldBe expected
    }

    "Day 09 Part 1" {
        val expected = 6201130364722L
        day09Part1(input) shouldBe expected
    }

    "Day 09 Part 2 sample input 1" {
        val expected = 2858L
        day09Part2(sampleInput1) shouldBe expected
    }

    "Day 09 Part 2 sample input 2" {
        val expected = 132L
        day09Part2(sampleInput2) shouldBe expected
    }

    "Day 09 Part 2 sample input 3" {
        val expected = 385L
        day09Part2(sampleInput3) shouldBe expected
    }

    "Day 09 Part 2 sample input 4" {
        val expected = 1325L
        day09Part2(sampleInput4) shouldBe expected
    }

    "Day 09 Part 2 sample input x" {
        val expected = 12L
        day09Part2("111000000000000000001") shouldBe expected
    }

    "Day 09 Part 2" {
        val expected = -1
        day09Part2(input) shouldBe expected
    }
})
