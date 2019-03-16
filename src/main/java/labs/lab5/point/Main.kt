package labs.lab5.point

fun main(args: Array<String>) {
    val points = Pair(Point(10, 10), Point(15, 15))
    println("point x = ${points.first.x} y = ${points.first.y}")
    println("point x = ${points.second.x} y = ${points.second.y}")
    val sum = points.first + points.second
    println("sum x = ${sum.x} y = ${sum.y}")
}
