

fun main(args: Array<String>) {
    val inventory = mapOf("apples" to 5, "bananas" to 3, "oranges" to 1)

    // pass functions to functions
    inventory.map { entry -> // lambda
        "${entry.key[0]}${entry.value}"
    }.forEach {
        // can refer to a single arg as "it"
        println(it)
    }
}