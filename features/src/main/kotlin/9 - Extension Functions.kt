
// can access from Java with ListUtils.swapEnds(list)
@file:JvmName("ListUtils")

fun <T> List<T>.swapEnds() : List<T> {
    return if (this.isEmpty() || this.size == 1) {
        this // refers to the original list
    } else {
        val mutableCopy = this.toMutableList()
        mutableCopy[0] = this.last()
        // lastIndex is an extension property
        mutableCopy[this.lastIndex] = this.first()
        mutableCopy
    }
}

infix fun Int.add(n: Int) = this + n // omit braces for single expressions

fun main(args: Array<String>) {
    val fruits = listOf("apple", "banana", "orange")
    println(fruits.swapEnds())

    print(2.add(2)) // what you'd see in Java
    print(2 add 2) // with infix
}