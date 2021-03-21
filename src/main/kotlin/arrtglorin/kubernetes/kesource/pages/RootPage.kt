package arrtglorin.kubernetes.kesource.pages

import arrtglorin.kubernetes.kesource.Messages.Pages.Welcome
import arrtglorin.kubernetes.kesource.UrlSegment
import arrtglorin.kubernetes.kesource.bottstrap.bsCard
import arrtglorin.kubernetes.kesource.bottstrap.bsContainer
import io.kvision.core.Container

object RootPage : Page {

    override val title: String = "Kesource"
    override val segment = UrlSegment(segment = "#")

    override fun Container.render() {
        bsContainer {
            bsCard {
                body {
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
}
