package day10

import day3.readResource
import java.util.regex.Pattern
import java.util.stream.Collectors

class Bot(val name: String, var toHigh: String? = null, var toLow: String? = null) {
    public val inputs = mutableListOf<Int>()
    fun setValue(v: Int) {
        inputs.add(v)
        if (inputs.size == 2) {
            bots.get(toHigh)?.setValue(inputs.max()!!)
            bots.get(toLow)?.setValue(inputs.min()!!)
        }
    }

    override fun toString(): String {
        return "$name : $inputs"
    }
}

var bots = mutableMapOf<String, Bot>()

fun createBots(lines: List<String>) {
    val botPattern = Pattern.compile("^(bot \\d*) gives low to ([a-z]* \\d*) and high to ([a-z]* \\d*)$")
    lines.filter { it.startsWith("bot ") }.forEach {
        val matcher = botPattern.matcher(it)
        if (matcher.matches()) {
            val botName = matcher.group(1)
            bots.put(botName, Bot(botName, matcher.group(3), matcher.group(2)))
        }
    }
}

fun applyValues(lines: List<String>) {
    val valuePattern = Pattern.compile("^value (\\d*) goes to (bot \\d*)$")
    lines.filter { it.startsWith("value ") }.forEach {
        val matcher = valuePattern.matcher(it)
        if (matcher.matches()) {
            bots[matcher.group(2)]?.setValue(matcher.group(1).toInt())
        }
    }
}

fun main(args: Array<String>) {
    val lines = readResource("day10/bots.txt").collect(Collectors.toList<String>())
    createBots(lines)
    (0..2).forEach { bots.put("output $it", Bot("output $it")) }
    applyValues(lines)
    (0..2).forEach { println(bots["output $it"]) }
}