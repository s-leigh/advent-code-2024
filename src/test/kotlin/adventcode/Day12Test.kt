package adventcode

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Day12Test: StringSpec ({
    val input = this::class.java.classLoader.getResource("./day-12-input.txt")!!.readText()
    val sampleInput1 = """AAAA
BBCD
BBCC
EEEC"""
    val sampleInput2 = """OOOOO
OXOXO
OOOOO
OXOXO
OOOOO"""

    "Day 12 Part 1 sample input 1" {
        val expected = 140
        day12Part1(sampleInput1) shouldBe expected
    }

    "Day 12 Part 1 sample input 2" {
        val expected = 772
        day12Part1(sampleInput2) shouldBe expected
    }

    "Day 12 Part 1" {
        val expected = -1
        day12Part1(input) shouldBe expected
    }

//    "Day 12 Part 2 sample input 1" {
//        val expected = -1
//        day12Part2(sampleInput1) shouldBe expected
//    }
//
//    "Day 12 Part 2" {
//        val expected = -1
//        day12Part2(input) shouldBe expected
//    }
})
