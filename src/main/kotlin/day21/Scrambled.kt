package day21

import java.util.regex.Matcher
import java.util.regex.Pattern

val reversePos: Pattern = Pattern.compile("reverse positions (\\d) through (\\d)")
val rotateLetter: Pattern = Pattern.compile("rotate based on position of letter (.)")
val swapPositions: Pattern = Pattern.compile("swap position (\\d) with position (\\d)")
val movePositions: Pattern = Pattern.compile("move position (\\d) to position (\\d)")
val swapLetters: Pattern = Pattern.compile("swap letter (.) with letter (.)")
val rotateRight: Pattern = Pattern.compile("rotate right (\\d) steps")
val rotateLeft: Pattern = Pattern.compile("rotate left (\\d) steps")

val instructionSet: Map<Pattern, (String, Matcher) -> String> =
        mapOf(
                reversePos to { a: String, b: Matcher -> a.reversePositions(b.group(1).toInt(), b.group(2).toInt()) },
                rotateLetter to { a: String, b: Matcher -> a.rotateLetter(b.group(1)[0]) },
                swapPositions to { a: String, b: Matcher -> a.swapPositions(b.group(1).toInt(), b.group(2).toInt()) },
                swapLetters to { a: String, b: Matcher -> a.swapChars(b.group(1)[0], b.group(2)[0]) },
                rotateRight to { a: String, b: Matcher -> a.rotateRight(b.group(1).toInt()) },
                rotateLeft to { a: String, b: Matcher -> a.rotateLeft(b.group(1).toInt()) },
                movePositions to { a: String, b: Matcher -> a.movePositions(b.group(1).toInt(), b.group(2).toInt()) }
        )

fun applyOperation(acc: String, instruction: String): String {
    return instructionSet.entries.map { Pair(it.key.matcher(instruction)!!, it.value) }
            .filter { it.first.matches() }.take(1)
            .map { it.second(acc, it.first) }.first()
}

fun String.reversePositions(from: Int, to: Int) = this.substring(0, from) + this.subSequence(from, to + 1).reversed() + this.substring(to + 1)
fun String.swapChars(first: Char, second: Char) = this.map {
    when (it) {
        first -> second
        second -> first
        else -> it
    }
}.joinToString("")

fun String.swapPositions(pos1: Int, pos2: Int) = this.swapChars(this[pos1], this[pos2])
fun String.rotateLeft(move: Int) = this.substring(move) + this.substring(0, move)
fun String.rotateRight(move: Int) = this.rotateLeft(this.length - move)
fun String.rotateLetter(c: Char): String {
    var pos = this.indexOf(c) + 1
    if (pos > 4) pos += 1
    return this.rotateRight(pos)
}

fun String.movePositions(pos1: Int, pos2: Int): String {
    return this.substring(0, pos1) + this.substring(pos1 + 1)
}

fun applyInstructions(initial: String, instructions: List<String>): String = instructions.fold(initial, ::applyOperation)

fun main(args: Array<String>) {
    applyInstructions("abcde", listOf())
}