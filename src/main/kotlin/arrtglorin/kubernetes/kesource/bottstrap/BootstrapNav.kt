package arrtglorin.kubernetes.kesource.bottstrap

import arrtglorin.kubernetes.kesource.IdGenerator
import io.kvision.core.Container
import io.kvision.html.Div
import io.kvision.html.Nav
import io.kvision.html.TAG
import io.kvision.html.Tag


enum class BootstrapNavContainer {
    div, nav
}

enum class BootstrapNavOrientation {
    horizintal, vertical
}

enum class BootstrapNavItemType {
    button, link
}

fun Container.bsNav(f: BootstrapNavBuilder.() -> Unit) {
    add(BootstrapNavBuilder().apply(f).build())
}


class BootstrapNavBuilder {
    var containerType: BootstrapNavContainer = BootstrapNavContainer.div
    var orientation: BootstrapNavOrientation = BootstrapNavOrientation.horizintal
    var itemType: BootstrapNavItemType = BootstrapNavItemType.button
    var pills: Boolean = false
    var justified: Boolean = false
    var id = IdGenerator.nextIdString()
    val customAttributes = mutableMapOf<String, String>()
    private var items = listOf<Container>()
    fun generateItems(count: Int, initializer: Container.(index: Int) -> Unit) {
        for (i in 0 until count) {
            items += when (itemType) {
                BootstrapNavItemType.button -> Tag(TAG.BUTTON)
                BootstrapNavItemType.link -> Tag(TAG.A)
            }.apply {
                if (containerType == BootstrapNavContainer.nav) {
                    addCssClass("nav-link")
                } else {
                    addCssClass("nav-item")
                }
                initializer(i)
            }
        }
    }

    private fun Container.init() {
        addCssClass("nav")
        if (orientation == BootstrapNavOrientation.vertical) {
            addCssClass("flex-column")
        }
        if (pills) {
            addCssClass("nav-pills")
        }
        if (justified) {
            addCssClass("nav-justified")
        }
    }

    private fun Container.addItems() {
        items.onEach { add(it) }
    }

    fun build(): Container =
        when (containerType) {
            BootstrapNavContainer.div -> {
                Div {
                }
            }
            BootstrapNavContainer.nav -> {
                Nav {
                }
            }
        }.apply {
            id = this@BootstrapNavBuilder.id
            init()
            addItems()
            customAttributes.onEach { (key, value) ->
                setAttribute(key, value)
            }
        }

}
