package arrtglorin.kubernetes.kesource.pages

import arrtglorin.kubernetes.kesource.Messages.Pages.Welcome
import arrtglorin.kubernetes.kesource.UrlSegment
import arrtglorin.kubernetes.kesource.bottstrap.bsCard
import arrtglorin.kubernetes.kesource.design
import io.kvision.core.Container
import io.kvision.html.div

object RootPage : Page {

    override val title: String = "Kesource"
    override val segment = UrlSegment(segment = "#")

    override fun Container.render() {
        div {
            design("pages.root.div")
            bsCard {
                design("pages.root.card")
                title {
                    content = Welcome.title
                }
                text {
                    content = Welcome.description
                }
            }
        }
    }
}
