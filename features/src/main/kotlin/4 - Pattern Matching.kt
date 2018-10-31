

fun main(args: Array<String>) {

    val random = Math.round(Math.random() * 100.0)

    // can return a value
    val grade : Any = when { // Any = Object
        // check against conditions
        random in 90..100 -> true
        random % 2 == 0L -> "even"
        random <= 50 -> Exception("fail")
        else -> random // need else because we're setting a value
    }
    // java switch can't check against conditions

    // can take an argument (even null)
    when (grade) {
        true -> println("good job")
        "even" -> println("got even")
        65L, 75L, 85L -> println("letter grade")
        // smart-casting
        is Exception -> println("$random -> ${grade.message}")
        // don't need else
    }
    // java switch can only compare against values of the same type

}