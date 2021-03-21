package arrtglorin.kubernetes.kesource.components

import arrtglorin.kubernetes.kesource.Messages
import arrtglorin.kubernetes.kesource.bottstrap.*
import arrtglorin.kubernetes.kesource.bottstrap.Bootstrap.Text.textCenter
import arrtglorin.kubernetes.kesource.bottstrap.Bootstrap.h4
import arrtglorin.kubernetes.kesource.bottstrap.Bootstrap.m3
import arrtglorin.kubernetes.kesource.currentLocation
import arrtglorin.kubernetes.kesource.pages.DeploymentPage
import arrtglorin.kubernetes.kesource.pages.NamespacePage
import arrtglorin.kubernetes.kesource.pages.ResourcesPage
import arrtglorin.kubernetes.kesource.tr
import io.kvision.core.Container
import io.kvision.html.h4
import io.kvision.html.link

val resources = listOf(
    NamespacePage,
    DeploymentPage
)

fun Container.resourceList() {
    bsContainer {
        addCssClass("text-center")
        h4(
            classes = Bootstrap {
                +textCenter
                +m3
            }
        ) {
            link(
                label = Messages.resourcesList.tr(),
                url = ResourcesPage.segment.path,
                classes = Bootstrap { +h4; +Bootstrap.TextColor.textInfo }
            )
        }
        bsNav {
            containerType = BootstrapNavContainer.nav
            pills = true
            orientation = BootstrapNavOrientation.vertical
            itemType = BootstrapNavItemType.link
            generateItems(resources.size) { index ->
                resources[index].let { page ->
                    setAttribute("href", page.segment.path)
                    +page.title.tr()
                    currentLocation.subscribe {
                        if (page.segment == it) {
                            removeCssClass(Bootstrap.TextColor.textInfo.name)
                            addCssClass(Bootstrap.Backgroung.bgInfo.name)
                            addCssClass(Bootstrap.TextColor.textWhite.name)
                        } else {
                            removeCssClass(Bootstrap.Backgroung.bgInfo.name)
                            removeCssClass(Bootstrap.TextColor.textWhite.name)
                            addCssClass(Bootstrap.TextColor.textInfo.name)
                        }
                    }
                }

            }
        }
    }
}
