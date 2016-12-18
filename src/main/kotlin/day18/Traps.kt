package day18

fun generateNextLine(previous: String): String {
    val extendedLine = ".$previous."
    return extendedLine.zip(extendedLine.substring(2))
            .map { if (it.first != it.second) '^' else '.' }
            .joinToString("")
}

fun generateLines(firstLine: String, n: Int) = generateSequence(firstLine){ generateNextLine(it)}.take(n).toList()

fun countSafe(lines: List<String>): Int = lines.map { it.count { c -> c =='.' } }.sum()

fun main(args: Array<String>) {
    val lines = generateLines(".^^^.^.^^^.^.......^^.^^^^.^^^^..^^^^^.^.^^^..^^.^.^^..^.^..^^...^.^^.^^^...^^.^.^^^..^^^^.....^....", 400000)
    println(countSafe(lines))
}