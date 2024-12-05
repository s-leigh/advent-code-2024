package adventcode

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Day05Test: StringSpec ({
    val input = this::class.java.classLoader.getResource("./day-05-input.txt")!!.readText()
    val sampleInput1 = """47|53
97|13
97|61
97|47
75|29
61|13
75|53
29|13
97|29
53|29
61|53
97|53
61|29
47|13
75|47
97|75
47|61
75|61
47|29
75|13
53|13

75,47,61,53,29
97,61,53,29,13
75,29,13
75,97,47,61,53
61,13,29
97,13,75,29,47"""

    "Day 05 Part 1 sample input 1" {
        val expected = 143
        day05Part1(sampleInput1) shouldBe expected
    }

    "Day 05 Part 1" {
        val expected = 5639
        day05Part1(input) shouldBe expected
    }

    "Day 05 Part 2 sample input 1" {
        val expected = 123
        day05Part2(sampleInput1) shouldBe expected
    }

    "Day 05 Part 2" {
        val expected = 5273
        day05Part2(input) shouldBe expected
    }
})
