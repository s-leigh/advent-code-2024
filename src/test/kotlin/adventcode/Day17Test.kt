package adventcode

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Day17Test : StringSpec({
    val input = this::class.java.classLoader.getResource("./day-17-input.txt")!!.readText()
    val sampleInput1 = """Register A: 729
Register B: 0
Register C: 0

Program: 0,1,5,4,3,0"""

    "Day 17 Part 1 sample input 1" {
        val expected = listOf(4, 6, 3, 5, 6, 3, 5, 2, 1, 0)
        day17Part1(sampleInput1) shouldBe expected
    }

    "Day 17 Part 1" {
        val expected = listOf(7, 1, 3, 4, 1, 2, 6, 7, 1)
        day17Part1(input) shouldBe expected
    }

//    "Day 17 Part 2 sample input 1" {
//        val expected = -1
//        day17Part2(sampleInput1) shouldBe expected
//    }
//
//    "Day 17 Part 2" {
//        val expected = -1
//        day17Part2(input) shouldBe expected
//    }
})
