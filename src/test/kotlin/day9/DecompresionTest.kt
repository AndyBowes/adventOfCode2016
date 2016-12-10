package day9

import io.kotlintest.specs.FeatureSpec

class DecompresionTest : FeatureSpec(){

    init{
        feature("Version 2 Decompression"){
            scenario("Simple Decompression"){
                decompress("(3x3)XYZ") shouldEqual 9
            }

            scenario("X(8x2)(3x3)ABCY"){
                decompress("X(8x2)(3x3)ABCY") shouldEqual "XABCABCABCABCABCABCY".length
            }

            scenario("(27x12)(20x12)(13x14)(7x10)(1x12)A"){
                decompress("(27x12)(20x12)(13x14)(7x10)(1x12)A") shouldEqual 241920
            }

            scenario("(25x3)(3x3)ABC(2x3)XY(5x2)PQRSTX(18x9)(3x2)TWO(5x7)SEVEN"){
                decompress("(25x3)(3x3)ABC(2x3)XY(5x2)PQRSTX(18x9)(3x2)TWO(5x7)SEVEN") shouldEqual 445
            }

        }
    }
}

