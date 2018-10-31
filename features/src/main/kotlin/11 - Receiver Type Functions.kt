
class Gson {
    var lenient = false
    var serializeNulls = false
    var prettyPrint = false
    var version = 1.0
}

fun main(args: Array<String>) {
    val gson = Gson().apply {
        this.serializeNulls = true
        // can omit "this"
        prettyPrint = true
        // can also call functions
    }
    /**
     * Gson gson = new Gson();
     * gson.serializeNulls = true;
     * gson.prettyPrint = true;
     */
    println(gson.prettyPrint)
}