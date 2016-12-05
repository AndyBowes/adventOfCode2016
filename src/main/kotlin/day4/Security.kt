package day4

import day3.readResource
import java.util.regex.Pattern
import java.util.stream.Collectors
import kotlin.comparisons.compareBy

val doorcodeRegex: Pattern = Pattern.compile("^([a-z-]*)-([0-9]*)\\[([a-z]{5})\\]$")!!


fun readDoorCodesFile(path: String): List<DoorCode>{
    return readResource(path)!!.map { doorcodeRegex.matcher(it) }
            .filter{ it.matches()}
            .map{DoorCode(it.group(1), it.group(2).toInt(), it.group(3))}
            .collect(Collectors.toList<DoorCode>())
}

data class DoorCode(val encryptedName:String, val sectorId: Int, val checksum: String){

    fun calculateChecksum() = encryptedName.groupBy{ c -> c }
            .map { e -> Pair(e.key, -e.value.size) }
            .filterNot { it.first == '-' }
            .sortedWith(compareBy({ it.second},{it.first}))
            .take(5).map { it.first }
            .joinToString("")

    fun isValid(): Boolean{
        return (calculateChecksum() == checksum)
    }

}

private fun decryptChar(c: Char, n: Int): Char{
    if ( c == '-' ) return ' '
    return (((c.toInt() + n) - 97) % 26 + 97).toChar()
}

fun String.decrypt(n: Int) = this.map{ decryptChar(it, n)}.joinToString(separator = "")






