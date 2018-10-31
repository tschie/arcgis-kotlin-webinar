

fun main(args: Array<String>) {
    val random = Math.random()

    if (random >= 0.5) {
        println("heads")
    } else {
        println("tails")
    }

    // can return from if, don't need ternary operator
    val coin = if (random >= 0.5) "heads" else "tails"

    val temp = if (random < 0.4) {
        "too cold"
    } else if (random in 0.4..0.6) {
        "just right"
    } else {
        println("ouch")
        // last line in braces is the return value
        "too hot"
    }
    println(temp)

    // opt into mutable data structures, default listOf is immutable
    val numbers = mutableListOf<Int>()
    for(i in 0..10) { // range operator
        numbers.add(i)
    }

    // can loop through iterables
    for(number in numbers) {
        println(number)
    }
}