package adventcode

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Day16Test: StringSpec ({
    val input = this::class.java.classLoader.getResource("./day-16-input.txt")!!.readText()
    val sampleInput1 = """###############
#.......#....E#
#.#.###.#.###.#
#.....#.#...#.#
#.###.#####.#.#
#.#.#.......#.#
#.#.#####.###.#
#...........#.#
###.#.#####.#.#
#...#.....#.#.#
#.#.#.###.#.#.#
#.....#...#.#.#
#.###.#.#.#.#.#
#S..#.....#...#
###############
"""
    val sampleInput2 = """#################
#...#...#...#..E#
#.#.#.#.#.#.#.#.#
#.#.#.#...#...#.#
#.#.#.#.###.#.#.#
#...#.#.#.....#.#
#.#.#.#.#.#####.#
#.#...#.#.#.....#
#.#.#####.#.###.#
#.#.#.......#...#
#.#.###.#####.###
#.#.#...#.....#.#
#.#.#.#####.###.#
#.#.#.........#.#
#.#.#.#########.#
#S#.............#
#################
"""
    val sampleInput3 = """###############
#.......#...#E#
#.#.###.#.###.#
#.....#.#...#.#
#.###.#####.#.#
#.#.#.......#.#
#.###########.#
#.#.........#.#
###.#######.#.#
#...#.....#.#.#
#.###.###.#.#.#
#.#...#...#.#.#
#.###.#.#.#.#.#
#S#.#.....#...#
###############
"""
    "Day 16 Part 1 sample input 1" {
        val expected = 7036
        day16Part1(sampleInput1) shouldBe expected
    }

    "Day 16 Part 1 sample input 2" {
        val expected = 11048
        day16Part1(sampleInput2) shouldBe expected
    }

    "Day 16 Part 1 sample input 3" {
        val expected = 7036
        day16Part1(sampleInput3) shouldBe expected
    }

    "Day 16 Part 1" {
        val expected = -1
        day16Part1(input) shouldBe expected
    }

//    "Day 16 Part 2 sample input 1" {
//        val expected = -1
//        day16Part2(sampleInput1) shouldBe expected
//    }
//
//    "Day 16 Part 2" {
//        val expected = -1
//        day16Part2(input) shouldBe expected
//    }
})
