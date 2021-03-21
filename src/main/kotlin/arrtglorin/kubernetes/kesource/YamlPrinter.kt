package arrtglorin.kubernetes.kesource

class YamlPrinter {
    val text = StringBuilder()
    private var intend = 0

    private fun intend() {
        for(i in 0..intend)  {
            text.append(" ")
        }
    }

    operator fun String.plusAssign(value: Any) {
        intend()
        text.append(this).append(": ").append(value).append("\n")
    }

    operator fun String.divAssign(f: YamlPrinter.() -> Unit) {
        intend()
        text.append(this).append(":").append("\n")
        intend+=2
        f()
        intend-=2
    }
}

fun yaml(printer: YamlPrinter.() -> Unit): String =
    YamlPrinter().apply(printer).text.toString()
