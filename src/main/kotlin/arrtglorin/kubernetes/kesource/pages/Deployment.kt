package arrtglorin.kubernetes.kesource.pages

import arrtglorin.kubernetes.kesource.Messages
import arrtglorin.kubernetes.kesource.Segments.resourcesSegment
import arrtglorin.kubernetes.kesource.UrlSegment
import arrtglorin.kubernetes.kesource.bottstrap.bsCard
import arrtglorin.kubernetes.kesource.bottstrap.bsContainer
import arrtglorin.kubernetes.kesource.tr
import io.kvision.core.Container


object DeploymentPage : Page {

    override val title: String = Messages.Pages.Deployment.title

    override val segment: UrlSegment = ResourcesPage.segment.child("deployment")

    override fun Container.render() {
        bsContainer {
            bsCard {
                title {
                    content = Messages.Pages.Deployment.title.tr()
                }
                text {
                    rich = true
                    content = Messages.Pages.Deployment.description.tr()
                }
            }
        }
    }
}
