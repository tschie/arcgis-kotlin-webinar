
// default values eliminate redundant overloads
data class Point(var x : Double = 0.0,
                 var y: Double = 0.0,
                 var z: Double? = null)

fun main(args: Array<String>) {
    // use all or some defaults
    val originXY = Point()
    val xy = Point(50.0, 100.0)
    val z = Point(1.0, 1.0, 1.0)

    // named arguments
    val y = Point(y = 25.0)

    println(originXY)
    println(xy)
    println(y)
    println(z)
}