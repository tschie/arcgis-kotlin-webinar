

fun main(args: Array<String>) {
    val page = html {
        head {
           title = "Welcome"
        }
        body {
            // mixing code with markup structure
            for (i in 1..10) {
                div {
                    text = i.toString()
                }
            }
        }
    }
    println(page)
}















open class Tag(protected val name: String) {
    val children = mutableListOf<Tag>()
    override fun toString(): String {
        return "<$name>${children.joinToString(separator = "") { it.toString() }}</$name>"
    }
}

class Html : Tag("html")

class Head : Tag("head") {
    var title: String? = null
    override fun toString(): String {
        return "<$name><title>$title</title></$name>"
    }
}

class Body : Tag("body")

class Div: Tag("div") {
    var text: String? = null
    override fun toString(): String {
        return "<$name>$text</$name>"
    }
}



fun html(init: Html.() -> Unit): Html {
    val html = Html()
    html.init()
    return html
}

fun Html.head(init: Head.() -> Unit) {
    // create an empty Head tag
    val head = Head()
    // initialize it with the lambda passed in (receiver-type)
    head.init()
    // add it to "this" Html tag (extension function)
    children.add(head)
}

fun Html.body(init: Body.() -> Unit) {
    val body = Body()
    body.init()
    children.add(body)
}

fun Body.div(init: Div.() -> Unit) {
    val div = Div()
    div.init()
    children.add(div)
}