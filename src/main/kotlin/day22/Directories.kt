package day22

import util.readResourceLines
import java.util.regex.Pattern

val dirRegex = Pattern.compile("/dev/grid/node-x(\\d*)-y(\\d*) *(\\d*)T *(\\d*)T *(\\d*)T *(\\d*)%")

data class Directory(val x: Int, val y:Int, val size:Int,val used:Int, val avail:Int, val perc:Int)

fun viablePairs(directories: List<Directory>) : Int =
        directories.map { dir -> if (dir.used > 0) directories.filter { (it.x != dir.x || it.y != dir.y) && dir.used <= it.avail}.count()
            else 0 }.sum()


fun main(args: Array<String>) {
    val lines = readResourceLines("day22/directories.txt")
    val directories = lines.drop(2) // Ignore header lines
            .map { dirRegex.matcher(it) }
            .filter { it.matches() }
            .map { Directory(it.group(1).toInt(),it.group(2).toInt(),it.group(3).toInt(),it.group(4).toInt(),it.group(5).toInt(),it.group(6).toInt())}
    println(viablePairs(directories))
}


