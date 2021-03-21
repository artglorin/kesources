package arrtglorin.kubernetes.kesource.bottstrap

import arrtglorin.kubernetes.kesource.bottstrap.Bootstrap.card
import arrtglorin.kubernetes.kesource.bottstrap.Bootstrap.cardBody
import arrtglorin.kubernetes.kesource.bottstrap.Bootstrap.cardText
import arrtglorin.kubernetes.kesource.bottstrap.Bootstrap.cardTitle
import arrtglorin.kubernetes.kesource.bottstrap.Bootstrap.h2
import arrtglorin.kubernetes.kesource.bottstrap.Bootstrap.textPrimary
import io.kvision.core.Container
import io.kvision.html.Div
import io.kvision.html.H5
import io.kvision.html.P
import io.kvision.html.p


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

class Card : Div(classes = Bootstrap { +card }) {

    fun body(f: CardBody.() -> Unit) {
        add(CardBody().apply(f))
    }

    inner class CardBody : Div(classes = Bootstrap { +cardBody }) {

        fun title(f: H5.() -> Unit) {
            add(H5(classes = Bootstrap { +cardTitle }).apply(f))
        }

        fun text(f: P.() -> Unit) {
            add(P(classes = Bootstrap { +cardText }).apply(f))
        }
    }
}
