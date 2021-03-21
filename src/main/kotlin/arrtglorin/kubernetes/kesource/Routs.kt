package arrtglorin.kubernetes.kesource

import arrtglorin.kubernetes.kesource.bottstrap.Bootstrap
import arrtglorin.kubernetes.kesource.bottstrap.Bootstrap.btn
import arrtglorin.kubernetes.kesource.bottstrap.Bootstrap.btnPrimary
import arrtglorin.kubernetes.kesource.bottstrap.bsCard
import arrtglorin.kubernetes.kesource.pages.RootPage
import arrtglorin.kubernetes.kesource.pages.pagesList
import io.kvision.core.onClick
import io.kvision.html.div
import io.kvision.html.link
import io.kvision.navigo.BeforeHook
import io.kvision.navigo.RouteHooks
import io.kvision.routing.Routing
import io.kvision.routing.Strategy
import io.kvision.routing.routing
import kotlinx.browser.window

object Routs {
    object Resources {
        val namespace = "/resources/namespace"
    }


}

fun initRouting() {
    Routing
        .init(
            root = "/",
            useHash = true,
            strategy = Strategy.ALL
        )
    pagesList
        .onEach { page ->
            console.log("on page", page.segment.path)
            routing
                .on(
                    page.segment.path.removePrefix("#"),
                    {
                        currentLocation.value = page.segment
                        window.document.title = page.title
                        pageContainer.removeAll()
                        page
                            .apply {
                                pageContainer.render()
                            }
                    }
                )
        }
    routing
        .on(
            {
                RootPage.apply {
                    currentLocation.value = segment
                    window.document.title = title
                    pageContainer.removeAll()
                    pageContainer.render()
                }
            }
        )
        .notFound({
            window.document.title = "404 Not found"
            pageContainer.removeAll()
            currentLocation.value = UrlSegment(null, "404")
            pageContainer.bsCard {
                console.log(
                    "not found %s",
                    routing.getCurrentLocation().url
                )
                body {
                    title {
                        content = "Page not found ${routing.getCurrentLocation().url}"
                    }
                    text {
                        link(
                            label = "Return home",
                            classes = Bootstrap { +btn; +btnPrimary; },
                            url = "/"
                        )
                        div(
                            content = "Go back",
                            classes = Bootstrap { +btn; +btnPrimary; },
                        ) {
                            onClick {
                                window.history.back()
                            }
                        }
                    }
                }
            }
        })
        .resolve(getcurrent().removePrefix("#"))

}

fun getcurrent() = window.location.href
