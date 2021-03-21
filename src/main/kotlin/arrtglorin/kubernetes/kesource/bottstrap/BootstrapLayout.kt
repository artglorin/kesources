package arrtglorin.kubernetes.kesource.bottstrap

import arrtglorin.kubernetes.kesource.bottstrap.Bootstrap.Container.container
import io.kvision.core.Container
import io.kvision.html.Div
import io.kvision.html.div

fun Container.bsContainer(f: Div.() -> Unit) {
    div(classes = Bootstrap{+container}).apply(f)
}
