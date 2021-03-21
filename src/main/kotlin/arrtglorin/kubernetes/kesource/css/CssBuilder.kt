package arrtglorin.kubernetes.kesource.css

class CssBuilder {
    val set = mutableSetOf<String>()
    operator fun CssStyle.unaryPlus() {
        set += name
    }

}
