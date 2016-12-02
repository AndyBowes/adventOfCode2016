package day1

enum class Direction(public val dx: Int, public val dy: Int){
    N(0,1), E(1,0), S(0,-1), W(-1,0)
}

fun Direction.turnLeft() : Direction {
    return when (this){
        Direction.N -> Direction.W
        Direction.E -> Direction.N
        Direction.S -> Direction.E
        Direction.W -> Direction.S
    }
}

fun Direction.turnRight(): Direction {
    return when (this){
        Direction.N -> Direction.E
        Direction.E -> Direction.S
        Direction.S -> Direction.W
        Direction.W -> Direction.N
    }
}

fun Direction.turn(dir: String) = when (dir) {
    "L" -> this.turnLeft()
    "R" -> this.turnRight()
    else -> throw IllegalArgumentException("Unexpected Direction $dir")
}

class Step(def:String){
    val turn = def.substring(0,1)
    val distance = def.substring(1).toInt()
}

fun <T> List<T>.tail(): List<T> = drop(1)
fun <T> List<T>.head(): T= first()

fun String.toSteps(): List<Step> = this.split(",").map { Step(it.trim()) }
fun Triple<Direction,Int,Int>.makeStep(step:Step) : Triple<Direction,Int,Int>{
    val nextDir = this.first.turn(step.turn)
    return Triple(nextDir, this.second + nextDir.dx*step.distance, this.third + nextDir.dy*step.distance)
}
fun Triple<Direction, Int, Int>.distance() = Math.abs(second) + Math.abs(third)

fun List<Step>.walk():Triple<Direction,Int,Int> = this.fold(Triple(Direction.N,0,0), {pos, step -> pos.makeStep(step)})
fun List<Step>.findDuplicatePos() : Pair<Int, Int>{
    tailrec fun inner(steps: List<Step>, pos: Triple<Direction,Int, Int>, visited: MutableList<Pair<Int,Int>>): Pair<Int,Int>{
        val step = steps.head()
        val nextDir = pos.first.turn(step.turn)
        var v = Pair(pos.second,pos.third)
        for (d in (1..step.distance)){
            v = Pair(v.first+nextDir.dx, v.second+nextDir.dy)
            if (visited.contains(v)) return v
            visited.add(v)
        }
        return inner(steps.tail(), Triple(nextDir,v.first,v.second), visited)
    }
    return inner(this, Triple(Direction.N,0,0), mutableListOf(Pair(0,0)))
}
