
// auto-generates equals, hashCode, and toString
data class Customer(val name: String, var email: String)

// can still override auto-generated implementations

fun main(args: Array<String>) {
    println(Customer("Tyler", "tyler@email.com"))
}