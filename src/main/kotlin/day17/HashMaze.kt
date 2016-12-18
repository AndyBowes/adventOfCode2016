package day17

import org.apache.commons.codec.binary.Hex
import java.security.MessageDigest
import java.util.*

val hash = MessageDigest.getInstance("MD5")

fun toHex(bytes: ByteArray): String {
    return Hex.encodeHexString(bytes)
}

fun String.generateHash() = toHex(hash.digest(this.toByteArray(Charsets.UTF_8)))

enum class Direction(val dx: Int, val dy: Int) {
    U(0, -1), R(1, 0), D(0, 1), L(-1, 0);

    companion object {
        fun toList() = listOf(U, D, L, R)
    }
}

data class Move(val pos: Pair<Int, Int>, val path: String, val depth: Int)

fun Move.adjacentCells(prefix: String): List<Triple<Int, Int, String>> {
    val hashCode: String = (prefix + this.path).generateHash()
    val validChars = listOf('b', 'c', 'd', 'e', 'f')
    println(hashCode)
    return Direction.toList().zip(hashCode.asIterable())
            .filter { it.second in validChars }
            .map { Triple(this.pos.first + it.first.dx, this.pos.second + it.first.dy, it.first.name) }
            .filter { it.first > 0 && it.second > 0 && it.first <= 4 && it.second <= 4 }
}

fun walkMaze(start: Pair<Int, Int>, target: Pair<Int, Int>, prefix: String): List<String> {
    var queue: Queue<Move> = ArrayDeque<Move>()
    var successfulPaths = mutableListOf<String>()
    queue.add(Move(Pair(start.first, start.second), "", 0))
    while (queue.isNotEmpty()) {
        val move = queue.remove()!!
        println(move)
        if (move.pos == target) {
            successfulPaths.add(move.path)
//            return Pair(move.depth, move.path)
        } else {
            move.adjacentCells(prefix)
                    .map { Move(Pair(it.first, it.second), move.path + it.third, move.depth + 1) }
                    .forEach { queue.add(it!!) }
        }
    }
    return successfulPaths
}

fun main(args: Array<String>) {
    println(walkMaze(Pair(1, 1), Pair(4, 4), "udskfozm").last().length)
}
