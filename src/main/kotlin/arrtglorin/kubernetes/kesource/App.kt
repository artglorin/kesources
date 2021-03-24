package arrtglorin.kubernetes.kesource

import arrtglorin.kubernetes.kesource.components.appHeader
import arrtglorin.kubernetes.kesource.components.resourceList
import io.kvision.Application
import io.kvision.core.Container
import io.kvision.core.onEvent
import io.kvision.form.select.simpleSelect
import io.kvision.html.div
import io.kvision.i18n.DefaultI18nManager
import io.kvision.i18n.I18n
import io.kvision.module
import io.kvision.panel.root
import io.kvision.require
import io.kvision.startApplication


lateinit var pageContainer: Container

class App : Application() {
    init {
        require("css/kvapp.css")
    }

    override fun start() {
        println("!!!START!!!")
        I18n.manager =
            DefaultI18nManager(
                mapOf(
                    "pl" to require("i18n/messages-pl.json"),
                    "en" to require("i18n/messages-en.json"),
                    "ru" to require("i18n/messages-ru.json")
                )
            )

        root("kvapp") {
            div {
                design("root")
                appHeader()
                div {
                    design("center.nav.root")
                    div {
                        design("center.nav.resourceList")
                        resourceList()
                    }
                    div {
                        design("center.content")
                        pageContainer = this
                    }

                }
                div{
                    design("footer.div")
                    simpleSelect(listOf("en" to "English", "ru" to "Russian"), I18n.language) {
                        onEvent {
                            change = {
                                I18n.language = self.value ?: "en"
                            }
                        }
                    }
                }

            }
        }
        initRouting()
    }
}

fun main() {
    startApplication(::App, module.hot)
}
