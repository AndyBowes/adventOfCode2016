package day3

import java.util.stream.Stream


fun readResource(path: String): Stream<String> {
    val inputStream = String.javaClass.classLoader.getResourceAsStream(path)
    return inputStream.bufferedReader().lines()
}

fun String.replaceMultipleSpaces() = this.trim().replace("\\s+".toRegex(), " ")
fun String.toSortedInts() = this.split(" ").map(String::toInt).sorted()
fun List<Int>.isTriangle(): Boolean {
    val sides = this.sorted()
    return (sides.get(0) + sides.get(1) > sides.get(2))
}

fun getTriangles(path: String) = getLines(path).filter{ it.isTriangle() }

fun getLines(path: String) = readResource(path).map { it.replaceMultipleSpaces().toSortedInts() }

fun countTriangles(path: String): Long =
    getTriangles(path).count()

public fun <T> Sequence<T>.batch(n: Int): Sequence<List<T>> {
    return BatchingSequence(this, n)
}

private class BatchingSequence<T>(val source: Sequence<T>, val batchSize: Int) : Sequence<List<T>> {
    override fun iterator(): Iterator<List<T>> = object : AbstractIterator<List<T>>() {
        val iterate = if (batchSize > 0) source.iterator() else emptyList<T>().iterator()
        override fun computeNext() {
            if (iterate.hasNext()) setNext(iterate.asSequence().take(batchSize).toList())
            else done()
        }
    }
}

fun List<List<Int>?>.verticalTriangles() =
    this.asSequence().batch(3).toList()
            .filter { it.size == 3 }
            .map { listOf( listOf<Int>(it[0]!![0],it[1]!![0],it[2]!![0]),
                listOf<Int>(it[0]!![1],it[1]!![1],it[2]!![1]),
                listOf<Int>(it[0]!![2],it[1]!![2],it[2]!![2]))}.flatten()

fun List<List<Int>?>.countVerticalTriangles() =
        this.verticalTriangles().count{ it.isTriangle() }
