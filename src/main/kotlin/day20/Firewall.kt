package day20

import java.util.regex.Pattern
import java.util.stream.Collectors
import java.util.stream.Stream

val firewallRegex = Pattern.compile("(\\d*)-(\\d*)")

data class FirewallRule(val from: Long, val to: Long)

fun readResource(path: String): Stream<String> {
    val inputStream = String.javaClass.classLoader.getResourceAsStream(path)
    return inputStream.bufferedReader().lines()
}

fun findLowestFree(firewallRules: List<FirewallRule>): Long {
    val iter = firewallRules.sortedBy { it.from }.iterator()
    var rule = iter.next()
    var highest = rule.to

    while (iter.hasNext()) {
        rule = iter.next()
        if (rule.from > highest + 1) {
            break
        }
        if (rule.to > highest) highest = rule.to
    }
    return highest + 1
}

fun addRule(acc: Pair<Long, Long>, rule: FirewallRule): Pair<Long, Long> = when {
        rule.from > acc.second -> Pair(acc.first + (rule.from - acc.second) - 1, rule.to)
        rule.to > acc.second -> Pair(acc.first, rule.to)
        else -> acc
    }

fun countAvailablePorts(firewallRules: List<FirewallRule>): Long {
    val result: Pair<Long, Long> = firewallRules.sortedBy { it.from }.fold(Pair(0L, 0L), ::addRule)
    return (4294967295L - result.second) + result.first
}

fun main(args: Array<String>) {
    val firewallRules = readResource("day20/firewallRules.txt").collect(Collectors.toList<String>())
            .map { firewallRegex.matcher(it) }
            .filter { it!!.matches() }
            .map { FirewallRule(it.group(1).toLong(), it.group(2).toLong()) }
    println(findLowestFree(firewallRules))
    println(countAvailablePorts(firewallRules))
}

