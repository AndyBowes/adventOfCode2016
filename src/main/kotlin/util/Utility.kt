package util

import java.util.stream.Collectors
import java.util.stream.Stream

fun readResource(path: String): Stream<String> {
    val inputStream = String.javaClass.classLoader.getResourceAsStream(path)
    return inputStream.bufferedReader().lines()
}

fun readResourceLines(path: String): List<String>{
    return readResource(path).collect(Collectors.toList<String>())
}
