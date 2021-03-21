package arrtglorin.kubernetes.kesource.pages

import arrtglorin.kubernetes.kesource.UrlSegment
import arrtglorin.kubernetes.kesource.bottstrap.bsList
import arrtglorin.kubernetes.kesource.bottstrap.bsListItem
import arrtglorin.kubernetes.kesource.components.resources
import io.kvision.core.Container

object ResourcesPage : Page {

    override val title: String = "Kubernetes resources"

    override val segment: UrlSegment = RootPage.segment.child("resources")

    override fun Container.render() {
        bsList {
            resources
                .onEach {
                    bsListItem {
                        content = it.title
                    }
                }
        }
    }
}
