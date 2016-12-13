package day13

import java.util.*

fun Pair<Int,Int>.isWall(offset: Int) = isWall(this.first, this.second, offset)

fun isWall(x: Int, y: Int, offset:Int) = Integer.bitCount(x*x + 2*x*y + y*y + 3*x + y + offset) % 2 == 1

data class Move(val pos: Pair<Int,Int>, val visited: List<Pair<Int,Int>>, val depth: Int)

fun Move.adjacentCells(offset: Int): List<Pair<Int,Int>>{
    return listOf(Pair(pos.first-1, pos.second),
            Pair(pos.first + 1, pos.second),
            Pair(pos.first, pos.second+1),
            Pair(pos.first, pos.second -1))
            .filter { it.first >= 0 && it.second >= 0 }
            .filterNot { it in visited }
            .filterNot{ it.isWall(offset)}
}

fun walkMaze(start : Pair<Int,Int>, target: Pair<Int,Int>, offset: Int): Pair<Int,Int> {
    var queue: Queue<Move> = ArrayDeque<Move>()
    queue.add(Move(Pair(start.first, start.second), listOf(start), 0))
    val visited = mutableSetOf<Pair<Int,Int>>()
    while (queue.isNotEmpty()){
        val move = queue.remove()!!
        if (move.depth <= 50){
            visited.addAll(move.visited)
        }
        if (move.pos == target) {
            return Pair(move.depth, visited.size)
        }
        move.adjacentCells(offset).map { Move(it, move.visited + it, move.depth + 1) }.forEach { queue.add(it!!) }
    }
    return Pair(-1,0)
}

fun main(args: Array<String>) {

    println( walkMaze(Pair(1,1), Pair(31,39), 1358))
}
