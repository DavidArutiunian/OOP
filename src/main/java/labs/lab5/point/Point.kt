package labs.lab5.point

data class Point(val x: Int, val y: Int) {
    operator fun plus(point: Point): Point {
        return Point(x + point.x, y + point.y)
    }
}
