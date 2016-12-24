package day3

import io.kotlintest.specs.FeatureSpec
import java.util.stream.Collectors.toList

class TriangleTest : FeatureSpec (){
    init {
        feature("Read File"){
            scenario("Open triangles.txt"){
                readResource("day3/triangles.txt")
            }

            scenario("Count Triangles"){
                val triangles = countTriangles("day3/triangles.txt")
                println(triangles)
            }

            scenario("Get Triangles"){
                val triangles = getTriangles("day3/triangles.txt")
                triangles.forEach { println(it) }
            }

            scenario("Vertical Triangles"){
                val t: MutableList<List<Int>?>? = getUnsortedLines("day3/triangles.txt").collect(toList<List<Int>?>())
                val vTriangles = t?.toList()?.verticalTriangles()!!
                println(vTriangles.filter { it.isTriangle() }.count())
            }

        }
    }
}