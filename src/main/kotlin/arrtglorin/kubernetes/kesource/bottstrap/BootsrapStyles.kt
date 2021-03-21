package arrtglorin.kubernetes.kesource.bottstrap

import arrtglorin.kubernetes.kesource.css.CssBuilder
import arrtglorin.kubernetes.kesource.css.CssStyle
import io.kvision.core.CssClass

//inline class HexColor(value: String)

object Bootstrap {
    enum class Size{
        sm, md, lg, lx, xxl
    }
    object Border {
        val right = CssStyle("border-right")
        object Color {
            val info = CssStyle("border-info")
        }
    }
    object Padding {
        val p4 = CssStyle("p-4")
    }
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
    sealed class BgColors(name: String) : CssStyle(name) {
        object BgDark:  BgColors("bg-dark")
        object BgLight:  BgColors("bg-light")
        object BgPrimary: BgColors("bg-primary")
        class BgCustom(hexValue: String): BgColors("")
    }
    object Alert {
        val alert = CssStyle("alert")
        val alertLight = CssStyle("alert-light")

        fun io.kvision.core.Container.bsCssAlertLight() {
            addCssClass(alert.name)
            addCssClass(alertLight.name)
        }
    }

    object Container{
        val container = CssStyle("container")
        val containerFluid = CssStyle("container-fluid")
    }

    object Text {
        val textDark = CssStyle("text-dark")
        val textCenter = CssStyle("text-center")


    }

    object Navbar {
        val navbar = CssStyle("navbar")
        val fixedTop = CssStyle("fixed-top")
        val navbarBrand = CssStyle("navbar-brand")
        fun navbarExpand(size: Size): CssStyle =
            CssStyle("navbar-expand-$size")
        sealed class NavbarColors(name: String): CssStyle(name) {
            class NavbarDark(background: BgColors? = null): NavbarColors("""navbar-dark${if 
                    (background 
                != null) " ${background.name}" else ""}""")
            class NavbarLight(): NavbarColors("navbar-light")
        }

    }

    val active = CssStyle("active")

    val textPrimary = CssStyle("text-primary")
    val textBody = CssStyle("text-body")


    val card = CssStyle("card")
    val cardBody = CssStyle("card-body")
    val cardTitle = CssStyle("card-title")
    val cardText = CssStyle("card-text")

    val h1 = CssStyle("h-1")
    val h2 = CssStyle("h-2")
    val h3 = CssStyle("h-3")
    val h4 = CssStyle("h-4")
    val h5 = CssStyle("h-5")

    val linkPrimary = CssStyle("link-primary")
    val badge = CssStyle("badge")


    /**
     * all margin = 3
     */
    val m3 = CssStyle("m-3")
    val p3 = CssStyle("p-3")

    /**
     * Font-size = 3
     */
    val fs1 = CssStyle("fs-1")
    val fs2 = CssStyle("fs-2")
    val fs3 = CssStyle("fs-3")
    val fs4 = CssStyle("fs-4")
    val fs5 = CssStyle("fs-5")
    val fs6 = CssStyle("fs-6")
    val btn = CssStyle("btn")
    val btnPrimary = CssStyle("btn-primary")
    val listGroup = CssStyle("list-group")
    val listGroupFlush = CssStyle("list-group-flush")
    val listGroupItem = CssStyle("list-group-item")
    val listGroupItemAction = CssStyle("list-group-item-action")
    val borderBottom = CssStyle("border-bottom")

    operator fun invoke(f: CssBuilder.() -> Unit): Set<String> {
        return CssBuilder().apply(f).set
    }

}

