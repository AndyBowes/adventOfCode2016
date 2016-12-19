package day19

import java.util.*

fun solveJospehus(n: Int): Int {
    val l = n - Integer.highestOneBit(n)
    val safePosition = 2 * l + 1
    return safePosition
}

fun takeOppositePresent(n:Int): Int {
    val left: Deque<Int> = ArrayDeque<Int>((1..n/2).toList())
    val right: Deque<Int> = ArrayDeque<Int>((n/2 + 1 .. n).toList())

    while (left.size + right.size > 1){
        right.poll()
        right.add(left.poll())
        if ((left.size + right.size) % 2 == 0)
            left.add(right.poll())
    }
    return right.poll()!!
}

fun main(args: Array<String>) {
    println(solveJospehus(3017957))
    println(takeOppositePresent(3017957))
}


