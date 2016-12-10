package day9

import java.io.InputStreamReader
import java.util.regex.Pattern
import kotlin.jvm.internal.iterator

val tokenRegex = Pattern.compile("\\((\\d*)x(\\d*)\\)")

fun String.decompress(): String {
    val tokens = tokenise()
    val sb = StringBuffer()
    while (tokens.hasNext()) {
        val token = tokens.next()
        val matcher = tokenRegex.matcher(token)
        if (matcher.matches()) {
            val length = matcher.group(1).toInt()
            val times = matcher.group(2).toInt()
            val subToken = StringBuffer();
            while (subToken.length < length){
                subToken.append(tokens.next())
            }
            sb.append((0 until times).map{subToken.substring(0,length)}.joinToString(separator = ""))
            sb.append(subToken.substring(length))
        } else {
            sb.append(token)
        }
    }
    return sb.toString()
}

private fun String.tokenise(): ListIterator<String> {
    val tokens = this.split(Regex("(?<=(\\(\\d{1,4}x\\d{1,4}\\)))|(?=(\\(\\d{1,4}x\\d{1,4}\\)))")).listIterator()
    return tokens
}


val termRegex = Pattern.compile("^(\\d*)x(\\d*)$")

fun Iterator<Char>.take(n: Int) = (0 until n).map { this.next() }.joinToString(separator = "")
fun Iterator<Char>.takeUntil(c: Char) : String = this.asSequence().map { it }.takeWhile { it != c }.joinToString(separator = "")

fun decompress(str: String) = decompress(str.iterator())

fun  decompress(seq: Iterator<Char>): Long {
    tailrec fun decompressInner(seq: Iterator<Char>, len: Long = 0) : Long {
        if (!seq.hasNext()) return len
        val char = seq.next()
        if (char=='('){
            val term = seq.takeUntil(')')
            println(term)
            val matcher = termRegex.matcher(term)
            val numChars = matcher.group(1).toInt()
            val multiplier = matcher.group(2).toInt()
            println("$term : $numChars x $multiplier")
            val followingString = seq.take(numChars)
            println("Following : $followingString")
            val innerLength = decompressInner(followingString.iterator(),0)
            return decompressInner(seq, len + (multiplier*innerLength))
        } else {
            return decompressInner(seq, len+1)
        }
    }
    return decompressInner(seq)
}

fun main(args: Array<String>) {
    val reader = InputStreamReader(String.javaClass.classLoader.getResourceAsStream("day9/compression.txt")).buffered()
    val seq: Sequence<Char> = generateSequence { reader.read().toChar() }.takeWhile { reader.ready() }
    println(decompress(seq.filterNot(Char::isWhitespace).iterator()))
}