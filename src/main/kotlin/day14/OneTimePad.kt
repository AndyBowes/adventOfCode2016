package day14

import org.apache.commons.codec.binary.Hex
import java.nio.charset.Charset
import java.security.MessageDigest

val nums = generateSequence(1){ it + 1 }

val tripleRegex = Regex(".*((.)\\2{2}).*")
val quintetRegex = Regex(".*((.)\\2{5}).*")
val hash = MessageDigest.getInstance("MD5")

fun toHex(bytes: ByteArray): String {
    return Hex.encodeHexString(bytes)
}

/**
 * Generates a Sequence of Hashes which have at least 3 characters in a row
 */
fun findMD5HashesWithTriples(salt: String) = nums.map{ Pair(it, salt + it) }
        .map { Pair(it.first, hash.digest(it.second.toByteArray(Charset.forName("UTF-8")))) }
        .map{ Pair(it.first, toHex(it.second))}
        .filter { it.second.matches(tripleRegex) }

fun String.findRepeatingChars(repeatLength: Int): Set<Char>{
    return (0..this.length-repeatLength)
            .filter{this.substring(it,it+repeatLength).equals(subSequence(it,it+1).repeat(repeatLength))}
            .map { this[it] }.toSet()
}


fun main(args: Array<String>) {
    findMD5HashesWithTriples("ngcjuoqr").take(1000).forEach {
        val matches = it.second.matches(tripleRegex)
        println("$it : $matches)")
    }

    println( "assasaaaajgggggkkttttlll".findRepeatingChars(5))
}