package day7

fun List<String>.findProtocols(): List<String> {
    return this.filter{it.isProtocol()}
}

fun List<String>.findSSLProtocols(): List<String> {
    return this.filter{it.isSSL()}
}

fun String.isProtocol(): Boolean{
    return this.containsAbba()
            && !this.getHypernets().any { it.containsAbba() }
}

fun String.isSSL(): Boolean {
    val abaSet = this.getSupernets().map{ it.findAba() }.flatten().map { it.inverse() }.toSet()
    val hypernets = this.getHypernets()
    return abaSet.isNotEmpty()
            && hypernets.isNotEmpty()
            && hypernets.any{ hypernet -> abaSet.any{ hypernet.contains(it)}}
}

fun String.inverse() = this[1] + this.substring(0,2)

fun String.getHypernets(): List<String>{
    return (0 until length).filter { this[it] == '[' }
            .map{ this.substring(it+1, this.indexOf(']',it+1))}
}

fun String.containsAbba() : Boolean {
    return (0 until length - 3)
            .any { this[it+3] == this[it]
                    && this[it+1] == this[it+2]
                    && this[it] != this[it+1] }
}

fun String.findAba() : List<String> {
    return (0 until length - 2)
            .filter { this[it+2] == this[it]
                      && this[it] != this[it+1] }
            .map {this.substring(it,it+3)}
}

fun String.getSupernets(): List<String> = this.split(']').map { it.split('[')[0] }