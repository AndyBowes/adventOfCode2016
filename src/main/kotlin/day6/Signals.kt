package day6

fun List<String>.findSignal() : String{
    val occurrances = mutableListOf<MutableList<Char>>()
    for (s in this){
        for (i in 0 until s.length){
            if (occurrances.size < i+1){
                occurrances.add(mutableListOf<Char>())
            }
            occurrances[i].add(s[i])
        }
    }
    return occurrances.map { it.groupBy { it }
            .toList().map { Pair(it.first, it.second.count()) }
            .sortedByDescending { it.second }.first() }
            .map { it.first }
            .joinToString(separator = "")
}