package day15

val nums = generateSequence(0){ it + 1 }

data class Disc(val id: Int, val size: Int, val offset:Int){
    fun position(dropTime:Int) = (id + offset + dropTime) % size
}

fun findDropTime(disks: List<Disc>) = nums.filter{ time -> disks.all{ it.position(time) == 0}}.first()

fun main(args: Array<String>) {
    val discs = listOf(Disc(1,13,11), Disc(2,5,0), Disc(3,17,11), Disc(4,3,0), Disc(5,7,2), Disc(6,19,17), Disc(7,11,0))
    println(findDropTime(discs))
}