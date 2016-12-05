package day5

import org.apache.commons.codec.binary.Hex
import java.nio.charset.Charset
import java.security.MessageDigest

val nums = generateSequence(1){ it + 1 }

val hash = MessageDigest.getInstance("MD5")

fun toHex(bytes: ByteArray): String {
    return Hex.encodeHexString(bytes)
}

fun findPassword(root: String, digits: Int) = nums.map{ root + it }
        .map { hash.digest(it.toByteArray(Charset.forName("UTF-8"))) }
        .map(::toHex)
        .filter {  it.startsWith("00000") }
        .map { it[5] }
        .take(digits).joinToString(separator = "")

fun findAdvancedPassword(root: String, digits: Int) : String {
    val chars: MutableList<Char> = (0..(digits-1)).map { '-' }.toMutableList()
    val codeIterator = nums.map { root + it }
            .map { hash.digest(it.toByteArray(Charset.forName("UTF-8"))) }
            .map(::toHex)
            .filter { it.startsWith("00000") }
            .map { Pair(it[5],it[6])}
            .filter{ it.first.isDigit()}
            .map{ Pair(it.first.toString().toInt(), it.second)}.iterator()

    while (chars.contains('-')){
        val entry = codeIterator.next()
        if (entry.first < 8 && chars[entry.first] == '-'){
            chars.set(entry.first, entry.second)
        }
    }

    return chars.joinToString(separator = "")
}



