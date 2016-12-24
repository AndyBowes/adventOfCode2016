package day24

import io.kotlintest.specs.FeatureSpec

class MazeTest : FeatureSpec() {
    init {
        feature("Find Distances") {
            scenario("given sample data should return 14") {
                val distances = mapOf(0 to mapOf(1 to 2, 2 to 8, 3 to 10, 4 to 2),
                        1 to mapOf(0 to 2, 2 to 6, 3 to 8, 4 to 4),
                        2 to mapOf(0 to 8, 1 to 6, 3 to 2, 4 to 10),
                        3 to mapOf(0 to 10, 1 to 8, 2 to 2, 4 to 8),
                        4 to mapOf(0 to 2, 1 to 4, 2 to 10, 3 to 8))

                findMinimumRoute((0..4).toSet(), distances) shouldEqual 14
            }
        }

        feature("Check layout of sample Maze") {
            val sampleMaze = listOf("###########",
                    "#0.1.....2#",
                    "#.#######.#",
                    "#4.......3#",
                    "###########")

            scenario("Find location of digits") {
                val digitLocations = sampleMaze.getDigitLocations()
                digitLocations.size shouldBe 5
                digitLocations[0] shouldEqual Pair(1, 1)
                digitLocations[1] shouldEqual Pair(3, 1)
                digitLocations[2] shouldEqual Pair(9, 1)
                digitLocations[3] shouldEqual Pair(9, 3)
                digitLocations[4] shouldEqual Pair(1, 3)
            }

            scenario("Find distances between points") {
                val distanceMatrix = sampleMaze.getDistanceMatrix()
                distanceMatrix shouldEqual mapOf(0 to mapOf(0 to 0, 1 to 2, 2 to 8, 3 to 10, 4 to 2),
                        1 to mapOf(0 to 2, 1 to 0, 2 to 6, 3 to 8, 4 to 4),
                        2 to mapOf(0 to 8, 1 to 6, 2 to 0, 3 to 2, 4 to 10),
                        3 to mapOf(0 to 10, 1 to 8, 2 to 2, 3 to 0, 4 to 8),
                        4 to mapOf(0 to 2, 1 to 4, 2 to 10, 3 to 8, 4 to 0))
            }
        }
    }
}