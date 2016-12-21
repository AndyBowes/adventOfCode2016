package day21

import io.kotlintest.specs.FeatureSpec

class ScrambledSpec : FeatureSpec() {

    init {
        feature("Test Basic Operations") {
            scenario("Swap Characters") {
                "abcde".swapChars('a', 'c') shouldEqual "cbade"
                "abcde".swapChars('a', 'e') shouldEqual "ebcda"
            }
            scenario("Swap Positions") {
                "abcde".swapPositions(0, 2) shouldEqual "cbade"
                "abcde".swapPositions(2, 0) shouldEqual "cbade"
                "abcde".swapPositions(0, 4) shouldEqual "ebcda"
            }
            scenario("Reverse sections") {
                "abcde".reversePositions(1, 3) shouldEqual "adcbe"
                "abcde".reversePositions(0, 3) shouldEqual "dcbae"
                "abcde".reversePositions(1, 4) shouldEqual "aedcb"
            }
            scenario("Rotate left") {
                "abcde".rotateLeft(1) shouldEqual "bcdea"
                "abcde".rotateLeft(3) shouldEqual "deabc"
                "abcde".rotateLeft(4) shouldEqual "eabcd"
            }
            scenario("Rotate right") {
                "abcde".rotateRight(1) shouldEqual "eabcd"
                "abcde".rotateRight(3) shouldEqual "cdeab"
                "abcde".rotateRight(4) shouldEqual "bcdea"
            }
        }
        feature("Apply Instructions") {
            scenario("Sample Data") {
                val instructions = listOf("swap position 4 with position 0",
                        "swap letter d with letter b",
                        "reverse positions 0 through 4",
                        "rotate left 1",
                        "move position 1 to position 4",
                        "move position 3 to position 0",
                        "rotate based on position of letter b",
                        "rotate based on position of letter d")
                applyInstructions("abcde", instructions) shouldEqual "decab"
            }
        }
    }
}

