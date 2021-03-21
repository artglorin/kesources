package arrtglorin.kubernetes.kesource.pages

import arrtglorin.kubernetes.kesource.Messages
import arrtglorin.kubernetes.kesource.Messages.Components.ResourceSaveModal
import arrtglorin.kubernetes.kesource.UrlSegment
import arrtglorin.kubernetes.kesource.bottstrap.Bootstrap
import arrtglorin.kubernetes.kesource.bottstrap.bsCard
import arrtglorin.kubernetes.kesource.bottstrap.bsContainer
import arrtglorin.kubernetes.kesource.bottstrap.bsInfo
import arrtglorin.kubernetes.kesource.repository.IndexedDbRepository
import arrtglorin.kubernetes.kesource.resources.Namespace
import arrtglorin.kubernetes.kesource.tr
import arrtglorin.kubernetes.kesource.yaml
import io.kvision.core.Container
import io.kvision.core.JustifyContent
import io.kvision.core.TooltipOptions
import io.kvision.core.onEvent
import io.kvision.form.text.text
import io.kvision.html.button
import io.kvision.html.tag
import io.kvision.modal.Modal
import io.kvision.panel.hPanel
import io.kvision.toolbar.buttonGroup
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.serialization.Serializable
import org.w3c.files.Blob
import org.w3c.files.BlobPropertyBag


object NamespacePage : Page {

    override val title: String = Messages.Pages.Namespace.title

    override val segment: UrlSegment = ResourcesPage.segment.child("namespace")

    override fun Container.render() {
        bsCard {
            addCssClass(Bootstrap.Margin.mt4.name)
            body {
                title {
                    content = Messages.Pages.Namespace.title.tr()
                }
                text {
                    rich = true
                    content = Messages.Pages.Namespace.description.tr()
                }
            }
        }
        namespaceForm()
    }
}

@Serializable
data class NamespaceData(
    val namespace: String
)

val nsdb = IndexedDbRepository()

fun Container.namespaceForm() {
    bsContainer {
        addCssClass("form")
        val text = text {
            label = "Имя namespace"
        }
        hPanel(
            justify = JustifyContent.STRETCH,
            spacing = 10
        ) {
            val saveBtn = button(text = "save") {
                onClick {
                    val modal = Modal(ResourceSaveModal.title.tr()) {
                        val md = this
                        bsInfo(ResourceSaveModal.description.tr())
                        val tagInput = text {
                            maxlength = 20
                            autofocus = true
                            autocomplete = false
                        }
                        buttonGroup {
                            val saveButton = button(ResourceSaveModal.confirmText.tr()) {
                                enableTooltip(
                                    TooltipOptions(
                                        title = ResourceSaveModal
                                            .confirmTooltip.tr()
                                    )
                                )
                                disabled = true
                                onClick {
                                    nsdb.save(Namespace(text.value ?: "unknown"))
                                }
                            }
                            button(ResourceSaveModal.declineText.tr()) {
                                enableTooltip(TooltipOptions(ResourceSaveModal.declineTooltip.tr()))
                                onClick {
                                    md.hide()
                                }
                            }
                            tagInput.input.subscribe {
                                saveButton.disabled = it?.isEmpty() ?: true
                            }
                        }
                    }
                    modal.show()
                }
            }
            val downloadBtn = button(text = "download") {
                onClick {
                    val data = Blob(arrayOf(
                        yaml {
                            "apiVersion" += "v1"
                            "metadata" /= {
                                "name" += text.value ?: ""
                            }
                            "root" += "test"
                            "test" /= {
                                "array" /= {
                                    "this" += "pther"
                                }
                            }
                        }

                    ), BlobPropertyBag("application/vnd.yaml"))
                    val link: dynamic = document.createElement("a")
                    link.download = "namespace.yaml"
                    val wnd: dynamic = window
                    if (wnd.webkitURL != null) {
                        link.href = wnd.webkitURL.createObjectURL(data);
                    } else {
                        link.href = wnd.URL.createObjectURL(data);
                        link.style.display = "none";
                        document.appendChild(link);
                    }
                    link.click()
                }

            }
            text.subscribe {
                it
                    .isNullOrEmpty()
                    .let { disabled ->
                        saveBtn.disabled = disabled
                        downloadBtn.disabled = disabled
                    }
            }
        }
    }
}
