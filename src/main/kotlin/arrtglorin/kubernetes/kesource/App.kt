package arrtglorin.kubernetes.kesource

import arrtglorin.kubernetes.kesource.bottstrap.Bootstrap
import arrtglorin.kubernetes.kesource.bottstrap.bs
import arrtglorin.kubernetes.kesource.components.appHeader
import arrtglorin.kubernetes.kesource.components.resourceList
import io.kvision.Application
import io.kvision.core.Container
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
            div() {
                bs {
                    container { fluid = true }
                }
                appHeader()
                div(classes = setOf("row", "gx-3")) {
                    bs {
                        row {
                            gx(3)
                        }
                    }
                    div(classes = setOf("col-2")) {
                        bs {
                            col(2)
                            border {
                                right = true
                                color(info)
                            }
                        }
                        addCssClass(Bootstrap.Border.right.name)
                        addCssClass(Bootstrap.Border.Color.info.name)
                        resourceList()
                    }
                    div(classes = setOf("col-10")) {
                        bs {
                            col(10) {}
                        }
                        pageContainer = this
                    }

                }
            }

//            dockPanel(classes = Bootstrap { +container; +m3 }) {
////                up {
////                }
////                left {
////                }
////                center {
//////                    flexPanel(
//////                        FlexDirection.ROW,
//////                        FlexWrap.WRAP,
//////                        JustifyContent.FLEXSTART,
//////                        AlignItems.CENTER,
//////                        spacing = 5
//////                    ) {
////
//////                    }
////                    height = 100.perc
////                    width = 100.perc
////                }
////                down {
////                    position = Position.ABSOLUTE
////                    simpleSelect(listOf("en" to "English", "ru" to "Russian"), I18n.language) {
////                        onEvent {
////                            change = {
////                                I18n.language = self.value ?: "en"
////                            }
////                        }
////                    }
////                }
//            }


        }
        initRouting()
    }
}

fun main() {
    startApplication(::App, module.hot)
}
