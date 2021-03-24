package arrtglorin.kubernetes.kesource.bottstrap

import arrtglorin.kubernetes.kesource.bottstrap.Bootstrap.card
import arrtglorin.kubernetes.kesource.bottstrap.Bootstrap.h2
import arrtglorin.kubernetes.kesource.bottstrap.Bootstrap.textPrimary
import arrtglorin.kubernetes.kesource.design
import io.kvision.core.Container
import io.kvision.html.*


fun Container.bsTitle(f: P.() -> Unit) {
    p(classes = Bootstrap { +h2; +textPrimary }) {
        f(this)
    }
}

fun Container.bsDescription(f: P.() -> Unit) {
    p(classes = Bootstrap { +h2 }) {
        f(this)
    }
}

fun Container.bsCard(f: Card.() -> Unit) {
    add(Card().apply(f))
}

class Card : Div() {
    val body = div { design("card.body") }

    init {
        design("card.root")
        add(body)
    }

    fun title(f: H5.() -> Unit) {
        body.add(H5 { design("card.title"); f() })
    }

    fun text(f: P.() -> Unit) {
        body.add(P { design("card.text"); f() })
    }
}
