package arrtglorin.kubernetes.kesource.pages

import arrtglorin.kubernetes.kesource.UrlSegment
import io.kvision.core.Container

val pagesList = listOf(
    RootPage,
    ResourcesPage,
    DeploymentPage,
    NamespacePage
)

interface Page {
    val title: String
    val segment: UrlSegment

    fun Container.render()
}
