package arrtglorin.kubernetes.kesource.bottstrap

import arrtglorin.kubernetes.kesource.css.CssBuilder
import arrtglorin.kubernetes.kesource.css.CssStyle

object Bootstrap {
    object Margin {
        val m4 = CssStyle("m-4")
        val mt4 = CssStyle("mt-4")
    }

    object Backgroung {
        val bgInfo = CssStyle("bg-info")
    }

    object TextColor {
        val textInfo = CssStyle("text-info")
        val textWhite = CssStyle("text-white")
    }

    object Alert {
        val alert = CssStyle("alert")
        val alertLight = CssStyle("alert-light")

        fun io.kvision.core.Container.bsCssAlertLight() {
            addCssClass(alert.name)
            addCssClass(alertLight.name)
        }
    }

    object Container {
        val container = CssStyle("container")
    }

    object Text {
        val textCenter = CssStyle("text-center")


    }

    val textPrimary = CssStyle("text-primary")


    val card = CssStyle("card")
    val cardBody = CssStyle("card-body")
    val cardTitle = CssStyle("card-title")
    val cardText = CssStyle("card-text")

    val h2 = CssStyle("h-2")
    val h4 = CssStyle("h-4")

    /**
     * all margin = 3
     */
    val m3 = CssStyle("m-3")

    /**
     * Font-size = 3
     */
    val btn = CssStyle("btn")
    val btnPrimary = CssStyle("btn-primary")
    val listGroup = CssStyle("list-group")
    val listGroupFlush = CssStyle("list-group-flush")
    val listGroupItem = CssStyle("list-group-item")
    val listGroupItemAction = CssStyle("list-group-item-action")

    operator fun invoke(f: CssBuilder.() -> Unit): Set<String> {
        return CssBuilder().apply(f).set
    }

}
