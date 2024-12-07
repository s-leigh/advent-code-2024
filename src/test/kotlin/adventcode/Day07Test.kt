package adventcode

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Day07Test: StringSpec ({
    val input = this::class.java.classLoader.getResource("./day-07-input.txt")!!.readText()
    val sampleInput1 = """190: 10 19
3267: 81 40 27
83: 17 5
156: 15 6
7290: 6 8 6 15
161011: 16 10 13
192: 17 8 14
21037: 9 7 18 13
292: 11 6 16 20"""

    "Day 07 Part 1 sample input 1" {
        val expected = 3749
        day07Part1(sampleInput1) shouldBe expected
    }

    "Day 07 Part 1" {
        val expected = 7885693428401
        day07Part1(input) shouldBe expected
    }

//    "Day 07 Part 2 sample input 1" {
//        val expected = -1
//        day07Part2(sampleInput1) shouldBe expected
//    }
//
//    "Day 07 Part 2" {
//        val expected = -1
//        day07Part2(input) shouldBe expected
//    }
})
