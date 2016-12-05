package day5

import io.kotlintest.specs.FeatureSpec

class ChessTest : FeatureSpec(){
    init{
        feature("Password Generator"){
            scenario("Example Password"){
                findPassword("abc", 8) shouldEqual "18f47a30"
            }
        }
        scenario("Solution Password"){
            println(findPassword("uqwqemis", 8))
        }

        scenario("Advanced Password"){
            println(findAdvancedPassword("uqwqemis", 8))
        }
    }
}