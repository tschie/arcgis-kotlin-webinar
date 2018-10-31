

// final by default, use "open" to make it extendable
open class Car(private val make: String, // access modifiers
               val model: String, // readOnly property
               var speed: Double) { // settable property

    init {
        println("Initializing")
    }

    constructor(make: String, model: String) : this(make, model, 0.0) {
        // any additional initialization
    }

    // object is also how you would create a singleton in Kotlin
    companion object {
        // static properties
        const val licenseClass = "C"
    }

    // override keyword instead of annotation
    override fun toString(): String {
        return "$make $model going ${speed}mph" // string interpolation
    }
}

fun main(args: Array<String>) {
    // now new keyword
    val car = Car("Honda", "Civic")
    // can access fields as properties
    println(car.model)
    car.speed += 60.0
    // can access static properties
    println(Car.licenseClass)
}