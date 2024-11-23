#!/bin/zsh
set -eu

dayArg=$1
if [[ dayArg -lt 10 ]]; then
  dayNumber="0${dayArg}";
else
  dayNumber=$dayArg;
fi

mainDir=src/main/kotlin/adventcode/day"${dayNumber}"
testDir=src/test/kotlin/adventcode/day"${dayNumber}"
mkdir $mainDir
mkdir $testDir

touch src/test/resources/day"${dayNumber}"input.txt

mainFile="$mainDir/day${dayNumber}.kt"
testFile="$testDir/Day${dayNumber}Test.kt"

echo "package adventcode.day${dayNumber}\n" > $mainFile
echo "package adventcode.day${dayNumber}

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Day${dayNumber}Test: StringSpec ({
    val input = this::class.java.classLoader.getResource(\"./day${dayNumber}input.txt\")!!.readText()
    val sampleInput = \"\"\"\"\"\"\"

    \"Day ${dayNumber} Part 1 sample input\" {

    }

    \"Day ${dayNumber} Part 1\" {

    }

    \"Day ${dayNumber} Part 2 sample input\" {

    }

    \"Day ${dayNumber} Part 2\" {

    }
})\n" > $testFile
