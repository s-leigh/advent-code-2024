#!/bin/bash

dayArg=$1
if [[ dayArg -lt 10 ]]; then
  dayNumber="0${dayArg}";
else
  dayNumber=$dayArg;
fi

mainDir=src/main/kotlin/adventcode
testDir=src/test/kotlin/adventcode
#mkdir $mainDir
#mkdir $testDir

inputFilename=day-"${dayNumber}"-input.txt
touch src/test/resources/"$inputFilename"

mainFile="$mainDir/day${dayNumber}.kt"
testFile="$testDir/Day${dayNumber}Test.kt"

echo "package adventcode

fun day${dayNumber}Part1(input: String): Int {

}" > $mainFile
echo "package adventcode

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Day${dayNumber}Test: StringSpec ({
    val input = this::class.java.classLoader.getResource(\"./$inputFilename\")!!.readText()
    val sampleInput1 = \"\"\"\"\"\"

    \"Day ${dayNumber} Part 1 sample input 1\" {
        val expected = -1
        day${dayNumber}Part1(sampleInput1) shouldBe expected
    }

    \"Day ${dayNumber} Part 1\" {
        val expected = -1
        day${dayNumber}Part1(input) shouldBe expected
    }

//    \"Day ${dayNumber} Part 2 sample input 1\" {
//        val expected = -1
//        day${dayNumber}Part2(sampleInput1) shouldBe expected
//    }
//
//    \"Day ${dayNumber} Part 2\" {
//        val expected = -1
//        day${dayNumber}Part2(input) shouldBe expected
//    }
})" > $testFile
