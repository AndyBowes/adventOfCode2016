package day12

import java.util.regex.Pattern

val instructionRegex = Pattern.compile("^([a-z]{3}) (\\d*|[a-d])(?: (-?\\d*|[a-d]))?$")

fun processInstuctions(insructions: List<String>): Map<Char, Int> {

    val registers = mutableMapOf<Char, Int>()
    ('a'..'d').forEach { registers.put(it,0) }
    var step = 0
    while (step >= 0 && step < insructions.size) {
        val matcher = instructionRegex.matcher(insructions[step])
        if (!matcher.matches()) {
            throw IllegalArgumentException("Invalid Instruction at Step: $step")
        }
        when (matcher.group(1)) {
            "cpy" -> {
                registers[matcher.group(3).first()] = getValue(registers, matcher.group(2)); step += 1
            }
            "inc" -> {
                registers[matcher.group(2).first()] = registers[matcher.group(2).first()]!! + 1; step += 1
            }
            "dec" -> {
                registers[matcher.group(2).first()] = registers[matcher.group(2).first()]!! - 1; step += 1
            }
            "jnz" -> {
                if (registers[matcher.group(2).first()] != 0) step += matcher.group(3).toInt() else step += 1
            }
        }
    }
    return registers
}

fun  getValue(registers: Map<Char,Int>, v: String) : Int {
    try {
        return v.toInt()
    } catch (e: NumberFormatException){
        return registers[v.first()]!!
    }
}

fun main(args: Array<String>) {
    val retVal = processInstuctions(listOf("cpy 1 c","cpy 1 a","cpy 1 b","cpy 26 d","jnz c 2","jnz 1 5","cpy 7 c","inc d","dec c","jnz c -2","cpy a c","inc a","dec b","jnz b -2","cpy c b","dec d","jnz d -6","cpy 13 c","cpy 14 d","inc a","dec d","jnz d -2","dec c","jnz c -5"))
    println(retVal)
}

