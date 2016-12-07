package day6

import io.kotlintest.specs.FeatureSpec
import day3.readResource
import java.util.stream.Collectors


class SignalTest : FeatureSpec(){

    init {
        feature("Find Signal in List"){
            scenario("Test Case"){
                val testData = listOf("eedadn","drvtee","eandsr","raavrd","atevrs","tsrnev","sdttsa","rasrtv","nssdts","ntnada","svetve","tesnvt","vntsnd","vrdear","dvrsen","enarar")
                testData.findSignal() shouldEqual "easter"
            }
            scenario("Read submission data"){
                val entries = readResource("day6/signals.txt").collect(Collectors.toList<String>())
                println(entries.findSignal())
            }
        }
    }
}

