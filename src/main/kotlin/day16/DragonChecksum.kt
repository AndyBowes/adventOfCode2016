package day16

tailrec fun fillDisk(a: String, diskSize: Int) : String {
    if (a.length>= diskSize)
        return a.substring(0, diskSize)
    return fillDisk(a + "0" + a.reversed().map { if (it =='1') '0' else '1' }.joinToString(separator = ""), diskSize)
}

tailrec fun generateChecksum(a: String) : String {
    val checkSum = (0 until a.length step 2).map { if (a[it] == a[it+1]) '1' else '0'}.joinToString(separator = "")
    if (checkSum.length % 2 == 1) return checkSum
    return generateChecksum(checkSum)
}

fun dragonChecksum(initial: String, diskSize: Int): String {
    val contents = fillDisk(initial, diskSize)
    return generateChecksum(contents)
}

fun main(args: Array<String>) {
    println(dragonChecksum("01000100010010111",35651584))
}