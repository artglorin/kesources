package arrtglorin.kubernetes.kesource.components

import arrtglorin.kubernetes.kesource.design
import arrtglorin.kubernetes.kesource.pages.RootPage
import io.kvision.core.Container
import io.kvision.html.div
import io.kvision.html.link
import io.kvision.html.nav


fun Container.appHeader() {
    nav {
        design("header.root")
        div {
            design("header.div")
            link(
                label = "Home",
                url = RootPage.segment.path

            ) {
                design("header.link.home")
            }
        }
    }
}
