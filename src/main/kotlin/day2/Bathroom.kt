package day2

val standardKeyPad: Map<Char, Map<Direction, Char>> = mapOf('1' to mapOf(Direction.R to '2', Direction.D to '4'),
        '2' to mapOf(Direction.L to '1', Direction.R to '3', Direction.D to '5'),
        '3' to mapOf(Direction.L to '2', Direction.D to '6'),
        '4' to mapOf(Direction.U to '1', Direction.R to '5', Direction.D to '7'),
        '5' to mapOf(Direction.U to '2', Direction.L to '4', Direction.R to '6', Direction.D to '8'),
        '6' to mapOf(Direction.U to '3', Direction.D to '9', Direction.L to '5'),
        '7' to mapOf(Direction.U to '4', Direction.R to '8'),
        '8' to mapOf(Direction.L to '7', Direction.U to '5', Direction.R to '9'),
        '9' to mapOf(Direction.L to '8', Direction.U to '6'))


val advancedKeyPad: Map<Char, Map<Direction, Char>> = mapOf('1' to mapOf(Direction.D to '3'),
        '2' to mapOf(Direction.R to '3', Direction.D to '6'),
        '3' to mapOf(Direction.L to '2', Direction.R to '4', Direction.U to '1', Direction.D to '7'),
        '4' to mapOf(Direction.L to '3', Direction.D to '8'),
        '5' to mapOf(Direction.R to '6'),
        '6' to mapOf(Direction.U to '2', Direction.D to 'A', Direction.L to '5', Direction.R to '7'),
        '7' to mapOf(Direction.U to '3', Direction.L to '6', Direction.R to '8', Direction.D to 'B'),
        '8' to mapOf(Direction.L to '7', Direction.U to '4', Direction.R to '9', Direction.D to 'C'),
        '9' to mapOf(Direction.L to '8'),
        'A' to mapOf(Direction.U to '6', Direction.R to 'B'),
        'B' to mapOf(Direction.U to '7', Direction.L to 'A', Direction.R to 'C', Direction.D to 'D'),
        'C' to mapOf(Direction.U to '8', Direction.L to 'B'),
        'D' to mapOf(Direction.U to 'B')
)


enum class Direction() { U, L, D, R }

fun Char.toDirection() = Direction.valueOf(this.toString())
fun String.toDirection() = Direction.valueOf(this)
fun Char.move(d: Direction, keyPad: Map<Char, Map<Direction, Char>> = standardKeyPad) = keyPad.get(this)!!.getOrElse(d, { this })

fun String.processLine(initialChar: Char, keyPad: Map<Char, Map<Direction, Char>> = standardKeyPad) = this.map{it.toDirection()}.fold(initialChar, { pos, dir -> pos.move(dir, keyPad) })

fun List<String>.processLines(keyPad: Map<Char, Map<Direction, Char>> = standardKeyPad) {
    var pos = '5'
    forEach {
        pos = it.processLine(pos, keyPad)
        print(pos)
    }
}

