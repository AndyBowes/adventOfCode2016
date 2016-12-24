package day23

import util.readResourceLines
import java.util.regex.Pattern

val instructionRegex = Pattern.compile("^([a-z]{3}) (-?\\d*|[a-d])(?: (-?\\d*|[a-d]))?$")

fun processInstuctions(insructions: MutableList<String>, initialA: Int): Map<Char, Int> {

    val registers = mutableMapOf<Char, Int>()
    ('a'..'d').forEach { registers.put(it,0) }
    registers.put('a', initialA)
    var step = 0
    while (step >= 0 && step < insructions.size) {
        val matcher = instructionRegex.matcher(insructions[step])
        if (!matcher.matches()) {
            throw IllegalArgumentException("Invalid Instruction at Step: $step: ${insructions[step]}")
        }
        when (matcher.group(1)) {
            "cpy" -> {
                if (matcher.group(3).first().isLetter()){
                    registers[matcher.group(3).first()] = getValue(registers, matcher.group(2))
                }
                step += 1
            }
            "inc" -> {
                if (matcher.group(2).first().isLetter()) {
                    registers[matcher.group(2).first()] = registers[matcher.group(2).first()]!! + 1
                }
                step += 1
            }
            "dec" -> {
                if (matcher.group(2).first().isLetter()) {
                    registers[matcher.group(2).first()] = registers[matcher.group(2).first()]!! - 1
                }
                step += 1
            }
            "jnz" -> {
                if ( getValue(registers, matcher.group(2)) != 0) step += getValue(registers, matcher.group(3)) else step += 1
            }
            "tgl" -> {
                val offset = getValue(registers, matcher.group(2))
                val targetPos = step + offset
                if (targetPos >= 0 && targetPos< insructions.size) {
                    insructions[targetPos] = insructions[targetPos].toggleInstruction()
                }
                step += 1
            }
        }
//        println(step)
    }
    return registers
}

fun String.toggleInstruction() : String {
    val prefix = this.substring(0,3)
    val newPrefix = when (prefix){
        "dec", "tgl" -> "inc"
        "inc" -> "dec"
        "jnz" -> "cpy"
        else -> "jnz"
    }
    return newPrefix + this.substring(3)
}

fun  getValue(registers: Map<Char,Int>, v: String) : Int {
    try {
        return v.toInt()
    } catch (e: NumberFormatException){
        return registers[v.first()]!!
    }
}

fun main(args: Array<String>) {
    val instructions = readResourceLines("day23/instructions.txt").toMutableList()
    val retVal = processInstuctions(instructions, 7)
    println(retVal)
}

