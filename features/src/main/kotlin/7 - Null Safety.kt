
// String? indicates a nullable type
data class Employee(val name: String, var email: String?, val car: Car?)


fun main(args: Array<String>) {
    val employee = Employee("Tim", null, null)

    //println(employee.email.length)

    if (employee.email != null) {
        //println(employee.email.length)
    }

    val email : String? = employee.email
    if (email != null) {
        println(email.length)
    }

    // safe operator
    println(employee.email?.length)

    // can chain these
    println(employee.car?.model?.length)

    // can provide an alternative in case of null
    println(employee.email ?: "${employee.name}@esri.com")
}