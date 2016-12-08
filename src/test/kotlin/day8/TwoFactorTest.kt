package day8

import day3.readResource
import io.kotlintest.specs.FeatureSpec
import java.util.stream.Collectors

class TwoFactorTest : FeatureSpec(){

    init {
        feature("Create Screen") {
            scenario("Empty Screen") {
                val screen = createScreen()
                println(screen.asString())
            }

            scenario("Set Rectangle") {
                val screen = createScreen()
                screen.setRect(10, 3)
                println(screen.asString())
            }

            scenario("Rotate Row") {
                val screen = createScreen()
                screen.setRect(10, 3)
                screen.rotateRow(2, 4)
                screen.setRect(2, 3)
                println(screen.asString())
                screen.rotateRow(2, 48)
                println(screen.asString())
                println(screen.count())
            }

            scenario("Rotate Column") {
                val screen = createScreen()
                screen.setRect(10, 3)
                screen.rotateColumn(2, 2)
                println(screen.asString())
            }
        }

        feature("Process Instructions") {
            scenario("Draw Rectangle") {
                val screen = createScreen()
                "rect 2x1".processInstruction(screen)
                "rotate row y=0 by 4".processInstruction(screen)
                println(screen.asString())
            }
        }

        feature("Submission") {
            scenario("Part 1") {
                val intructions = readResource("day8/input.txt").collect(Collectors.toList<String>())
                val screen = processInstructions(intructions)
                println(screen.asString())
                println(screen.count())
            }
        }
    }
}

