package day8

import java.util.*
import java.util.regex.Pattern

val rectPattern = Pattern.compile("rect (\\d*)x(\\d*)")
val rowRotatePattern = Pattern.compile("rotate row y=(\\d*) by (\\d*)")
val rowColumnPattern = Pattern.compile("rotate column x=(\\d*) by (\\d*)")

val SCREEN_WIDTH = 50

fun createScreen(x: Int= SCREEN_WIDTH, y: Int=6) = (0 until y ).map{ BitSet(x)}

fun processInstructions(instructions: List<String>): List<BitSet>{
    val screen = createScreen()
    instructions.forEach { it.processInstruction(screen) }
    return screen
}

fun  String.processInstruction(screen: List<BitSet>) {
    var matcher = rectPattern.matcher(this)
    if (matcher.matches()) { screen.setRect(matcher.group(1).toInt(), matcher.group(2).toInt())}
    else {
        matcher = rowRotatePattern.matcher(this)
        if (matcher.matches()) { screen.rotateRow(matcher.group(1).toInt(), matcher.group(2).toInt())}
        else {
            matcher = rowColumnPattern.matcher(this)
            if (matcher.matches()) { screen.rotateColumn(matcher.group(1).toInt(), matcher.group(2).toInt())}
            else {
                throw IllegalArgumentException("Invalid Instruction: $this")
            }
        }
    }
}

fun BitSet.shiftRight() {
    val lastVal = this[SCREEN_WIDTH-1]
    (SCREEN_WIDTH - 1 downTo 1).forEach { this[it] = this[it-1]}
    this[0] = lastVal
}

fun List<BitSet>.setRect(x: Int,y: Int){
    for (i in 0 until y){ this[i].set(0,x,true)}
}

fun List<BitSet>.rotateRow(y : Int,n : Int) = (0 until n).forEach { this[y].shiftRight() }

fun List<BitSet>.rotateColumn(x: Int,n: Int){
    (0 until n).forEach {
        val lastVal = this[this.size - 1][x]
        (this.size - 1 downTo 1).forEach { this[it][x] = this[it - 1][x] }
        this[0][x] = lastVal
    }
}

fun List<BitSet>.count() = this.sumBy { it.cardinality() }

fun List<BitSet>.asString(): String {
    val flags = mapOf<Boolean,Char>(true to 'X', false to ' ')
    return this.map { row -> (0 until SCREEN_WIDTH).map {flags[row[it]]}.joinToString(separator = "") }.joinToString(separator="\n\r")
}