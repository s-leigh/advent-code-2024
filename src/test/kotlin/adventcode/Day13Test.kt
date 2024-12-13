package adventcode

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Day13Test : StringSpec({
    val input = this::class.java.classLoader.getResource("./day-13-input.txt")!!.readText()
    val sampleInput1 = """Button A: X+94, Y+34
Button B: X+22, Y+67
Prize: X=8400, Y=5400

Button A: X+26, Y+66
Button B: X+67, Y+21
Prize: X=12748, Y=12176

Button A: X+17, Y+86
Button B: X+84, Y+37
Prize: X=7870, Y=6450

Button A: X+69, Y+23
Button B: X+27, Y+71
Prize: X=18641, Y=10279"""

    "Day 13 Part 1 sample input 1" {
        val expected = 480
        day13Part1(sampleInput1) shouldBe expected
    }

    "Day 13 Part 1" {
        val expected = 37680
        day13Part1(input) shouldBe expected
    }

    "Day 13 Part 2" {
        val expected = 87550094242995
        day13Part2(input) shouldBe expected
    }
})
