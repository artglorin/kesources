package arrtglorin.kubernetes.kesource.components

import arrtglorin.kubernetes.kesource.bottstrap.Bootstrap
import arrtglorin.kubernetes.kesource.pages.RootPage
import io.kvision.core.Container
import io.kvision.html.div
import io.kvision.html.link
import io.kvision.html.nav


fun Container.appHeader() {
    nav(classes = Bootstrap {
        +Bootstrap.Navbar.navbar
        +Bootstrap.Navbar.NavbarColors.NavbarDark()
        +Bootstrap.BgColors.BgPrimary
        +Bootstrap.Navbar.navbarExpand(Bootstrap.Size.lg)
//        +Bootstrap.Navbar.fixedTop
    }) {
        div(classes = Bootstrap { +Bootstrap.Container.containerFluid }) {
            link(
                label = "Home",
                url = RootPage.segment.path,
                classes = Bootstrap {
                    +Bootstrap
                        .Navbar.navbarBrand
                }
            )
        }
    }
}
