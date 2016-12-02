package day1

import io.kotlintest.specs.ShouldSpec

class Test1 : ShouldSpec(){
    val TEST_STEPS = "L2, L3, L3, L4, R1, R2, L3, R3, R3, L1, L3, R2, R3, L3, R4, R3, R3, L1, L4, R4, L2, R5, R1, L5, R1, R3, L5, R2, L2, R2, R1, L1, L3, L3, R4, R5, R4, L1, L189, L2, R2, L5, R5, R45, L3, R4, R77, L1, R1, R194, R2, L5, L3, L2, L1, R5, L3, L3, L5, L5, L5, R2, L1, L2, L3, R2, R5, R4, L2, R3, R5, L2, L2, R3, L3, L2, L1, L3, R5, R4, R3, R2, L1, R2, L5, R4, L5, L4, R4, L2, R5, L3, L2, R4, L1, L2, R2, R3, L2, L5, R1, R1, R3, R4, R1, R2, R4, R5, L3, L5, L3, L3, R5, R4, R1, L3, R1, L3, R3, R3, R3, L1, R3, R4, L5, L3, L1, L5, L4, R4, R1, L4, R3, R3, R5, R4, R3, R3, L1, L2, R1, L4, L4, L3, L4, L3, L5, R2, R4, L2"

    init {
        should("extract steps from strings") {
            val steps = "L2, L2, R4, R1, L12".toSteps()
            steps.size shouldEqual 5
            steps.first().turn shouldEqual "L"
            steps.first().distance shouldEqual 2
            steps.last().turn shouldEqual "L"
            steps.last().distance shouldEqual 12
        }

        should("Test Case 1"){
            val steps = listOf(Step("R2"), Step("L3"))
            val finalPos = steps.walk()
            finalPos.second shouldEqual 2
            finalPos.third shouldEqual 3
            finalPos.distance() shouldEqual 5
        }

        should("Test Case 2"){
            val steps = "R5, L5, R5, R3".toSteps()
            val finalPos = steps.walk()
            finalPos.distance() shouldEqual 12
        }

        should("walk all of the steps"){
            println(TEST_STEPS.toSteps().walk().distance())
        }

        should("find first crossing point"){
            println(TEST_STEPS.toSteps().findDuplicatePos())
        }
    }
}
